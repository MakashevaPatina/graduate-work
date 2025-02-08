package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdService;

import java.util.List;


@Service
public class AdServiceImpl implements AdService {

    @Override
    public List<Ad> getAllAd() {
        return List.of();
    }

    @Override
    public Ad createAd(CreateOrUpdateAd adDto) {
        return new Ad();
    }

    @Override
    public ExtendedAd getAdById(int id) {
        return new ExtendedAd();
    }

    @Override
    public Ad updateAd(int id, CreateOrUpdateAd adDto) {
        return new Ad();
    }

    @Override
    public void deleteAd(int id) {
    }

    @Override
    public List<Ad> getMyAds() {
        return List.of();
    }

    @Override
    public void updateImage(int id) {
    }
}