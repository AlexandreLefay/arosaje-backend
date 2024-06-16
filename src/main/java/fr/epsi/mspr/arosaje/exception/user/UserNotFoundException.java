package fr.epsi.mspr.arosaje.exception.user;

import fr.epsi.mspr.arosaje.exception.config.BusinessException;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class UserNotFoundException extends BusinessException {
    private static final String USER_NOT_FOUND = "No user found with %s";

    public UserNotFoundException(Long id) {
        super(USER_NOT_FOUND, "id " + id);
    }

    public UserNotFoundException(String text, String type) {
        super(USER_NOT_FOUND, type + " " + text );
    }
}
