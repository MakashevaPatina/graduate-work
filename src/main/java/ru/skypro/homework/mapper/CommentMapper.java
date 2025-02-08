package ru.skypro.homework.mapper;

import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import org.mapstruct.Mapper;
import ru.skypro.homework.model.CommentEntity;


@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    Comment commentEntityToCommentDTO(CommentEntity entity);

}
