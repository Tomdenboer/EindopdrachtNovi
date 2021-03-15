package com.tomdenboer.composercloud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoFileAttachedException extends RuntimeException{

    public NoFileAttachedException() {
        super("Er is geen bestand meegegeven.");
    }
}