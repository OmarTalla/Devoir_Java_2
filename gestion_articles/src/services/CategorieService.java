package services;

import java.util.List;

import entities.Categorie;
import repositories.CategorieRepository;

public class CategorieService {
     private CategorieRepository categorieRepository=new CategorieRepository();

    public void ajouterCategorie(Categorie categorie){
        categorieRepository.insertCategorie(categorie);
    }

    public List<Categorie> listerCategories(){
          return categorieRepository.selectAllCategories();
    }

    public Categorie trouverCategorieParId(int id) {
        return categorieRepository.findById(id);
    }
}
