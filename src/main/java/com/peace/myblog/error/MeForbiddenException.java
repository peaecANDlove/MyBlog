package com.peace.myblog.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author YR#
 * @create 2020-08-14 15:46
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class MeForbiddenException extends RuntimeException {

    public MeForbiddenException() {
        super();
    }

    public MeForbiddenException(String message) {
        super(message);
    }

    public MeForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }
}
