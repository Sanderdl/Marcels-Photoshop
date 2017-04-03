package models;

import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

import static org.junit.Assert.*;

/**
 * Created by Adriaan on 03-Apr-17.
 */
public class ProductRegistrationTest {
    ProductRegistration pr;

    @Test
    public void productRegistrationTest(){
        assertTrue(pr == null);
        MultipartFile mocky = new MockyPartFile();
        pr = new ProductRegistration("image", 2.00, mocky, new int[]{1,2}, true);

        assertTrue(pr.getTitle() == "image");
        pr.setTitle("foo");
        assertTrue(pr.getTitle() == "foo");

        assertTrue(pr.getPrice() == 2.00);
        pr.setPrice(1.00);
        assertTrue(pr.getPrice() == 1.00);

        assertTrue(pr.getPicture() != null);
        pr.setPicture(mocky);

        assertTrue(pr.getProducts()[0] == 1);
        pr.setProducts(new int[]{2,1});
        assertTrue(pr.getProducts()[0] == 2);

        assertTrue(pr.getIsPublic() == true);
        pr.setIsPublic(false);
        assertTrue(pr.getIsPublic() == false);

        assertTrue(pr.getDate() == null);
        pr.setDate(new Date(3313));
        assertTrue(pr.getDate().toString().equals("1970-01-01"));

        assertTrue(pr.toString().contains("ProductRegistration{"));
    }

    public class MockyPartFile implements MultipartFile{

        @Override
        public String getName() {
            return null;
        }

        @Override
        public String getOriginalFilename() {
            return null;
        }

        @Override
        public String getContentType() {
            return null;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public long getSize() {
            return 0;
        }

        @Override
        public byte[] getBytes() throws IOException {
            return new byte[0];
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return null;
        }

        @Override
        public void transferTo(File file) throws IOException, IllegalStateException {

        }
    }

}