package fr.epsi.mspr.arosaje.exception.config;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Runtime exception par défaut pour lever des exceptions fonctionnelles.
 */
@ResponseStatus(BAD_REQUEST)
public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -4650567748718841516L;

    /**
     * Constructor
     *
     * @param message message
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * Constructor that allows to customize the returned message with a pattern using "Format String Syntax"
     *
     * @param message message with pattern
     * @param params  params for pattern
     * @see String#format
     */
    public BusinessException(String message, Object... params) {
        this(String.format(message, params));
    }

    /**
     * Constructor
     *
     * @param message   message
     * @param throwable exception
     */
    public BusinessException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Constructor that allows to customize the returned message with a pattern using "Format String Syntax"
     *
     * @param message   message with pattern
     * @param throwable exception
     * @param params    params for pattern
     * @see String#format
     */
    public BusinessException(String message, Throwable throwable, Object... params) {
        this(String.format(message, params), throwable);
    }
}
