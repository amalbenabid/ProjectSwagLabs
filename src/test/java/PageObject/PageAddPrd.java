package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class PageAddPrd {
    WebDriver driver;

    @FindBy(className = "product_sort_container")
    WebElement Liste;

    /*@FindBy(id = "add-to-cart-sauce-labs-onesie")
    WebElement AddToCart;*/

    @FindBy(className = "shopping_cart_link")
    WebElement Panier;

   /* @FindBy(id = "continue-shopping")
    WebElement ContinueShopping;*/

    public PageAddPrd(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
   public  void setListe(String choix)
   {
    Select listeOption = new Select(Liste);
    listeOption.selectByVisibleText(choix);
   }

  /* public void setAddToCart()
   {
       AddToCart.click();
   }*/
  public void ajouterProduitsAuPanier(List<String> nomsProduits) {
      for (String produit : nomsProduits) {
          try {
              // Génère un identifiant dynamique pour trouver le bouton "Add to Cart"
              String idBouton = "add-to-cart-" + produit.toLowerCase().replace(" ", "-");
              WebElement boutonAjouter = driver.findElement(By.id(idBouton));
              boutonAjouter.click();
              System.out.println("Produit ajouté au panier : " + produit);
          } catch (Exception e) {
              System.err.println("Erreur lors de l'ajout du produit : " + produit + ". Vérifiez l'identifiant.");
          }
      }
  }
   public void setPanier()
   {
       Panier.click();
    String pagecontent = driver.findElement(By.tagName("html")).getText();
       List<String> motrecherche = new ArrayList<>();
       motrecherche.add("Sauce Labs Onesie");
       for (String mot : motrecherche)
       {
           if(pagecontent.contains(mot))
           {
               System.out.println("le produit" + mot + "existe sur la page");

           }else
           {
               System.out.println("le produit" + mot + "n'existe sur la page");
           }
       }
   }
   /*public void setContinueShopping()
   {
       ContinueShopping.click();
   }*/
}
