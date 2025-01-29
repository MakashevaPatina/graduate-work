package ru.skypro.homework.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
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

    @Column(name = "ad_id")
    private int adId;
}
