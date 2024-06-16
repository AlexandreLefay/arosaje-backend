package fr.epsi.mspr.arosaje.exception.guardianship;

import fr.epsi.mspr.arosaje.exception.config.BusinessException;

public class GuardianshipNotFoundException extends BusinessException {
    private static final String GUARDIANSHIP_NOT_FOUND = "No guardianship found with id %s";

    public GuardianshipNotFoundException(Long id) {
            super(GUARDIANSHIP_NOT_FOUND, id);
        }
    }
