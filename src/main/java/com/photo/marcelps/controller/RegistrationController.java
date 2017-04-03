package com.photo.marcelps.controller;

import data.database.MySQLRegistrationContext;
import logic.RegisterRepo;
import models.Registration;
import models.exceptions.InvalidRegisterException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by Tomt on 6-3-2017.
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private RegisterRepo repo;

    public RegistrationController() {
        repo = new RegisterRepo(new MySQLRegistrationContext());
    }

    @RequestMapping(value = "/page/", method = RequestMethod.GET)
    public String setupPage(Model model) {
        Registration registration = new Registration();
        model.addAttribute("newAccount", registration);
        return "customerregistration";
    }

    @RequestMapping(value = "/register/", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("newAccount") Registration registration, RedirectAttributes attr, Model model) {
        String message;
        try {
            repo.registerUser(registration);
        } catch (InvalidRegisterException | SQLException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.INFO, ex.getMessage(), ex);
            message = ex.getMessage();
            model.addAttribute("message", message);
            return "customerregistration";
        }
        return "redirect:/login/page/";
    }
}
