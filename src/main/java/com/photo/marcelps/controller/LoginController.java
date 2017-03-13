package com.photo.marcelps.controller;

import logic.LoginRepo;
import models.Login;
import models.exceptions.LoginException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
    public String loginUser(@ModelAttribute("newLogin") Login login, RedirectAttributes attr) {
        String message = "blablabla";

        try {
            repo.UserLogin(login.getUsername(), login.getPassword());
            return "redirect:/gallery/random/";
        } catch (LoginException ex) {
            message = ex.getMessage();
        }

        attr.addFlashAttribute("message", message);
        return "redirect:/login/page/";
    }
}
