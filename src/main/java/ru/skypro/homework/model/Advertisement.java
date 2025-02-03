package ru.skypro.homework.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private Double price;
    private String image;
    @ManyToOne
    private User author;
    @OneToMany(mappedBy = "advertisement", cascade = CascadeType.REMOVE)
    private List<Comment> comments;
}
