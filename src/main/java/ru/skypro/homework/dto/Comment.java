package ru.skypro.homework.dto;

import jakarta.persistence.*;
import lombok.Data;


@Table(name = "comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;

    private int author;
    private String authorImage;
    private String authorFirstName;
    private long createdAt;
    private String text;

}
