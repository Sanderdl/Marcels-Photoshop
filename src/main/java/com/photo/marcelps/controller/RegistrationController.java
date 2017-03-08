package com.photo.marcelps.controller;

import logic.RegisterRepo;
import models.exceptions.InvalidRegisterException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


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
    public ModelAndView getData() {

        ModelAndView model = new ModelAndView("customerregistration");
        return model;
    }

    @RequestMapping(value = "/register/", method = RequestMethod.GET)
    public ModelAndView registerUser(@RequestParam ("inputUsername") String username,
                                     @RequestParam ("inputEmail") String email,
                                     @RequestParam("inputPassword") String password) {
        String message = null;

        try {
            repo.validateUsername(username);
            repo.validateEmail(email);
            repo.validatePassword(password);
        }catch (InvalidRegisterException ex){
            message = ex.getMessage();
        }

        ModelAndView model = new ModelAndView("customerregistration");
        model.addObject("message" , message);
        return model;
    }

}
