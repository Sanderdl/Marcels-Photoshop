package models;

import org.apache.commons.lang.ObjectUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Adriaan on 03-Apr-17.
 */
public class AlbumTest {
    private Album inputAlbum;

    @Before
    public void setup(){

    }

    @Test
    public void albumConstructorTest() throws Exception {
        assertTrue(inputAlbum == null);
        MockyPartFile file = new MockyPartFile();
        inputAlbum = new Album("name", new Photographer(-1, "userName", "name", "email", User.UserStatus.verified),
                -1, new int[]{1,2}, file);

        assertTrue(inputAlbum.getId() == -1);
        inputAlbum.setId(1);
        assertTrue(inputAlbum.getId() == 1);

        assertTrue(inputAlbum.getName() == "name");
        inputAlbum.setName("foo");
        assertTrue(inputAlbum.getName() == "foo");

        assertTrue(inputAlbum.getOwner() != null);
        inputAlbum.setOwner(new Photographer(-1, "foo", "name", "email", User.UserStatus.verified));
        assertTrue(inputAlbum.getOwner().getUserName() == "foo");

        assertTrue(inputAlbum.getCategories()[0] ==  1);
        inputAlbum.setCategories(new int[]{2 , 1});
        assertTrue(inputAlbum.getCategories()[0] ==  2);

        assertTrue(inputAlbum.getThumbnail() != null);
        inputAlbum.setThumbnail(new MockyPartFile());

        inputAlbum.setCategoryList(new ArrayList<AlbumCategory>());
        assertTrue(inputAlbum.getCategoryList() != null);

    }


    public class MockyPartFile implements MultipartFile {

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