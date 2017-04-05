package com.photo.marcelps.controller;

import logic.GalleryRepo;
import models.GalleryImage;
import models.exceptions.GalleryException;
import models.exceptions.PageOutOfBoundsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sande on 02/03/2017.
 */
@Controller
@RequestMapping("/gallery")
public class ImageController {
    private GalleryRepo repo = new GalleryRepo();

    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public void showImage(@RequestParam("id") int id, HttpServletResponse response, HttpSession session)
            throws ServletException, IOException {

        Map<Integer, GalleryImage> map = (Map<Integer, GalleryImage>)session.getAttribute("Gallery");
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(map.get(id).getImage());
        response.getOutputStream().close();
    }

    @RequestMapping(value = "/random/", method = RequestMethod.GET)
    public ModelAndView getData(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber, HttpSession session) {
        Map<Integer, GalleryImage> list = null;
        int maxPageCount = 1;
        try
        {
            maxPageCount = repo.getPageCount();
            list = repo.allImages(pageNumber);
            session.setAttribute("Gallery", list);
        }
        catch (GalleryException e)
        {
            Logger.getLogger(ImageController.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        catch(PageOutOfBoundsException e){
            Logger.getLogger(ImageController.class.getName()).log(Level.INFO, e.getMessage(), e);
            return new ModelAndView("redirect:/gallery/random/");
        }
        int[] nums = new int[3];
        nums[0] = 1;
        nums[1] = 2;
        nums[2] = 3;

        //return back to index.jsp
        ModelAndView model = new ModelAndView("index");
        model.addObject("lists", list);
        model.addObject("pageNumber", pageNumber);
        model.addObject("pageCount", maxPageCount);
        model.addObject("loopCount", nums);

        return model;

    }

}
