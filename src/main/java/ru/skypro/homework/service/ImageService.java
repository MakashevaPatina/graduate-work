package ru.skypro.homework.service;

import jakarta.servlet.http.HttpServletResponse;

public interface ImageService {

    void transferImageToResponse(Long id, HttpServletResponse response);
}
