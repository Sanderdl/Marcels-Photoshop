package com.photo.marcelps.controller;

import models.Login;
import models.ProductRegistration;
import models.User;
import models.exceptions.LoginException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by ruudv on 13-3-2017.
 */
@Controller
@RequestMapping("/registerproduct")
public class RegisterProductController {

    @RequestMapping(value = "/page/", method = RequestMethod.GET)
    public String setupPage(Model model) {
        ProductRegistration user = new ProductRegistration();
        model.addAttribute("productregistration", user);
        ArrayList products = new ArrayList<String>();
        products.add("Mok");
        products.add("Shirt");
        model.addAttribute("availableProducts", products);
        return "registerproduct";
    }

    @RequestMapping(value = "/submit/", method = RequestMethod.POST)
    public ModelAndView registerUser(@ModelAttribute("productregistration") ProductRegistration productRegistration) {
        String message = null;

        try {

        } catch (Exception ex) {
            message = ex.getMessage();
        }

        ModelAndView model = new ModelAndView("registerproduct");
        model.addObject("message", message);
        return model;
    }
}
