package ru.skypro.homework.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "comments")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private String text;

    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @ManyToOne
    private Advertisement advertisement;

    public CommentEntity() {
    }
}
