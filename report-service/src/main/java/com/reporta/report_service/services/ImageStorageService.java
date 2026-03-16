package com.reporta.report_service.services;

import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {

    String guardarImagen(MultipartFile image);
    void eliminarImagen(String imageName);
    UrlResource cargarImagen(String imageName);

}
