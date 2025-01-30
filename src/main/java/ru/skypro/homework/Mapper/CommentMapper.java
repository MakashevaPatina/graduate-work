package ru.skypro.homework.Mapper;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toEntity(CommentsDTO dto);
    CommentsDTO toDTO(Comment comment);

}
