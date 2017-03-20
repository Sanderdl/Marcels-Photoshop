package logic;

import data.database.MySQLProductContext;
import models.ProductRegistration;
import models.exceptions.UploadException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by ruudv on 20-3-2017.
 */
public class UploadRepoTest {

    private UploadRepo uploadRepo;
    private ProductRegistration productRegistration;

    @Before
    public void setup() {
        uploadRepo = new UploadRepo();
        productRegistration = new ProductRegistration("Title", "0,99", MultipartFile picture, int[] products, int album, boolean isPublic);
    }

    @Test(expected = UploadException.class)
    public void uploadInvalidPictureTest() throws UploadException {
        this.registerRepo.registerUser(this.registration);
    }
}