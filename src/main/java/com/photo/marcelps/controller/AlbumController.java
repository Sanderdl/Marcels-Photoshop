package com.photo.marcelps.controller;

import logic.AlbumRepo;
import models.GalleryImage;
import models.exceptions.AlbumException;
import models.exceptions.GalleryException;
import models.exceptions.UploadException;
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
 * Created by sande on 10/04/2017.
 */
@Controller
@RequestMapping("album")
public class AlbumController {

    private AlbumRepo repo = new AlbumRepo();

    /**
     *
     * @param id, integer
     * @param session, HttpSession
     * @return model, ModelAndView
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView showImage(@RequestParam("id") int id, HttpSession session) {

        Map<Integer, GalleryImage> map = null;

        try {
            map = repo.retrieveAlbumPictures(id);
        } catch (AlbumException|GalleryException|UploadException e) {
            Logger.getLogger(AlbumController.class.getName()).log(Level.INFO, e.getMessage(), e);
        }

        ModelAndView model = new ModelAndView("album");
        model.addObject("map", map);
        session.setAttribute("map",map);

        return model;
    }

    /**
     *
     * @param id, integer
     * @param response, HttpServletResponse
     * @param session, HttpSession
     * @throws ServletException, throws this exception
     * @throws IOException, throws this exception
     */
    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public void showImage(@RequestParam("id") int id, HttpServletResponse response, HttpSession session)
            throws ServletException, IOException {

        Map<Integer, GalleryImage> map = (Map<Integer, GalleryImage>)session.getAttribute("map");
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(map.get(id).getImage());
        response.getOutputStream().close();
    }
}
