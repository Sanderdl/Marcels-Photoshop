package com.photo.marcelps.controller;

import data.database.MySQLAlbumContext;
import data.database.interfaces.IAlbumContext;
import models.GalleryImage;
import models.exceptions.GalleryException;
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
    private IAlbumContext gc;

    public ImageController(){
        this.gc = new MySQLAlbumContext();
    }

    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public void showImage(@RequestParam("id") int id, HttpServletResponse response, HttpSession session)
            throws ServletException, IOException {

        Map<Integer, GalleryImage> map = (Map<Integer, GalleryImage>)session.getAttribute("Gallery");
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(map.get(id).getImage());
        response.getOutputStream().close();
    }

    @RequestMapping(value = "/random/", method = RequestMethod.GET)
    public ModelAndView getData(HttpSession session) {
        Map<Integer, GalleryImage> list = null;
        try
        {
            list = gc.allImages();
            session.setAttribute("Gallery", list);
        }
        catch (GalleryException e)
        {
            Logger.getLogger(ImageController.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        //return back to index.jsp
        ModelAndView model = new ModelAndView("index");
        model.addObject("lists", list);

        return model;

    }

}
