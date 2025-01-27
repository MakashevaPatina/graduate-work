package ru.skypro.homework.model;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private Integer author;
}
