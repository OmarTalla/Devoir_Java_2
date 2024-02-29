package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import core.Database;
import entities.Categorie;

public class CategorieRepository extends Database {
    private final String SQL_INSERT_CATEGORIE = "INSERT INTO `categorie` (`nomCat`) VALUES (?)";
    private final String SQL_SELECT_ALL_CATEGORIES = "SELECT * FROM `categorie`";

    public void insertCategorie(Categorie categorie) {
        openConnexion();
        try { 
            PreparedStatement ps = conn.prepareStatement(SQL_INSERT_CATEGORIE);
            ps.setString(1, categorie.getNomCat());
            ps.executeUpdate();
        closeConnexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Categorie> selectAllCategories() {
        List<Categorie> categories = new ArrayList<>();
        
        try {
            openConnexion();
            PreparedStatement ps = conn.prepareStatement(SQL_SELECT_ALL_CATEGORIES);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Categorie categorie = new Categorie();
                categorie.setId(rs.getInt("id_categorie"));
                categorie.setNomCat(rs.getString("nomCat"));
                categories.add(categorie);
            }
            rs.close();
           closeConnexion();
        } catch (SQLException e) {
            System.out.println("Erreur de Connexion a la BD");
        }
        return categories;
    }

     public Categorie findById(int id) {
        Categorie categorie = null;
        try {
            openConnexion();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM categorie WHERE id_categorie = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                categorie = new Categorie();
                categorie.setId(rs.getInt("id_categorie")); 
                categorie.setNomCat(rs.getString("nomCat"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnexion(); 
        }
        return categorie;
    }
    

}