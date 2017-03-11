package com.photo.marcelps.controller;

import logic.LoginRepo;
import models.Customer;
import models.User;
import models.exceptions.LoginException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public ModelAndView getData() {

        ModelAndView model = new ModelAndView("login");
        return model;
    }

//    @RequestMapping(value = "/submit/", method = RequestMethod.GET)
//    public void getLoginData(@RequestParam String loginName) {
//        User customer = new Customer(1, loginName);
//    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public @ResponseBody String processAJAXRequest(
            @RequestParam("email") String email,
            @RequestParam("password") String password	) {

        String response = "";
        // Process the request
        // Prepare the response string

        // Check Email & Password
        if(password.length()<8){
            return response = "Password needs to be atleast 8 characters";
        }
        try{
            repo.UserLogin();
            response = "Inloggen gelukt";
        }
        catch(LoginException ex){
            response = "Helaas, er is iets misgegaan met het inloggen";
        }
        return response;
    }

    // passing data to tempdata
    // http://stackoverflow.com/questions/31167613/access-current-model-in-spring-mvc
}
