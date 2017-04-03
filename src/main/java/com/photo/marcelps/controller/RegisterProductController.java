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

    @Autowired
    ServletContext context;

    @RequestMapping(value = "/page/", method = RequestMethod.GET)
    public String setupPage(Model model, HttpSession session, RedirectAttributes attr) {
        if (session.getAttribute("User") instanceof Photographer) {

            ProductRegistration newProduct = new ProductRegistration();
            model.addAttribute("productregistration", newProduct);
            Album newAlbum = new Album();
            model.addAttribute("album", newAlbum    );

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
                Map<Integer, String> categorie = new LinkedHashMap<>();

                for (AlbumCategory a : categories) {
                    categorie.put(a.getCategoryId(), a.getCategoryName());
                }
                model.addAttribute("categories", categorie);
            } catch (UploadException | AlbumException ex) {
                Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }

            return "registerproduct";
        }

        String message = "You need to be logged in as a verified photographer to register products.";
        attr.addFlashAttribute("message", message);
        return "redirect:/login/page/";
    }

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
                if (imageID == -1) {
                    message = "An error occurred while uploading the photo to the database.";
                    attr.addFlashAttribute("message", message);
                    return "redirect:/registerproduct/page/";
                }
            }
        } catch (SQLException | UploadException e) {
            message = e.getMessage();
            attr.addFlashAttribute("message", message);
            Logger.getLogger(RegisterProductController.class.getName()).log(Level.INFO, e.getMessage(), e);
            return "redirect:/registerproduct/page/";
        }
        return "redirect:/gallery/random/";
    }

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
            attr.addFlashAttribute("message", message);
            Logger.getLogger(RegisterProductController.class.getName()).log(Level.INFO, e.getMessage(), e);
        }
        return "redirect:/registerproduct/page/";
    }

}
