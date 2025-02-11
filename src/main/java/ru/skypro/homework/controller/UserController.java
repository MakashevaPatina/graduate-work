package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.LoginDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.impl.UserServiceImpl;

import java.io.IOException;
import java.security.Principal;

@RequiredArgsConstructor
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
@Tag(name = "Пользватели")
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/set_password")
    @Operation(summary = "Обновление пароля")
    @ApiResponse(responseCode = "201", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")

    public boolean setPassword(@RequestParam String password,
                               @RequestParam String newPassword) {
        //@AuthenticationPrincipal UserDetails) {
        return userService.setPassword(password, newPassword);
    }

    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизованном пользователе")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public UserDTO showMe(Principal principal) {
        return userService.showUserInfo(principal.getName());
    }

    @PatchMapping("/me")
    @Operation(summary = "Обновление информации об авторизованном пользователе")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public void updateUserInfo(Principal principal,
                               @RequestBody UserDTO userDTO) {

        userService.updateUserInfo(principal.getName(), userDTO);
    }

    @PatchMapping(path = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление аватара авторизованного пользователя")
    @ApiResponse(responseCode = "201", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public void uploadImage(@RequestBody LoginDTO loginDTO,
                            @RequestBody MultipartFile multipartFile) throws IOException {
        userService.uploadAvatar(loginDTO, multipartFile);
    }
}
