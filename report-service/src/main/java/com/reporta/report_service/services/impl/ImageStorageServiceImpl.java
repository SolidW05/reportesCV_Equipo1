package com.reporta.report_service.services.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.reporta.report_service.exceptions.NotFoundObjectException;
import com.reporta.report_service.exceptions.NotValidResourceException;
import com.reporta.report_service.services.ImageStorageService;

@Service
public class ImageStorageServiceImpl implements ImageStorageService {

    @Value("${file.uploads-dir}")
    private String uploadsDir;

    @Override
    public String guardarImagen(MultipartFile image) {

        if (image == null || image.isEmpty()) {
            throw new NotFoundObjectException("La imagen está vacía");
        }

        if (!image.getContentType().startsWith("image/")) {
            throw new NotValidResourceException("El archivo no es una imagen válida");
        }
        
        try {

            String nombreArchivo = generarNombre(image.getOriginalFilename());

            Path rutaUploads = Paths.get(uploadsDir).toAbsolutePath();
            Files.createDirectories(rutaUploads);

            Path rutaArchivo = rutaUploads.resolve(nombreArchivo);

            Files.copy(
                    image.getInputStream(),
                    rutaArchivo,
                    StandardCopyOption.REPLACE_EXISTING);

            return nombreArchivo;

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la imagen", e);
        }
    }

    @Override
    public void eliminarImagen(String imageName) {

        if (imageName == null || imageName.isEmpty()) {
            return;
        }

        try {

            Path rutaArchivo = Paths.get(uploadsDir)
                    .toAbsolutePath()
                    .resolve(imageName);

            Files.deleteIfExists(rutaArchivo);

        } catch (IOException e) {
            throw new RuntimeException("Error al eliminar la imagen", e);
        }
    }

    @Override
    public UrlResource cargarImagen(String imageName) {

        try {

            Path rutaArchivo = Paths.get(uploadsDir)
                    .toAbsolutePath()
                    .resolve(imageName);

            UrlResource recurso = new UrlResource(rutaArchivo.toUri());

            if (!recurso.exists() || !recurso.isReadable()) {
                throw new RuntimeException("No se pudo leer la imagen");
            }

            return recurso;

        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al cargar la imagen", e);
        }
    }


    private String generarNombre(String imageName) {

        String uuid = UUID.randomUUID().toString();

        return uuid + "_" + imageName.replace(" ", "_");
    }


}