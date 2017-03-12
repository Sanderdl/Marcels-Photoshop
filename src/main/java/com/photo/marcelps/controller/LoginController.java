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
            @RequestParam("username") String username,
            @RequestParam("password") String password	) {

        // Prepare the response string
        String response = "Success";

        // Process the request
        try{
            repo.UserLogin(username, password);
        }
        catch(LoginException ex){
            // An error occurred, updating response.
            response = ex.getMessage();
        }
        return response;
    }

    // passing data to tempdata
    // http://stackoverflow.com/questions/31167613/access-current-model-in-spring-mvc
}
