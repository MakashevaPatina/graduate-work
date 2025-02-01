package ru.skypro.homework.exceptions;

import ru.skypro.homework.dto.Register;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String string) {
        super();
    }
}