package services;
import java.util.List;
import entities.Article;
import repositories.ArticleRepository;

public class ArticleService {
    private ArticleRepository articleRepository=new ArticleRepository();

    public void ajouterarticle(Article article){
        articleRepository.insertArticle(article);
    }

    public List<Article> listerArticles(){
          return articleRepository.selectAllArticlesWithCategory();
    }
}
