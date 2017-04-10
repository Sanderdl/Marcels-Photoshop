package com.photo.marcelps.controller;

import logic.AlbumRepo;
import models.Album;
import models.GalleryImage;
import models.exceptions.AlbumException;
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

/**
 * Created by sande on 10/04/2017.
 */
@Controller
@RequestMapping("album")
public class AlbumController {

    private AlbumRepo repo = new AlbumRepo();

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView setupPage(@RequestParam("id") int id, HttpSession session) {

        Map<Integer, GalleryImage> map = null;
        Album album = null;
        try {
            album = repo.retrieveAlbumById(id);
            map = repo.retrieveAlbumPictures(id);
        } catch (AlbumException e) {
            e.printStackTrace();
        }

        ModelAndView model = new ModelAndView("album");
        model.addObject("album",album);
        model.addObject("map", map);
        session.setAttribute("map",map);

        return model;
    }

    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public void showImage(@RequestParam("id") int id, HttpServletResponse response, HttpSession session)
            throws ServletException, IOException {

        Map<Integer, GalleryImage> map = (Map<Integer, GalleryImage>)session.getAttribute("map");
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(map.get(id).getImage());
        response.getOutputStream().close();
    }
}
