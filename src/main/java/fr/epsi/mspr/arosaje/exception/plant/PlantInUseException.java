package fr.epsi.mspr.arosaje.exception.plant;

import fr.epsi.mspr.arosaje.exception.config.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CONFLICT;

@ResponseStatus(CONFLICT)
public class PlantInUseException extends BusinessException {
    private static final String PLANT_IN_USE = "Plant with id %s is in use";

    public PlantInUseException(Long id) {
        super(PLANT_IN_USE, id);
    }
}
