package com.tomdenboer.composercloud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PlaylistNotFoundException extends RuntimeException {
    public PlaylistNotFoundException() {
        super("Playlist bestaat niet");
    }

    public PlaylistNotFoundException(long id) {
        super("Playlist met id: " + id + " bestaat niet");
    }
}