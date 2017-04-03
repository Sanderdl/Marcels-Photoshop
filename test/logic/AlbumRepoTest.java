package logic;

import models.Album;
import models.Photographer;
import models.User;
import models.exceptions.UploadException;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

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
        this.album = new Album("TestAlbum", user, 1);
    }

    @Test
    public void validateAlbumNameTest() throws UploadException, SQLException {
        try {
            this.album.setName("");
            albumRepo.validateUploadAlbum(album, user);
        } catch (final UploadException e) {
            final String msg = "Invalid Album name";
            assertEquals(msg, e.getMessage());
        }
    }

}