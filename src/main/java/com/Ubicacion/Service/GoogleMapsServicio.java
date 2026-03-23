package com.Ubicacion.Service;


import com.Ubicacion.Dtos.GeocodigoInversoR;
import com.Ubicacion.Models.Ubicacion;
import com.Ubicacion.Repository.UbicacionRepositorio;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.Ubicacion.Exeption.GeoExeption;
import com.Ubicacion.Dtos.ReenvioGeocodigoR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoogleMapsServicio {

    @Autowired
    private GeoApiContext geoApiContext;

    @Autowired
    private UbicacionRepositorio ubicacionRepositorio;

    public GeocodigoInversoR reverseGeocode(Double latitud, Double longitud) {
        try {
            LatLng latlng = new LatLng(latitud, longitud);

            GeocodingResult[] results = GeocodingApi
                    .reverseGeocode(geoApiContext, latlng)
                    .language("es")
                    .await();

            if (results == null || results.length == 0) {
                throw new GeoExeption(
                        "No se encontró dirección para las coordenadas: " + latitud + ", " + longitud
                );
            }

            GeocodingResult resultado = results[0];

            // 1. Construimos la entidad para guardar en BD
            Ubicacion ubicacion = new Ubicacion();
            ubicacion.setLatitud(latitud);
            ubicacion.setLongitud(longitud);
            ubicacion.setDireccionCompleta(resultado.formattedAddress);

            //  componentes de Google Maps
            for (AddressComponent componente : resultado.addressComponents) {
                for (AddressComponentType tipo : componente.types) {
                    switch (tipo) {
                        case ROUTE ->
                                ubicacion.setCalle(componente.longName);
                        case STREET_NUMBER ->
                                ubicacion.setNumero(componente.longName);
                        case SUBLOCALITY_LEVEL_1, NEIGHBORHOOD -> {
                            if (ubicacion.getColonia() == null)
                                ubicacion.setColonia(componente.longName);
                        }
                        case POSTAL_CODE ->
                                ubicacion.setCodigoPostal(componente.longName);
                        case LOCALITY ->
                                ubicacion.setMunicipio(componente.longName);
                        case ADMINISTRATIVE_AREA_LEVEL_1 ->
                                ubicacion.setEstado(componente.longName);
                        case COUNTRY ->
                                ubicacion.setPais(componente.longName);
                        default -> { }
                    }
                }
            }

            // 3.guarda en bd y obtenemos el ID generado
            Ubicacion guardada = ubicacionRepositorio.save(ubicacion);

            // DTo
            GeocodigoInversoR dto = new GeocodigoInversoR();
            dto.setId(guardada.getId());
            dto.setLatitud(guardada.getLatitud());
            dto.setLongitud(guardada.getLongitud());
            dto.setDireccionCompleta(guardada.getDireccionCompleta());
            dto.setCalle(guardada.getCalle());
            dto.setNumero(guardada.getNumero());
            dto.setColonia(guardada.getColonia());
            dto.setCodigoPostal(guardada.getCodigoPostal());
            dto.setMunicipio(guardada.getMunicipio());
            dto.setEstado(guardada.getEstado());
            dto.setPais(guardada.getPais());

            return dto;

        } catch (GeoExeption e) {
            throw e;
        } catch (Exception e) {
            throw new GeoExeption("Error al llamar a Google Maps API: " + e.getMessage(), e);
        }
    }
    public ReenvioGeocodigoR forwardGeocode(String query) {
        try {
            GeocodingResult[] results = GeocodingApi
                    .geocode(geoApiContext, query)
                    .language("es")
                    .await();

            if (results == null || results.length == 0) {
                throw new GeoExeption("No se encontró ubicación para: " + query);
            }

            GeocodingResult resultado = results[0];
            ReenvioGeocodigoR dto = new ReenvioGeocodigoR();

            dto.setLatitud(resultado.geometry.location.lat);
            dto.setLongitud(resultado.geometry.location.lng);
            dto.setDireccionCompleta(resultado.formattedAddress);

            for (AddressComponent componente : resultado.addressComponents) {
                for (AddressComponentType tipo : componente.types) {
                    switch (tipo) {
                        case LOCALITY ->
                                dto.setMunicipio(componente.longName);
                        case ADMINISTRATIVE_AREA_LEVEL_1 ->
                                dto.setEstado(componente.longName);
                        default -> { }
                    }
                }
            }

            return dto;

        } catch (GeoExeption  e) {
            throw e;
        } catch (Exception e) {
                throw new GeoExeption("Error al llamar a Google Maps API: " + e.getMessage(), e);
        }
    }
}