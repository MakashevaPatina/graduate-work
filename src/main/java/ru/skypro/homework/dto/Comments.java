package ru.skypro.homework.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Comments {
    private Integer count;
    private List<Comment> results;
}
