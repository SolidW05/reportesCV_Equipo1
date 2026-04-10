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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ubicacion.Service.UbicacionService;

@Service
public class GoogleMapsServicio {

    @Autowired
    private GeoApiContext geoApiContext;

    @Autowired
    private UbicacionRepositorio ubicacionRepositorio;

    @Autowired
    private UbicacionService municipioService;

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

            Ubicacion ubicacion = new Ubicacion();
            ubicacion.setLatitud(latitud);
            ubicacion.setLongitud(longitud);

            String locality       = null;
            String adminLevel2    = null;
            String codigoPostal   = null;
            String colonia        = null;

            // --- primer resultado: calle, número, cp, estado, país ---
            GeocodingResult resultado = results[0];
            ubicacion.setDireccionCompleta(resultado.formattedAddress);

            for (AddressComponent componente : resultado.addressComponents) {
                for (AddressComponentType tipo : componente.types) {
                    switch (tipo) {
                        case ROUTE ->
                                ubicacion.setCalle(componente.longName);
                        case STREET_NUMBER ->
                                ubicacion.setNumero(componente.longName);
                        case SUBLOCALITY_LEVEL_1, NEIGHBORHOOD -> {
                            if (colonia == null) colonia = componente.longName;
                        }
                        case POSTAL_CODE ->
                                codigoPostal = componente.longName;
                        case LOCALITY ->
                                locality = componente.longName;
                        case ADMINISTRATIVE_AREA_LEVEL_2 ->
                                adminLevel2 = componente.longName;
                        case ADMINISTRATIVE_AREA_LEVEL_1 ->
                                ubicacion.setEstado(componente.longName);
                        case COUNTRY ->
                                ubicacion.setPais(componente.longName);
                        default -> { }
                    }
                }
            }

            ubicacion.setCodigoPostal(codigoPostal);
            ubicacion.setColonia(colonia);

            // --- buscar municipio en TODOS los resultados que devuelve Google ---
            for (GeocodingResult r : results) {
                for (AddressComponent componente : r.addressComponents) {
                    for (AddressComponentType tipo : componente.types) {
                        if (tipo == AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_2) {
                            adminLevel2 = componente.longName;
                            break;
                        }
                    }
                }
                if (adminLevel2 != null) break;
            }

            // Si ningún resultado trajo administrative_area_level_2
            // hacemos una segunda búsqueda por código postal
            if (adminLevel2 == null && codigoPostal != null) {
                GeocodingResult[] resultsCp = GeocodingApi
                        .geocode(geoApiContext, codigoPostal + " Jalisco México")
                        .language("es")
                        .await();

                if (resultsCp != null) {
                    for (GeocodingResult r : resultsCp) {
                        for (AddressComponent componente : r.addressComponents) {
                            for (AddressComponentType tipo : componente.types) {
                                if (tipo == AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_2) {
                                    adminLevel2 = componente.longName;
                                    break;
                                }
                            }
                        }
                        if (adminLevel2 != null) break;
                    }
                }
            }

            // Asignamos el municipio con la mejor fuente disponible
            if (adminLevel2 != null) {
                if (!municipioService.existeMunicipio(adminLevel2)) {
                    System.out.println("Municipio no encontrado en BD: " + adminLevel2);
                }
                System.out.println("Municipio encontrado: " + adminLevel2);
                ubicacion.setMunicipio(adminLevel2);
            } else if (locality != null) {
                ubicacion.setMunicipio(locality);
            }

            // Guardamos en BD
            Ubicacion guardada = ubicacionRepositorio.save(ubicacion);

            // Mapeamos a DTO
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

            String locality    = null;
            String adminLevel2 = null;

            for (GeocodingResult r : results) {
                for (AddressComponent componente : r.addressComponents) {
                    for (AddressComponentType tipo : componente.types) {
                        switch (tipo) {
                            case LOCALITY ->
                                    locality = componente.longName;
                            case ADMINISTRATIVE_AREA_LEVEL_2 ->
                                    adminLevel2 = componente.longName;
                            case ADMINISTRATIVE_AREA_LEVEL_1 ->
                                    dto.setEstado(componente.longName);
                            default -> { }
                        }
                    }
                }
                if (adminLevel2 != null) break;
            }

            if (adminLevel2 != null) {
                dto.setMunicipio(adminLevel2);
            } else if (locality != null) {
                dto.setMunicipio(locality);
            }

            return dto;

        } catch (GeoExeption e) {
            throw e;
        } catch (Exception e) {
            throw new GeoExeption("Error al llamar a Google Maps API: " + e.getMessage(), e);
        }
    }
}