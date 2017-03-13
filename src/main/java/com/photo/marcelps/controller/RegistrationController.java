package com.photo.marcelps.controller;

import logic.RegisterRepo;
import models.Registration;
import models.exceptions.InvalidRegisterException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Created by Tomt on 6-3-2017.
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private RegisterRepo repo;

    public RegistrationController(){
        repo = new RegisterRepo();
    }

    @RequestMapping(value = "/page/", method = RequestMethod.GET)
    public String setupPage( Model model) {
        Registration registration = new Registration();
        model.addAttribute("newAccount", registration);
        return "customerregistration";
    }

    @RequestMapping(value = "/register/", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("newAccount") Registration registration,  RedirectAttributes attr) {
        String message;

        try {
            repo.validateUsername(registration.getUserName());
            repo.validateEmail(registration.getEmail());
            repo.validatePassword(registration.getPassword());
            repo.validateUsername(registration.getName());
        }catch (InvalidRegisterException ex){
            message = ex.getMessage();
            attr.addFlashAttribute("message", message);
            return "redirect:/registration/page/";
        }
        return "redirect:/login/page/";
    }



}
