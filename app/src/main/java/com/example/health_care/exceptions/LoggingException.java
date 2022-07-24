package com.example.health_care.exceptions;

import java.util.Objects;

public class LoggingException extends Exception{
    private int id;

    public LoggingException(String message, int id) {
        super(message);
        this.id = id;
    }

    public String getLogoutMessage(){
        return String.format(Objects.requireNonNull(this.getMessage()), String.valueOf(this.id));
    }
}
