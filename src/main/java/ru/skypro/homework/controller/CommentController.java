package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.Exceptions.AccessDeniedException;
import ru.skypro.homework.Exceptions.CommentNotFoundException;
import ru.skypro.homework.Exceptions.UnauthorizedAccessException;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

@RestController
@RequestMapping("/ads/{id}/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<Comments> getComments(@PathVariable("id") int adId) {
        try {
            Comments comments = commentService.getComments(adId);
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (UnauthorizedAccessException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (CommentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Comment> addComment(@PathVariable("id") int adId, @RequestBody CreateOrUpdateComment createComment) {
        try {
            Comment newComment = commentService.addComment(adId, createComment);
            return new ResponseEntity<>(newComment, HttpStatus.OK);
        } catch (UnauthorizedAccessException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (CommentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") int adId, @PathVariable("commentId") int commentId) throws AccessDeniedException {
        try {
            commentService.deleteComment(adId, commentId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UnauthorizedAccessException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (CommentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") int adId, @PathVariable("commentId") int commentId, @RequestBody CreateOrUpdateComment updateComment) throws AccessDeniedException {
        try {
            Comment updatedComment = commentService.updateComment(adId, commentId, updateComment);
            return new ResponseEntity<>(updatedComment, HttpStatus.OK);
        } catch (UnauthorizedAccessException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (CommentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
