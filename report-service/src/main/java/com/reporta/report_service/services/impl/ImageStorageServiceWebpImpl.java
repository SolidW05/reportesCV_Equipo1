package com.reporta.report_service.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.reporta.report_service.services.ImageStorageService;

// TODO: Implementar logica para guardar la imagen en formato webp
@Service
public class ImageStorageServiceWebpImpl implements ImageStorageService {

    @Value("${file.uploads-dir}")
    private String uploadsDir;

    @Override
    public String guardarImagen(MultipartFile file) {
        return "";
    }

    @Override
    public void eliminarImagen(String imageName) {
    }

    @Override
    public UrlResource cargarImagen(String imageName) {
        return null;
    }

}
