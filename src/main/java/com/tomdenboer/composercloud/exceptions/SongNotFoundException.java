package com.tomdenboer.composercloud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SongNotFoundException extends RuntimeException{
    public SongNotFoundException() { super("Nummer bestaat niet"); }
    public SongNotFoundException(long id) { super("Nummer met id: " + id + " bestaat niet"); }
}
