package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;

import java.io.IOException;

public interface AdService {

//    List<Ad> getAllAd();

    Ad createAd(CreateOrUpdateAd createAd, MultipartFile image, String userId) throws IOException;


//    Ad updateAd(int id, CreateOrUpdateAd adDto);
//
//    void deleteAd(int id);
//
//    ExtendedAd getAdById(Integer id);
//
//    List<Ad> getMyAds();
}
