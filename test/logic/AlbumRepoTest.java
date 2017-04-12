package logic;

import models.Album;
import models.Photographer;
import models.User;
import models.exceptions.UploadException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * Created by ruudv on 20-3-2017.
 */
public class AlbumRepoTest {

    private AlbumRepo albumRepo;
    private Photographer user;
    private Album album;

    @Before
    public void setup() {
        this.albumRepo = new AlbumRepo();
        this.user = new Photographer(1, "Fred", "Fred Fransen", "Fred@Fred.nl", User.UserStatus.verified);
        this.album = new Album("TestAlbum", user, 1,new int[2], new MockyPartFile());
    }

    @Test
    public void validateAlbumNameTest() throws UploadException, SQLException {
        try {
            this.album.setName("");
            this.albumRepo.validateUploadAlbum(album, user);
        } catch (final UploadException e) {
            final String msg = "The title of your album must be between 1 and 50 characters long.";
            assertEquals(msg, e.getMessage());
        }
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