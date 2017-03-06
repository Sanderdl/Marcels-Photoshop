package com.photo.marcelps.controller;

import models.Customer;
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
public class LoginController {

    @RequestMapping(value = "/login/", method = RequestMethod.POST)
    public Customer getLoginData(@RequestParam String loginName, @RequestParam String passWord) {
        Customer customer = new Customer(loginName, passWord);
        return customer;
    }
}
