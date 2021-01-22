package com.tomdenboer.composercloud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UsernameExistsException extends RuntimeException{
    public UsernameExistsException(String name) {
        super("Er bestaat al een gebruiker met de naam: " + name);
    }

    public UsernameExistsException() {
        super("Er bestaat al een gebruiker met deze naam.");
    }
}
