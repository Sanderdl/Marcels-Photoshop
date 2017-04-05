package com.photo.marcelps.controller;

import logic.LoginRepo;
import logic.UploadRepo;
import models.Login;
import models.User;
import models.exceptions.LoginException;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;


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
    public String loginUser(@ModelAttribute("newLogin") Login login, HttpServletRequest servletRequest, HttpServletResponse servletResponse, RedirectAttributes attr, HttpSession session, Model model) {
        String message = null;
        try {
            User u = repo.UserLogin(login.getUsername(), login.getPassword());
            if (u != null) {
                String locale = repo.getUserLanguage(u);
                LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(servletRequest);
                if (locale != null) {
                    localeResolver.setLocale(servletRequest, servletResponse, StringUtils.parseLocaleString(locale));
                } else {
                    localeResolver.setLocale(servletRequest, servletResponse, StringUtils.parseLocaleString("en"));
                }
                session.setAttribute("User", u);
                return "redirect:/gallery/random/";
            }
            message = "An error occured while retrieving the user.";
        } catch (LoginException ex) {
            message = ex.getMessage();
            Logger.getLogger(UploadRepo.class.getName()).log(Level.INFO, ex.getMessage(), ex);
        } catch (SQLException e) {
            Logger.getLogger(UploadRepo.class.getName()).log(Level.INFO, e.getMessage(), e);
        }
        model.addAttribute("message", message);
        return "login";
    }

    @RequestMapping(value= "/logout/", method = RequestMethod.GET)
    public String logoutUser(HttpSession session) {
        if (session!=null)
        {
            session.invalidate();
        }
        return "redirect:/login/page/";
    }

    @RequestMapping(value = "/language/", method = RequestMethod.GET)
    public void language(@RequestParam("lang") String lang, HttpServletRequest servletRequest, HttpServletResponse servletResponse, HttpSession session) throws IOException {
        String referer = servletRequest.getHeader("Referer");
        User u = (User) session.getAttribute("User");

        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(servletRequest);
        localeResolver.setLocale(servletRequest, servletResponse, StringUtils.parseLocaleString(lang));

        if (u != null) {
            try {
                repo.setUserLanguage(lang, u);
            } catch (SQLException e) {
                Logger.getLogger(UploadRepo.class.getName()).log(Level.INFO, e.getMessage(), e);
            } catch (LoginException e) {
                Logger.getLogger(UploadRepo.class.getName()).log(Level.INFO, e.getMessage(), e);
            }
        }
        servletResponse.sendRedirect(referer);
    }
}
