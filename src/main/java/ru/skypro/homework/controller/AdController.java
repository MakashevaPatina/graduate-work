package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.service.AdService;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@Validated
@Slf4j
@Tag(name = "Объявления")
public class AdController {
    private final AdService adService;

    @Autowired
    public AdController(AdService adService) {
        this.adService = adService;
    }

    //    @Operation(summary = "Получение всех объявлений")
//    @GetMapping
//    public List<Ad> getAllAds() {
//        return adService.getAllAd();
//    }
//
//    @Operation(summary = "Получение информации об объявлении")
//    @GetMapping("/{id}")
//    public ExtendedAd getAdById(@PathVariable Integer id) {
//        return adService.getAdById(id);
//    }
    @Operation(summary = "Добавление объявления")
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Ad createAd(
            @RequestPart(value = "ad") String adJson,  // Получаем JSON в виде строки
            @RequestPart("image") MultipartFile image,
            Principal principal) throws IOException {

        log.info("Создание объявления пользователем: {}", principal.getName());

        // Десериализуем JSON в объект CreateOrUpdateAd
        CreateOrUpdateAd adDto = new ObjectMapper().readValue(adJson, CreateOrUpdateAd.class);

        return adService.createAd(adDto, image, principal.getName());
    }


//    @Operation(summary = "Обновление информации об объявлении")
//    @PatchMapping("/{id}")
//    public Ad updateAd(@PathVariable Integer id, @RequestBody CreateOrUpdateAd ad) {
//        return adService.updateAd(id, ad);
//    }
//
//    @Operation(summary = "Удаление объявления")
//    @DeleteMapping("/{id}")
//    public void deleteAd(@PathVariable Integer id) {
//        adService.deleteAd(id);
//    }
//
//    @Operation(summary = "Получение объявлений авторизованного пользователя")
//    @GetMapping("/me")
//    public List<Ad> getMyAds() {
//        return adService.getMyAds();
//    }
//
//    @Operation(summary = "Обновление картинки объявления")
//    @PatchMapping("/{id}/image")
//    public void updateImage(@PathVariable int id) {
//    }
}
