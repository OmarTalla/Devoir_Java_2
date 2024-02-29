import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import entities.Categorie;
import entities.Article;
import services.CategorieService;
import services.ArticleService;
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CategorieService categorieService = new CategorieService();
        ArticleService articleService = new ArticleService();
        int choix;

        do {
            System.out.println("------------------------------------------------");
            System.out.println("|              GESTION DES ARTICLES            |");
            System.out.println("------------------------------------------------");
            System.out.println("1. Créer une Categorie");
            System.out.println("2. Lister les Catégories");
            System.out.println("3. Ajouter Article et l'associée à une catégorie");
            System.out.println("4. Lister les articles ainsi que la catégorie associée");
            System.out.println("5. Quitter");
            System.out.println("------------------------------------------------");
            System.out.print("Choisissez une option : ");
            choix = scanner.nextInt();
            scanner.nextLine(); 

            switch (choix) {
                case 1:
                System.out.println("------------------------------------------------");
                System.out.print("Entrer le nom de la Categorie : ");
                String nomCat = scanner.nextLine();
                Categorie categorie = new Categorie();
                categorie.setNomCat(nomCat);
                categorieService.ajouterCategorie(categorie);
                System.out.println("Categorie ajoutée avec succès.");
                    
                    break;
                case 2:
                    System.out.println("------------------------------------------------");
                    System.out.println("|                  LISTE DES CATEGORIES        |");
                    System.out.println("------------------------------------------------");
                    List<Categorie> categories = categorieService.listerCategories();
                    for (Categorie cat : categories) {
                        System.out.printf("| ID : %-40d |\n", cat.getId());
                        System.out.printf("| NOM : %-38s |\n", cat.getNomCat());
                        System.out.println("------------------------------------------------");
                    }
                    break;
                case 3:
                    System.out.println("------------------------------------------------");
                    System.out.print("Entrer le titre de l'Article : ");
                    String titre = scanner.nextLine();
                    
                    System.out.print("Entrer le contenu de l'Article : ");
                    String contenu = scanner.nextLine();
                    
                    System.out.print("Entrer l'état de l'Article : ");
                    String etat = scanner.nextLine();

                    categories = categorieService.listerCategories();
                    System.out.println("------------------------------------------------");
                    System.out.println("|                CHOISISSEZ UNE CATEGORIE      |");
                    System.out.println("------------------------------------------------");
                    for (Categorie cat : categories) {
                        System.out.printf("| %d - %s\n", cat.getId(), cat.getNomCat());
                    }
                    System.out.println("------------------------------------------------");
                    System.out.print("Entrer Id d'une Categorie : ");
                    int idCategorie = scanner.nextInt();
                    scanner.nextLine(); 
                    
                    categorie = categorieService.trouverCategorieParId(idCategorie);
                    
                    Article article = new Article();
                    article.setTitre(titre);
                    article.setContenu(contenu);
                    article.setEtat(etat);
                    article.setDateCreation(new Date(System.currentTimeMillis())); 
                    article.setCategorie(categorie);
                    
                    articleService. ajouterarticle(article);
                    System.out.println("Article ajouté avec succès et associé à la catégorie: " + categorie.getNomCat());
                    break;
                case 4:
                    System.out.println("------------------------------------------------");
                    System.out.println("|                LISTE DES ARTICLES            |");
                    System.out.println("------------------------------------------------");
                    List<Article> articles = articleService.listerArticles(); 
                    for (Article a : articles) {
                        System.out.println("| ID : " + a.getId());
                        System.out.println("| Titre : " + a.getTitre());
                        System.out.println("| Contenu : " + a.getContenu());
                        System.out.println("| Date Création : " + a.getDateCreation().toString());
                        System.out.println("| Catégorie : " + a.getCategorie().getNomCat());
                        System.out.println("| État : " + a.getEtat());
                        System.out.println("------------------------------------------------");
                    }
                
                    break;
                case 5:
                    System.out.println("Fin du programme.");
                    break;
                default:
                    System.out.println("Option non reconnue, veuillez réessayer.");
                    break;
            }
        } while (choix != 5);

        scanner.close();
    }
}
