package logic;

import models.Photographer;
import models.ProductRegistration;
import models.User;
import models.exceptions.UploadException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

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
        this.productRegistration.setPicture(multipartFile);
    }

    @Test
    public void uploadInvalidPictureTest() throws UploadException, SQLException {
        try {
            uploadRepo.validateUpload(productRegistration, user);
        } catch (final UploadException e) {
            final String msg = "The uploaded picture is empty, please select a picture.";
            assertEquals(msg, e.getMessage());
        }
    }

    @Test
    public void uploadInvalidPriceTooBigTest() throws UploadException, SQLException {
        try {
            this.productRegistration.setPrice(9999.00);
            uploadRepo.validateUpload(productRegistration, user);
        } catch (final UploadException e) {
            final String msg = "The price of your picture must be at least €0.00 and not above €1000.00";
            assertEquals(msg, e.getMessage());
        }
    }

    @Test
    public void uploadInvalidPriceTooLowTest() throws UploadException, SQLException {
        try {
            this.productRegistration.setPrice(0.00);
            uploadRepo.validateUpload(productRegistration, user);
        } catch (final UploadException e) {
            final String msg = "The price of your picture must be at least €0.00 and not above €1000.00";
            assertEquals(msg, e.getMessage());
        }
    }

    @Test
    public void uploadInvalidTitleTest() throws UploadException, SQLException {
        try {
            this.productRegistration.setTitle("");
            uploadRepo.validateUpload(productRegistration, user);
        } catch (final UploadException e) {
            final String msg = "The title of your picture must be between 1 and 50 characters long.";
            assertEquals(msg, e.getMessage());
        }
    }

}