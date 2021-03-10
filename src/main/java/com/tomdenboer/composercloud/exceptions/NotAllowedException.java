package com.tomdenboer.composercloud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class NotAllowedException extends RuntimeException{

    public NotAllowedException() {
        super("U bent niet gemachtigd voor deze handeling.");
    }
}
