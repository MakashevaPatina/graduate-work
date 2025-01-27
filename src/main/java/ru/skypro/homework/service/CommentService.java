package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.Exceptions.CommentNotFoundException;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private final List<Comment> comments = new ArrayList<>();

    public Comments getComments(int adId) {
        Comments response = new Comments();
        response.setCount(comments.size());
        response.setResults(new ArrayList<>(comments));
        return response;
    }

    public int addComment(int adId, CreateOrUpdateComment createComment) {
        if (createComment.getText() == null || createComment.getText().isEmpty()) {
            throw new IllegalArgumentException("Text of comment can't be empty.");
        }
        Comment newComment = new Comment();
        newComment.setPk(comments.size() + 1);
        newComment.setAuthor(0); //заглушка
        newComment.setCreatedAt(System.currentTimeMillis());
        newComment.setText(createComment.getText());
        comments.add(newComment);
        return newComment.getPk();
    }

    public void deleteComment(int adId, int commentId) {
        boolean removed = comments.removeIf(comment -> comment.getPk() == commentId);
        if (!removed) {
            throw new CommentNotFoundException("Comment isn't found.");
        }
    }

    public Comment updateComment(int adId, int commentId, CreateOrUpdateComment updateComment) {
        for (Comment comment : comments) {
            if (comment.getPk() == commentId) {
                comment.setText(updateComment.getText());
                return comment;
            }
        }
        throw new CommentNotFoundException("Comment isn't found.");
    }
}
