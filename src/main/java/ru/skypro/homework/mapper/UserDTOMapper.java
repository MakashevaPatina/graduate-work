package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.User;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {

    UserDTOMapper INSTANCE = Mappers.getMapper(UserDTOMapper.class);


    @Mapping(target = "image", expression = "java(getImageUrl(user))") // Добавляем URL аватарки
    //@Mapping(target = "password", ignore = true)
    UserDTO userToAllInfoUserDTO(User user);

    default String getImageUrl(User user) {
        if (user.getUserAvatar() != null) {
            return "/images/" + user.getUserAvatar().getId(); // Формируем URL аватарки
        }
        return null; // Если аватарки нет, возвращаем null
    }

    @Mapping(target = "id", source = "id")
    UserDTO userToUserId(User user);
}