package fr.epsi.mspr.arosaje.exception.plant;

import fr.epsi.mspr.arosaje.exception.config.BusinessException;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class PlantNotFoundException extends BusinessException {
    private static final String PLANT_NOT_FOUND = "No plant found with id %s";

    public PlantNotFoundException(Long id) {
            super(PLANT_NOT_FOUND, id);
        }
}
