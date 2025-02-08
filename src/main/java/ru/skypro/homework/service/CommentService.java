package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.exceptions.CommentNotFoundException;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Advertisement;
import ru.skypro.homework.model.CommentEntity;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private final List<Comment> comments = new ArrayList<>();
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comments getComments(int adId) {
        List<CommentEntity> comments = commentRepository.findByAdvertisement_Id(adId);
        Comments response = new Comments();
        response.setCount(comments.size());
        response.setResults(mapEntitiesToDTOs(comments));
        return response;
    }

    public Long addComment(int adId, CreateOrUpdateComment createComment) {
        if (createComment.getText() == null || createComment.getText().isEmpty()) {
            throw new IllegalArgumentException("Text of comment can't be empty.");
        }

        CommentEntity newComment = new CommentEntity();
        newComment.setAuthor(new User());
        newComment.setText(createComment.getText());
        newComment.setAdvertisement(new Advertisement());

        return commentRepository.save(newComment).getPk();
    }

    public void deleteComment(int adId, Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public Comment updateComment(int adId, Long commentId, CreateOrUpdateComment updateComment) {
        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(commentId));
        commentEntity.setText(updateComment.getText());
        return mapEntityToDTO(commentRepository.save(commentEntity));
    }

    private List<Comment> mapEntitiesToDTOs(List<CommentEntity> entities) {
        List<Comment> comments = new ArrayList<>();
        for (CommentEntity entity : entities) {
            comments.add(mapEntityToDTO(entity));
        }
        return comments;
    }

    private Comment mapEntityToDTO(CommentEntity entity) {
        return CommentMapper.INSTANCE.commentEntityToCommentDTO(entity);
    }
}

