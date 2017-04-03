package logic;

import data.database.interfaces.ICategoryContext;
import data.database.interfaces.ILoginContext;
import models.Album;
import models.AlbumCategory;
import models.Customer;
import models.User;
import models.exceptions.AlbumException;
import models.exceptions.LoginException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Riccardo on 3/4/2017.
 */
public class CategoryRepoTest
{
    private CategoryRepo repo;

    @Before
    public void setUp() throws Exception
    {
        repo = new CategoryRepo(new TestCatContext());
    }

    @Test
    public void getAllCategories() throws Exception
    {
        Collection<AlbumCategory> result = repo.getAllCategories();
        assertEquals(4, result.size());
    }

    @Test
    public void addAndGetCategoryForAlbum() throws Exception
    {
        List<AlbumCategory> tempList = (List)repo.getAllCategories();
        repo.addCategoryToAlbum(tempList, 2);
        Collection<AlbumCategory> result = repo.getCategoryForAlbum(2);
        int i = 0;
        for (AlbumCategory ac: result)
        {
            assertTrue(tempList.get(i).getCategoryName() == ac.getCategoryName());
            i++;
        }

    }


    class TestCatContext implements ICategoryContext
    {
        private List<Album> albumList = new ArrayList<Album>();
        private List<AlbumCategory> categoryList = new ArrayList<AlbumCategory>();

        public TestCatContext()
        {
            albumList.add(new Album());
            albumList.add(new Album());
            albumList.add(new Album());
            albumList.add(new Album());
            albumList.add(new Album());

            categoryList.add(new AlbumCategory(1, "1"));
            categoryList.add(new AlbumCategory(2, "2"));
            categoryList.add(new AlbumCategory(3, "3"));
            categoryList.add(new AlbumCategory(4, "4"));
        }

        @Override
        public Collection<AlbumCategory> getAllCategories() throws AlbumException
        {
            return categoryList;
        }

        @Override
        public Collection<AlbumCategory> getCategoryForAlbum(int albumId) throws AlbumException
        {

            return albumList.get(albumId).getCategoryList();
        }

        @Override
        public void addCategoryToAlbum(Collection<AlbumCategory> albumList, int albumId) throws AlbumException
        {
            this.albumList.get(albumId).setCategoryList(albumList);
        }
    }

}