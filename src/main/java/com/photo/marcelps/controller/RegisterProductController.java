package com.photo.marcelps.controller;

import data.database.MySQLAlbumContext;
import data.database.MySQLExtrasContext;
import data.database.interfaces.IExtrasContext;
import models.Album;
import models.Photographer;
import models.ProductRegistration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by ruudv on 13-3-2017.
 */
@Controller
@RequestMapping("/registerproduct")
public class RegisterProductController {

    private IExtrasContext extrasContext = new MySQLExtrasContext();
    private MySQLAlbumContext albumContext = new MySQLAlbumContext();

    @RequestMapping(value = "/page/", method = RequestMethod.GET)
    public String setupPage(Model model, HttpSession session) throws SQLException {
        ProductRegistration newProduct = new ProductRegistration();
        model.addAttribute("productregistration", newProduct);

        Collection<String> products = extrasContext.getAvailableExtras();
        model.addAttribute("availableProducts", products);

        Photographer photographer = (Photographer) session.getAttribute("User");
        Collection<Album> albums = albumContext.getAllAlbumsByUser(photographer);

        model.addAttribute("albums", albums);
        return "registerproduct";
    }

    @RequestMapping(value = "/submit/", method = RequestMethod.POST)
    public ModelAndView registerUser(@ModelAttribute("productregistration") ProductRegistration productRegistration) {
        String message = null;

        try {

        } catch (Exception ex) {
            message = ex.getMessage();
        }

        ModelAndView model = new ModelAndView("registerproduct");
        model.addObject("message", message);
        return model;
    }
}
