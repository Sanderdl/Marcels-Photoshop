package com.photo.marcelps.controller;

import logic.GalleryRepo;
import models.GalleryImage;
import models.exceptions.GalleryException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by lucreinink on 29/03/2017.
 */
@Controller
@RequestMapping("viewphoto")
public class ViewPhotoController {

    private GalleryRepo imageRepo = new GalleryRepo();

    @RequestMapping(value = "/page/", method = RequestMethod.GET)
    public String setupPage(@RequestParam("id") int id, Model model, HttpSession session) {

        GalleryImage image = null;
        Map<Integer, GalleryImage> map = (Map<Integer, GalleryImage>) session.getAttribute("Gallery");
        if (map != null) {
            image = map.get(id);
        }

        if (image == null) {
            try {
                image = this.imageRepo.getImageById(id);
            } catch (GalleryException ex) {
                Logger.getLogger(ViewPhotoController.class.getName()).log(Level.INFO, ex.getMessage(), ex);
            }
        }

        model.addAttribute("image", image);
        model.addAttribute("imageBytes", DatatypeConverter.printBase64Binary(image.getImage()));

        return "viewphoto";
    }

}
