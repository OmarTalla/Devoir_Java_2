package entities;

import java.util.ArrayList;
import java.util.List;

public class Categorie {
    private int id;
    private String nomCat;
    List<Categorie> categories=new ArrayList<>();
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNomCat() {
        return nomCat;
    }
    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }
    public List<Categorie> getCategories() {
        return categories;
    }
    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }

}
