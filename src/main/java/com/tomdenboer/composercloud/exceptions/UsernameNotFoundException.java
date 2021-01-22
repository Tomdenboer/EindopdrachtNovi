package com.tomdenboer.composercloud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UsernameNotFoundException extends RuntimeException{
    public UsernameNotFoundException(String name) {
        super("Kan gebruiker " + name + " niet vinden");
    }
    public UsernameNotFoundException() { super("Kan gebruiker niet vinden"); }
}
