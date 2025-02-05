package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toEntity(Comments dto);
    Comments toDTO(Comment comment);

}
