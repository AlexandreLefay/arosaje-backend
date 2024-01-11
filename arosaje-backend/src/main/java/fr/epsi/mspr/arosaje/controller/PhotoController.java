package fr.epsi.mspr.arosaje.controller;

import fr.epsi.mspr.arosaje.entity.Photo;
import fr.epsi.mspr.arosaje.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller for managing photos.
 */
@RestController
@RequestMapping("/api/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    /**
     * GET /api/photos : get all the photos.
     *
     * @return the list of photos.
     */
    @GetMapping
    public List<Photo> getAllPhotos() {
        return photoService.findAll();
    }
}