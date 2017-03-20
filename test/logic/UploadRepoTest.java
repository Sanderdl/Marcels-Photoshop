package logic;

import data.database.MySQLProductContext;
import models.Photographer;
import models.ProductRegistration;
import models.User;
import models.exceptions.UploadException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.springframework.mock.web.MockMultipartFile;

import static org.junit.Assert.assertEquals;

/**
 * Created by ruudv on 20-3-2017.
 */
public class UploadRepoTest {

    private UploadRepo uploadRepo;
    private ProductRegistration productRegistration;
    private Photographer user;
    private MultipartFile multipartFile;

    @Before
    public void setup() {
        this.uploadRepo = new UploadRepo();
        this.user = new Photographer(1, "Fred", "Fred Fransen", "Fred@Fred.nl", User.UserStatus.verified);

        Path path = Paths.get(".gitignore");
        String name = "file.txt";
        String originalFileName = "file.txt";
        String contentType = "text/plain";
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }
        multipartFile = new MockMultipartFile(name,
                originalFileName, contentType, content);

        this.productRegistration = new ProductRegistration("Title", 0.99, multipartFile, new int[]{1, 2}, true);
    }

    @Test
    public void uploadInvalidPictureTest() throws UploadException, SQLException {
        try {
            this.productRegistration.setPicture(null);
            uploadRepo.validateUpload(productRegistration, user);
        } catch (final UploadException e) {
            final String msg = "Invalid photo";
            assertEquals(msg, e.getMessage());
        }
    }

    @Test
    public void uploadInvalidPriceTooBigTest() throws UploadException, SQLException {
        try {
            this.productRegistration.setPrice(9999.00);
            uploadRepo.validateUpload(productRegistration, user);
        } catch (final UploadException e) {
            final String msg = "Price must be between €0.00 and €1000.00";
            assertEquals(msg, e.getMessage());
        }
    }

    @Test
    public void uploadInvalidPriceTooLowTest() throws UploadException, SQLException {
        try {
            this.productRegistration.setPrice(0.00);
            uploadRepo.validateUpload(productRegistration, user);
        } catch (final UploadException e) {
            final String msg = "Price must be between €0.00 and €1000.00";
            assertEquals(msg, e.getMessage());
        }
    }

    @Test
    public void uploadInvalidTitleTest() throws UploadException, SQLException {
        try {
            this.productRegistration.setTitle("");
            uploadRepo.validateUpload(productRegistration, user);
        } catch (final UploadException e) {
            final String msg = "Invalid name length";
            assertEquals(msg, e.getMessage());
        }
    }
}