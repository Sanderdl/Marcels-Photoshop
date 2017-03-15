package com.photo.marcelps.controller;

import data.database.interfaces.IAlbumContext;
import data.database.MySQLAlbumContext;
import models.GalleryImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;

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
    public void showImage(@RequestParam("id") Integer itemId, HttpServletResponse response)
            throws ServletException, IOException {

        GalleryImage gi = null;
        try {
            gi = gc.getImageById(itemId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(gi.getImage());

        response.getOutputStream().close();
    }

    @RequestMapping(value = "/random/", method = RequestMethod.GET)
    public ModelAndView getData() {

        HashSet<Integer> list = null;
        try {
            list = gc.allImages();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //return back to index.jsp
        ModelAndView model = new ModelAndView("index");
        model.addObject("lists", list);

        return model;

    }

}
