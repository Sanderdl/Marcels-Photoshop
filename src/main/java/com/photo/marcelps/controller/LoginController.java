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
 * Created by ruudv on 6-3-2017.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(value = "/page/", method = RequestMethod.GET)
    public ModelAndView getData() {

        ModelAndView model = new ModelAndView("login");
        return model;
    }

    @RequestMapping(value = "/submit/", method = RequestMethod.GET)
    public void getLoginData(@RequestParam String loginName) {
        User customer = new Customer(1, loginName);
    }
}
