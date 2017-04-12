package com.photo.marcelps.controller;

import logic.GalleryRepo;
import models.GalleryImage;
import models.User;
import models.exceptions.GalleryException;
import models.exceptions.PageOutOfBoundsException;
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
    public ModelAndView setupPage(@RequestParam("id") int id, @RequestParam("pageNumber") int pageNumber, HttpSession session) {

        if (((User) session.getAttribute("User")).getId() == id) {
            Map<Integer, GalleryImage> list = null;
            int maxPageCount = 1;
            try {
                maxPageCount = repo.getPrivatePageCount(id);
                list = repo.allSharedImages(id, pageNumber);
                session.setAttribute("Gallery", list);
            } catch (GalleryException|PageOutOfBoundsException e) {
                Logger.getLogger(SharedImagesController.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            }
            int[] nums = new int[]{1, 2, 3};

            //return back to index.jsp
            ModelAndView model = new ModelAndView("shared");
            model.addObject("lists", list);
            model.addObject("pageNumber", pageNumber);
            model.addObject("pageCount", maxPageCount);
            model.addObject("loopCount", nums);

            return model;
        } else {
            return null;
        }
    }
}
