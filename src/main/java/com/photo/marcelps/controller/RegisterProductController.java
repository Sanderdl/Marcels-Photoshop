package com.photo.marcelps.controller;

import data.database.MySQLAlbumContext;
import data.database.MySQLExtrasContext;
import data.database.interfaces.IExtrasContext;
import logic.UploadRepo;
import models.Album;
import models.Photographer;
import models.ProductRegistration;
import models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by ruudv on 13-3-2017.
 */
@Controller
@RequestMapping("/registerproduct")
public class RegisterProductController {

    private IExtrasContext extrasContext = new MySQLExtrasContext();
    private MySQLAlbumContext albumContext = new MySQLAlbumContext();
    private UploadRepo uploadRepo = new UploadRepo();


    @RequestMapping(value = "/page/", method = RequestMethod.GET)
    public String setupPage(Model model, HttpSession session) throws SQLException {
        ProductRegistration newProduct = new ProductRegistration();
        model.addAttribute("productregistration", newProduct);

        Collection<String> products = extrasContext.getAvailableExtras();
        model.addAttribute("availableProducts", products);

        Photographer photographer = (Photographer) session.getAttribute("User");
        Collection<Album> albums = albumContext.getAllAlbumsByUser(photographer);

        model.addAttribute("albums", albums);
        return "registerproduct";
    }

    @RequestMapping(value = "/submit/", method = RequestMethod.POST)
    public ModelAndView registerUser(@ModelAttribute("productregistration") ProductRegistration productRegistration, HttpSession session) {
        String message = null;

        try {
            User user = (User) session.getAttribute("User");
            uploadRepo.validateUpload(productRegistration, user);
        } catch (Exception ex) {
            message = ex.getMessage();
        }

        ModelAndView model = new ModelAndView("registerproduct");
        model.addObject("message", message);
        return model;
    }

    /**
     * Upload single file using Spring Controller
     */
    @RequestMapping(value = "/upload/", method = RequestMethod.POST)
    public @ResponseBody
    String uploadFileHandler(@ModelAttribute("productregistration") ProductRegistration productRegistration) {

        if (!productRegistration.getPicture().isEmpty()) {
            try {
                byte[] bytes = productRegistration.getPicture().getBytes();

                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + productRegistration.getTitle());
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                System.out.println(("Server File Location="
                        + serverFile.getAbsolutePath()));

                return "You successfully uploaded file=" + productRegistration.getTitle();
            } catch (Exception e) {
                return "You failed to upload " + productRegistration.getTitle() + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + productRegistration.getTitle()
                    + " because the file was empty.";
        }
    }
}
