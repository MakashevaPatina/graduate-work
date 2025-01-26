package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.model.Advertisement;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;

    @Autowired
    public AdServiceImpl(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public List<Ad> getAllAd() {
        return adRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public Ad getAdById(Integer id) {
        return adRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new IllegalArgumentException("Ad not found with id: " + id));
    }
    @Override
    public Ad createAd(Ad adDTO) {
        Advertisement advertisement = convertToEntity(adDTO);
        advertisement = adRepository.save(advertisement);
        return convertToDTO(advertisement);
    }
    @Override
    public Ad updateAd(Integer id, Ad adDetails) {
        Advertisement existingAd = adRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ad not found with id: " + id));

        existingAd.setTitle(adDetails.getTitle());
        existingAd.setDescription(adDetails.getTitle());
        existingAd.setPrice(adDetails.getPrice().doubleValue());
        existingAd.setImageUrl(adDetails.getImage());
        existingAd.setAuthor(adDetails.getAuthor());

        existingAd = adRepository.save(existingAd);
        return convertToDTO(existingAd);
    }
    @Override
    public void deleteAd(Integer id) {
        Advertisement existingAd = adRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ad not found with id: " + id));
        adRepository.delete(existingAd);
    }

    private Ad convertToDTO(Advertisement advertisement) {
        Ad dto = new Ad();
        dto.setPk(advertisement.getId());
        dto.setTitle(advertisement.getTitle());
        dto.setImage(advertisement.getImageUrl());
        dto.setPrice(advertisement.getPrice().intValue());
        dto.setAuthor(advertisement.getAuthor());
        return dto;
    }

    private Advertisement convertToEntity(Ad dto) {
        Advertisement advertisement = new Advertisement();
        advertisement.setId(dto.getPk());
        advertisement.setTitle(dto.getTitle());
        advertisement.setDescription(dto.getTitle());
        advertisement.setPrice(dto.getPrice().doubleValue());
        advertisement.setImageUrl(dto.getImage());
        advertisement.setAuthor(dto.getAuthor());
        return advertisement;
    }

}