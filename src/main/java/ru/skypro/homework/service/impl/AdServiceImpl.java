package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.model.Advertisement;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


@Service
public class AdServiceImpl implements AdService {

    private final AdRepository advertisementRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;

    private static final String UPLOAD_DIR = "image/";
    private static final Logger log = LoggerFactory.getLogger(AdServiceImpl.class);

    @Autowired
    public AdServiceImpl(AdRepository advertisementRepository, UserRepository userRepository, AdMapper adMapper) {
        this.advertisementRepository = advertisementRepository;
        this.userRepository = userRepository;
        this.adMapper = adMapper;
    }

    public Ad createAd(CreateOrUpdateAd dto, MultipartFile image, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String imagePath = saveImage(image);
        log.info("Mapping ad: {}, author: {}", dto, user.getUsername());
        Advertisement advertisement = adMapper.toAdvertisement(dto, user);
        log.info("Mapped advertisement: {}", advertisement);
        advertisement.setImage(imagePath);

        Advertisement savedAd = advertisementRepository.save(advertisement);
        return adMapper.toAd(savedAd);
    }

    private String saveImage(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Path.of(UPLOAD_DIR, filename);
            Files.createDirectories(path.getParent());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return path.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }
}