package ru.skypro.homework.service;

import ru.skypro.homework.dto.Ad;

import java.util.List;
import java.util.Optional;

public interface AdService {

    List<Ad> getAllAd();

    Ad getAdById(Integer id);

    Ad createAd(Ad ad);

    Ad updateAd(Integer id, Ad adDetails);

    void deleteAd(Integer id);
}
