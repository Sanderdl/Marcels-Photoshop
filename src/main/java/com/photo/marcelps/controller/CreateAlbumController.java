package com.photo.marcelps.controller;

import data.database.MySQLAlbumContext;
import data.database.MySQLRegistrationContext;
import data.database.interfaces.IAlbumContext;
import logic.CreateAlbumRepo;
import logic.RegisterRepo;
import models.Album;
import models.Registration;
import models.User;
import models.exceptions.InvalidRegisterException;
import models.exceptions.UploadException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


/**
 * Created by Tomt on 6=20-3-2017.
 */
@Controller
@RequestMapping("/createalbum")
public class CreateAlbumController {

    private CreateAlbumRepo albumRepo;

    public CreateAlbumController()
    {
        albumRepo= new CreateAlbumRepo();
    }

    @RequestMapping(value = "/page/", method = RequestMethod.GET)
    public String setupPage( Model model) {
        Album album = new Album();
        model.addAttribute("newAlbum", album);
        return "createalbum";
    }

    @RequestMapping(value = "/submit/", method = RequestMethod.POST)
    public String uploadAlbum(@ModelAttribute("newAlbum") Album album,  RedirectAttributes attr, HttpSession session) {
        String message = null;
        try {
            User user = (User) session.getAttribute("User");
            albumRepo.validateUploadAlbum(album, user);
        } catch (SQLException e)
        {
            message = e.getMessage();
        }
        catch (UploadException e)
        {
            message = e.getMessage();
        }
        attr.addFlashAttribute("message", message);
        return "redirect:/registerproduct/page/";
    }
}
