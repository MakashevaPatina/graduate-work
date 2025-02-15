//package ru.skypro.homework.service.impl;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//
//import ru.skypro.homework.exceptions.AvatarNotFoundException;
//import ru.skypro.homework.model.UserAvatar;
//
//import ru.skypro.homework.repository.UserAvatarRepository;
//
//
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.Objects;
//import org.apache.commons.lang3.RandomStringUtils;
//import static java.nio.file.StandardOpenOption.CREATE_NEW;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class ImageServiceImpl implements ru.skypro.homework.service.ImageService {
//    @Value("${ads.image.dir.path}")
//    private String imageDir;
//
//    private final UserAvatarRepository userAvatarRepository;
//
//    /**
//     * @param id       id сущности изображения
//     * @param response ответ сервера
//     */
//
//    @Override
//    public void transferImageToResponse(Long id, HttpServletResponse response) {
//        log.info("Был вызван метод для трансформации изображения для ответа{}{}", id, response);
//        UserAvatar userAvatar = userAvatarRepository.findById(id)
//                .orElseThrow(() -> new AvatarNotFoundException("Не удалось найти изображение по id: " + id));
//        try (InputStream is = Files.newInputStream(Path.of(userAvatar.getFilePath()));
//             OutputStream os = response.getOutputStream()) {
//            response.setStatus(200);
//            response.setContentType(userAvatar.getMediaType());
//            response.setContentLength((int) userAvatar.getFileSize());
//            is.transferTo(os);
//
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to transfer image to response ", e);
//        }
//    }
//}