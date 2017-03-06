package com.photo.marcelps.controller;

import database.IGalleryContext;
import database.MySQLGalleryContext;
import models.GalleryImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sande on 02/03/2017.
 */
@Controller
@RequestMapping("/gallery")
public class ImageController {
    private IGalleryContext gc;

    public ImageController(){
        this.gc = new MySQLGalleryContext();
    }

    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public void showImage(@RequestParam("id") Integer itemId, HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException {


        GalleryImage gi = gc.getImageById(itemId);

        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(gi.getImage());


        response.getOutputStream().close();
    }

    @RequestMapping(value = "/random/", method = RequestMethod.GET)
    public ModelAndView getData() {

        List<Integer> list = gc.allImages();

        //return back to index.jsp
        ModelAndView model = new ModelAndView("index");
        model.addObject("lists", list);

        return model;

    }

    private List<String> getList() {

        List<String> list = new ArrayList<String>();
        list.add("List A");
        list.add("List B");
        list.add("List C");
        list.add("List D");
        list.add("List 1");
        list.add("List 2");
        list.add("List 3");

        return list;

    }

}
