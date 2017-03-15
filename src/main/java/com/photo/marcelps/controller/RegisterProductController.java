package com.photo.marcelps.controller;

import data.database.MySQLAlbumContext;
import data.database.MySQLExtrasContext;
import data.database.MySQLUploadContext;
import data.database.interfaces.IExtrasContext;
import data.database.interfaces.IUploadContext;
import logic.UploadRepo;
import models.Album;
import models.Photographer;
import models.ProductRegistration;
import models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
<<<<<<< Updated upstream
import java.sql.Blob;
import java.sql.Date;
=======
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
>>>>>>> Stashed changes
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * Created by ruudv on 13-3-2017.
 */
@Controller
@RequestMapping("/registerproduct")
public class RegisterProductController {

    private IExtrasContext extrasContext = new MySQLExtrasContext();
    private MySQLAlbumContext albumContext = new MySQLAlbumContext();
<<<<<<< Updated upstream
    private UploadRepo uploadRepo = new UploadRepo();
=======
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "F://temp//";
>>>>>>> Stashed changes

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
