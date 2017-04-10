package com.photo.marcelps.controller;

import logic.GalleryRepo;
import models.GalleryImage;
import models.User;
import models.exceptions.GalleryException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by lucreinink on 10/04/2017.
 */
@Controller
@RequestMapping("/shared")
public class SharedImagesController {

    private GalleryRepo repo = new GalleryRepo();

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView setupPage(@RequestParam("id") int id, HttpSession session) {

        if (((User) session.getAttribute("User")).getId() == id) {
            Map<Integer, GalleryImage> list = null;
            int maxPageCount = 1;
            try {
                maxPageCount = repo.getPageCount();
                list = repo.allSharedImages(id);
                session.setAttribute("Gallery", list);
            } catch (GalleryException e) {
                Logger.getLogger(ImageController.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            }
            int[] nums = new int[]{1, 2, 3};

            //return back to index.jsp
            ModelAndView model = new ModelAndView("index");
            model.addObject("lists", list);
            model.addObject("pageNumber", 1);
            model.addObject("pageCount", maxPageCount);
            model.addObject("loopCount", nums);

            return model;
        } else {
            return null;
        }
    }
}
