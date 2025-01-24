package ru.skypro.homework.dto;

import java.util.List;

public class Comments {
    private int count;
    private List<Comment> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Comment> getResults() {
        return results;
    }

    public void setResults(List<Comment> results) {
        this.results = results;
    }
}
