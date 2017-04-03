package com.photo.marcelps.controller;

import data.database.MySQLAlbumContext;
import data.database.MySQLExtrasContext;
import data.database.interfaces.IExtrasContext;
import models.Admin;
import models.AdminExtra;
import models.Extra;
import models.exceptions.ExtraException;
import models.exceptions.UploadException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
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

    @RequestMapping(value = "/add/", method = RequestMethod.POST)
    public String addExtra(@ModelAttribute("Extras") AdminExtra extras){
        try {
            extrasContext.addNewExtraProduct(extras.getExtraName(),extras.getPrice(),true);
        } catch (UploadException e) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return "redirect:/admin/page/";
    }

    @RequestMapping(value = "/delete/", method = RequestMethod.POST)
    public String deleteExtra(@ModelAttribute("Extras") AdminExtra extras) {
        int[] extraIds = extras.getExtras();
        for (int id : extraIds){
            try{
                extrasContext.deleteExtra(id);
            } catch (ExtraException e) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        }
        return "admin";
    }
}
