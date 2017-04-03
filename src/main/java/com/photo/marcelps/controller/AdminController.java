package com.photo.marcelps.controller;

import data.database.MySQLAlbumContext;
import data.database.MySQLExtrasContext;
import data.database.interfaces.IExtrasContext;
import models.Admin;
import models.AdminExtra;
import models.Extra;
import models.exceptions.UploadException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sande on 03/04/2017.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private IExtrasContext extrasContext = new MySQLExtrasContext();

    @RequestMapping(value = "/page/", method = RequestMethod.GET)
    public String setupPage(Model model, HttpSession session) {
        if (session.getAttribute("User") instanceof Admin) {
            AdminExtra extras = new AdminExtra();
            model.addAttribute("Extras", extras);
            try {
                Collection<Extra> products = extrasContext.getAvailableExtras();
                model.addAttribute("availableProducts", products);


            } catch (UploadException ex) {
                Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }

            return "admin";
        }

        return "redirect:/login/page/";
    }

    @RequestMapping(value = "/delete/", method = RequestMethod.POST)
    public String fileUpload(@ModelAttribute("productregistration") AdminExtra extras,
                             BindingResult result, ModelMap model, HttpSession session,
                             RedirectAttributes attr) throws IOException {

        return "redirect:/admin/page";
    }
}
