package com.photo.marcelps.controller;

import logic.LoginRepo;
import models.Login;
import models.User;
import models.exceptions.LoginException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;


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
    public String loginUser(@ModelAttribute("newLogin") Login login, RedirectAttributes attr, HttpSession session) {
        String message = null;
        try {
            User u = repo.UserLogin(login.getUsername(), login.getPassword());
            if (u != null) {
                session.setAttribute("User", u);
                System.out.println(u.getName());
                return "redirect:/gallery/random/";
            }
            message = "An error occured while retrieving the user.";
        } catch (LoginException ex) {
            message = ex.getMessage();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        attr.addFlashAttribute("message", message);
        return "redirect:/login/page/";
    }
}
