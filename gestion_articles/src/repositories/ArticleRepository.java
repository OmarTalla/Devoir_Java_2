package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import core.Database;
import entities.Article;
import entities.Categorie;

public class ArticleRepository extends Database {
    private final String SQL_INSERT_ARTICLE = "INSERT INTO `article` (`titre`, `contenu`, `dateCreation`, `etat`, `id_categorie`) VALUES (?, ?, ?, ?, ?)";
    private final String SQL_SELECT_ALL_ARTICLES = "SELECT a.*, c.nomCat FROM article a INNER JOIN categorie c ON a.id_categorie = c.id_categorie";

    public void insertArticle(Article article) {
        openConnexion();
        try {
            PreparedStatement ps = conn.prepareStatement(SQL_INSERT_ARTICLE);
            ps.setString(1, article.getTitre());
            ps.setString(2, article.getContenu());
            ps.setTimestamp(3, new java.sql.Timestamp(article.getDateCreation().getTime()));
            ps.setString(4, article.getEtat());
            ps.setInt(5, article.getCategorie().getId());
            ps.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnexion();
        }
    }
    
    
    public List<Article> selectAllArticlesWithCategory() {
        List<Article> articles = new ArrayList<>();
        try {
            openConnexion();
            PreparedStatement ps = conn.prepareStatement(SQL_SELECT_ALL_ARTICLES);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Article article = new Article();
                Categorie categorie = new Categorie();
                
                article.setId(rs.getInt("id_article"));
                article.setTitre(rs.getString("titre"));
                article.setContenu(rs.getString("contenu"));
                article.setDateCreation(rs.getDate("dateCreation"));
                article.setEtat(rs.getString("etat"));
                
                categorie.setId(rs.getInt("id_categorie"));
                categorie.setNomCat(rs.getString("nomCat"));
                
                article.setCategorie(categorie);
                
                articles.add(article);
            }
            rs.close();
            closeConnexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }
}
