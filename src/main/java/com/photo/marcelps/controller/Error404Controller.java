package com.photo.marcelps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lucreinink on 10/04/2017.
 */
@Controller
@RequestMapping("/404")
public class Error404Controller {

    /**
     *
     * @return String page
     */
    @RequestMapping(value = "/page/", method = RequestMethod.GET)
    public String setupPage() {
        return "404";
    }
}
