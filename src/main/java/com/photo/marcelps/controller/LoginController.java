package com.photo.marcelps.controller;

import logic.LoginRepo;
import models.Customer;
import models.Login;
import models.Registration;
import models.User;
import models.exceptions.InvalidRegisterException;
import models.exceptions.LoginException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * Created by ruudv on 6-3-2017.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    private LoginRepo repo = new LoginRepo();

    @RequestMapping(value = "/page/", method = RequestMethod.GET)
    public String setupPage(Model model) {
        Login login = new Login();
        model.addAttribute("newLogin", login);
        return "login";
    }

    @RequestMapping(value = "/submit/", method = RequestMethod.POST)
    public ModelAndView registerUser(@ModelAttribute("newAccount") Login login) {
        String message = null;

        try {
            repo.UserLogin(login.getUsername(), login.getPassword());
        } catch (LoginException ex) {
            message = ex.getMessage();
        }

        ModelAndView model = new ModelAndView("login");
        model.addObject("message", message);
        return model;
    }
}
