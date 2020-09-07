package com.peace.myblog.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author YR#
 * @create 2020-08-10 18:39
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MeNotFoundException extends RuntimeException {

    public MeNotFoundException() {
    }

    public MeNotFoundException(String message) {
        super(message);
    }

    public MeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
