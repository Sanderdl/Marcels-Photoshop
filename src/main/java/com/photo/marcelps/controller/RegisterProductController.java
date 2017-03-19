package com.photo.marcelps.controller;

import data.database.MySQLAlbumContext;
import data.database.MySQLExtrasContext;
import data.database.MySQLProductContext;
import data.database.interfaces.IExtrasContext;
import logic.UploadRepo;
import models.*;
import models.exceptions.UploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ruudv on 13-3-2017.
 */
@Controller
@RequestMapping("/registerproduct")
public class RegisterProductController {

    private IExtrasContext extrasContext = new MySQLExtrasContext();
    private MySQLAlbumContext albumContext = new MySQLAlbumContext();
    private UploadRepo uploadRepo = new UploadRepo();
    MySQLProductContext pc = new MySQLProductContext();

    @Autowired
    ServletContext context;

    @RequestMapping(value = "/page/", method = RequestMethod.GET)
    public String setupPage(Model model, HttpSession session) throws SQLException {
        ProductRegistration newProduct = new ProductRegistration();
        model.addAttribute("productregistration", newProduct);

        Collection<Extra> products = extrasContext.getAvailableExtras();
        model.addAttribute("availableProducts", products);

        Photographer photographer = (Photographer) session.getAttribute("User");

        Collection<Album> albums = albumContext.getAllAlbumsByUser(photographer);
        Map<Integer, String> album = new LinkedHashMap<Integer, String>();

        for (Album a : albums) {
            album.put(a.getId(), a.getName());
        }
        model.addAttribute("albums", album);
        return "registerproduct";
    }

    @RequestMapping(value = "/submit/", method = RequestMethod.POST)
    public String fileUpload(@ModelAttribute("productregistration") ProductRegistration productRegistration,
                             BindingResult result, ModelMap model, HttpSession session,
                             RedirectAttributes attr) throws IOException {
        String message = null;
        try {
            User user = (User) session.getAttribute("User");
            java.util.Date date = Calendar.getInstance().getTime();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            productRegistration.setDate(sqlDate);
            uploadRepo.validateUpload(productRegistration, user);
        } catch (SQLException e) {
            message = e.getMessage();
        } catch (UploadException e) {
            message = e.getMessage();
        }
        attr.addFlashAttribute("message", message);

        return "redirect:/login/page/";

    }

}
