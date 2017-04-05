package com.photo.marcelps.controller;

import data.database.MySQLAlbumContext;
import data.database.MySQLExtrasContext;
import data.database.interfaces.IExtrasContext;
import logic.ExtraRepo;
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
import org.springframework.web.bind.annotation.RequestParam;

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
    private ExtraRepo repo = new ExtraRepo(extrasContext);

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
            Extra extra = new Extra(0,extras.getExtraName(),extras.getPrice(), true);
            //repo.addExtra(extra);
        } catch (Exception e) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return "redirect:/admin/page/";
    }

    @RequestMapping(value = "/delete/", method = RequestMethod.POST)
    public String deleteExtra(@ModelAttribute("Extras") AdminExtra extras) {
        int[] extraIds = extras.getExtras();
        for (int id : extraIds){
            try{
                repo.deleteExtra(id);
            } catch (ExtraException e) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        }
        return "redirect:/admin/page/";
    }

    @RequestMapping(value = "/edit/")
    public String adjustExtra(@RequestParam("id") int ID, @RequestParam("price") double prize, @RequestParam("avail") boolean avail ) {
        try {
            repo.updateExtraPrice(ID, prize);
            repo.updateExtraAvailable(ID, avail);
        }catch (UploadException | ExtraException e){
            Logger.getLogger(AdminController.class.getName()).log(Level.INFO, e.getMessage(), e);
        }
        return "redirect:/admin/page/";
    }




}
