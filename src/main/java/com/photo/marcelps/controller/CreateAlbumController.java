package com.photo.marcelps.controller;

import logic.AlbumRepo;
import models.Album;
import models.Photographer;
import models.User;
import models.exceptions.UploadException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by Tomt on 6=20-3-2017.
 */
@Controller
@RequestMapping("/createalbum")
public class CreateAlbumController {

    private AlbumRepo albumRepo;
    private static final String ATTRIBUTE_MESSAGE = "Message";
    /**
     * Constructor of this class
     */
    public CreateAlbumController() {
        albumRepo = new AlbumRepo();
    }

    /**
     *
     * @param model A Model
     * @param session A HttpSession
     * @param attr RedirectAttributes. Used to redirect to the desired page
     * @return String page
     */
    @RequestMapping(value = "/page/", method = RequestMethod.GET)
    public String setupPage(Model model, HttpSession session, RedirectAttributes attr) {
        if (session.getAttribute("User") instanceof Photographer) {
            Album album = new Album();
            model.addAttribute("newAlbum", album);
            return "createalbum";
        }
        String message = "You need to be logged in as a verified photographer to create an album";
        attr.addFlashAttribute(ATTRIBUTE_MESSAGE, message);
        return "redirect:/login/page/";
    }

    /**
     *
     * @param album An album. Used to upload an new album
     * @param attr RedirectAttributes. Used to redirect to the desired page
     * @param session A HttpSession. Used to retrieve the logged in user
     * @return String page
     */
    @RequestMapping(value = "/submit/", method = RequestMethod.POST)
    public String uploadAlbum(@ModelAttribute("newAlbum") Album album, RedirectAttributes attr, HttpSession session) {

        String message;
        if (!album.getName().isEmpty() && !"".equalsIgnoreCase(album.getName()) && !" ".equalsIgnoreCase(album.getName())) {
            try {
                User user = (User) session.getAttribute("User");
                albumRepo.validateUploadAlbum(album, user);
                message = "Your album has been successfully added.";
                attr.addFlashAttribute("message-success", message);
            } catch (UploadException e) {
                message = e.getMessage();
                Logger.getLogger(CreateAlbumController.class.getName()).log(Level.INFO, e.getMessage(), e);
                attr.addFlashAttribute(ATTRIBUTE_MESSAGE, message);
            }
        } else {
            message = "please enter a valid album name.";
            attr.addFlashAttribute(ATTRIBUTE_MESSAGE, message);
        }

        return "redirect:/createalbum/page/";
    }
}
