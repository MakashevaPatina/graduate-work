package ru.skypro.homework.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import org.mapstruct.Mapper;
import ru.skypro.homework.model.CommentEntity;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "author", source = "entity.author.id") // Маппим ID автора
    @Mapping(target = "createdAt", expression = "java(mapLocalDateTimeToLong(entity.getCreatedAt()))") // Преобразуем дату
    @Mapping(target = "authorFirstName", source = "entity.author.firstName") // Имя автора
    @Mapping(target = "authorImage", expression = "java(entity.getAuthor().getUserAvatar() != null ? entity.getAuthor().getUserAvatar().getFilePath() : null)") // Аватар автора (если есть)
    Comment commentEntityToCommentDTO(CommentEntity entity);

    default long mapLocalDateTimeToLong(LocalDateTime localDateTime) {
        return localDateTime.toEpochSecond(ZoneOffset.UTC);
    }

}
