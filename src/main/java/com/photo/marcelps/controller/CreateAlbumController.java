package com.photo.marcelps.controller;

import data.database.MySQLRegistrationContext;
import logic.RegisterRepo;
import models.Album;
import models.Registration;
import models.exceptions.InvalidRegisterException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;


/**
 * Created by Tomt on 6=20-3-2017.
 */
@Controller
@RequestMapping("/createalbum")
public class CreateAlbumController {

    @RequestMapping(value = "/page/", method = RequestMethod.GET)
    public String setupPage( Model model) {
        Album album = new Album();
        model.addAttribute("newAlbum", album);
        return "createalbum";
    }
}
