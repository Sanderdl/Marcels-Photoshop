package com.photo.marcelps.controller;

import models.Customer;
import models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * Created by Tomt on 6-3-2017.
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @RequestMapping(value = "/page/", method = RequestMethod.GET)
    public ModelAndView getData() {

        ModelAndView model = new ModelAndView("customerregistration");
        return model;
    }

}
