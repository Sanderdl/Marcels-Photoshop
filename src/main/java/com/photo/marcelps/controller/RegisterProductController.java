package com.photo.marcelps.controller;

import data.database.MySQLAlbumContext;
import data.database.MySQLExtrasContext;
import data.database.interfaces.IExtrasContext;
import logic.AlbumRepo;
import logic.CategoryRepo;
import logic.UploadRepo;
import models.*;
import models.exceptions.AlbumException;
import models.exceptions.UploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ruudv on 13-3-2017.
 */
@Controller
@RequestMapping("/registerproduct")
public class RegisterProductController {

    private IExtrasContext extrasContext = new MySQLExtrasContext();
    private MySQLAlbumContext albumContext = new MySQLAlbumContext();
    private UploadRepo uploadRepo = new UploadRepo();
    private AlbumRepo albumRepo = new AlbumRepo();
    private CategoryRepo categoryRepo = new CategoryRepo();
    private static final String ATTRIBUTE_MESSAGE = "message";

    @Autowired
    ServletContext context;

    /**
     * @param model,   Model
     * @param session, HttpSession
     * @param attr,    RedirectAttributes
     * @return String page
     */
    @RequestMapping(value = "/page/", method = RequestMethod.GET)
    public String setupPage(Model model, HttpSession session, RedirectAttributes attr) {
        if (session.getAttribute("User") instanceof Photographer) {

            ProductRegistration newProduct = new ProductRegistration();
            model.addAttribute("productregistration", newProduct);
            Album newAlbum = new Album();
            model.addAttribute("album", newAlbum);

            Photographer photographer = (Photographer) session.getAttribute("User");
            try {
                Collection<Extra> products = extrasContext.getAvailableExtras();
                model.addAttribute("availableProducts", products);

                Collection<Album> albums = albumContext.getAllAlbumsByUser(photographer);
                Map<Integer, String> album = new LinkedHashMap<>();

                for (Album a : albums) {
                    album.put(a.getId(), a.getName());
                }
                model.addAttribute("albums", album);

                Collection<AlbumCategory> categories = categoryRepo.getAllCategories();
                Map<Integer, String> category = new LinkedHashMap<>();

                Collection<User> users = this.uploadRepo.getAllUsers();
                Map<Integer, String> user = new LinkedHashMap<>();

                for (AlbumCategory a : categories) {
                    category.put(a.getCategoryId(), a.getCategoryName());
                }

                for (User u : users) {
                    user.put(u.getId(), u.getName());
                }

                model.addAttribute("categories", category);
                model.addAttribute("users", user);

            } catch (UploadException | AlbumException ex) {
                Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }

            return "registerproduct";
        }

        String message = "You need to be logged in as a verified photographer to register products.";
        attr.addFlashAttribute(ATTRIBUTE_MESSAGE, message);
        return "redirect:/login/page/";
    }

    /**
     * @param productRegistration, ProductRegistration
     * @param result,              BindingResult
     * @param model,               ModelMap
     * @param session,             HttpSession
     * @param attr,                RedirectAttribute
     * @return String page
     * @throws IOException, invalid picture
     */
    @RequestMapping(value = "/submit/", method = RequestMethod.POST)
    public String fileUpload(@ModelAttribute("productregistration") ProductRegistration productRegistration,
                             BindingResult result, ModelMap model, HttpSession session,
                             RedirectAttributes attr) throws IOException {
        String message;
        try {
            if (session.getAttribute("User") instanceof Photographer) {

                User user = (User) session.getAttribute("User");
                int imageID = this.uploadRepo.validateUpload(productRegistration, user);
                this.extrasContext.registerExtras(imageID, productRegistration.getProducts());
                this.uploadRepo.addSharedWith(imageID, productRegistration.getSharedWith());
                if (imageID == -1) {
                    message = "An error occurred while uploading the photo to the database.";
                    attr.addFlashAttribute(ATTRIBUTE_MESSAGE, message);
                    return "redirect:/registerproduct/page/";
                }
            }
        } catch (SQLException | UploadException e) {
            message = e.getMessage();
            attr.addFlashAttribute(ATTRIBUTE_MESSAGE, message);
            Logger.getLogger(RegisterProductController.class.getName()).log(Level.INFO, e.getMessage(), e);
            return "redirect:/registerproduct/page/";
        }
        return "redirect:/gallery/random/";
    }

    /**
     * @param album,   Album
     * @param session, HttpSession
     * @param result,  BindingResult
     * @param attr,    RedirectAttribute
     * @return String page
     * @throws IOException, invalid picture
     */
    @RequestMapping(value = "/modal/", method = RequestMethod.POST)
    public String fileUpload(@ModelAttribute("album") Album album, HttpSession session,
                             BindingResult result, RedirectAttributes attr) throws IOException {
        String message;
        try {
            User user = (User) session.getAttribute("User");
            album.setName(album.getName());
            albumRepo.validateUploadAlbum(album, user);
        } catch (UploadException e) {
            message = e.getMessage();
            attr.addFlashAttribute(ATTRIBUTE_MESSAGE, message);
            Logger.getLogger(RegisterProductController.class.getName()).log(Level.INFO, e.getMessage(), e);
        }
        return "redirect:/registerproduct/page/";
    }

}
