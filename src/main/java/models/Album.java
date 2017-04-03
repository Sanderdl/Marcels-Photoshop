package models;

import edu.emory.mathcs.backport.java.util.Collections;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by lucreinink on 13/03/2017.
 */
public class Album {

    private String name;
    private Photographer owner;
    private int id;
    private MultipartFile thumbnail;

    // When selecting which categories a new album should belong to
    private int[] categories;

    // When loading an existing category and setting it's available categories.
    private Collection<AlbumCategory> categoryCollection = new ArrayList<AlbumCategory>();

    public Album(String name, Photographer owner, int id, int[] categories, MultipartFile picture ) {
        this.name = name;
        this.owner = owner;
        this.id = id;
        this.categories = categories;
        this.thumbnail = picture;
    }

    public Album(String name, Photographer owner, int id, Collection<AlbumCategory> categoryCollection){
        this.name = name;
        this.owner = owner;
        this.id = id;
        this.categoryCollection = categoryCollection;
    }

    public Album(String name, Photographer owner, int id){
        this.name = name;
        this.owner = owner;
        this.id = id;
    }



    public Album(){}

    public Collection<AlbumCategory> getCategoryList() {
        return Collections.unmodifiableCollection(categoryCollection);
    }

    public void setCategoryList(Collection<AlbumCategory> categoryList) {
        this.categoryCollection = categoryList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Photographer getOwner() {
        return owner;
    }

    public void setOwner(Photographer owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } 

    public MultipartFile getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(MultipartFile thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int[] getCategories() {
        return categories;
    }

    public void setCategories(int[] categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return name;
    }
}
