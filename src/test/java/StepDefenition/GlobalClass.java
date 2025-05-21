package StepDefenition;

import PageObject.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class GlobalClass {

    WebDriver driver =  new ChromeDriver();
    LoginPage Login = new LoginPage(driver);
    PageAddPrd Produit = new PageAddPrd(driver);
    PageCheckout Checkout =  new PageCheckout(driver);
    PageDeletePrd DeletePrd = new PageDeletePrd(driver);
    PageLogout Logout = new PageLogout(driver);

    List<String> produits = List.of("Sauce Labs Onesie", "Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt");

    @Given("je suis sur le site swag labs")
    public void je_suis_sur_le_site_swag_labs() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();

    }

    @When("je tape le username")
    public void jeTapeLeUsername() {
        Login.setUsername("standard_user");

    }

    @And("je tape le passeword")
    public void jeTapeLePasseword() {
         Login.setPassword("secret_sauce");
    }

    @And("je clique sur le bouton login")
    public void jeCliqueSurLeBoutonLogin() {
     Login.setLoginButton();
    }

    @Then("je serais redigé vers la page home")
    public void jeSeraisRedigéVersLaPageHome() {
        String urlattendu = "https://www.saucedemo.com/inventory.html";
        String urlactuelle = driver.getCurrentUrl();
        if(urlactuelle.equals(urlattendu))
        {
            System.out.println("Connexion effectué");
        }
        else
        {
            System.out.println("Veuillez vérifier votre connexion");
        }


    }


    @And("je clique le menu liste pour sélectionner l'option")
    public void jeCliqueLeMenuListePourSélectionnerLOption() {
        Produit.setListe("Name (Z to A)");

    }

    @And("je clique sur le bouton add product")
    public void jeCliqueSurLeBoutonAddProduct() {
       // Produit.setAddToCart();
        Produit.ajouterProduitsAuPanier(produits);
    }

    @And("je vérifie le panier si le produit est ajouté")
    public void jeVérifieLePanierSiLeProduitEstAjouté() {
        Produit.setPanier();
    }

    @And("je supprime l'article ajouté")
    public void jeSupprimeLArticleAjouté() {
        DeletePrd.setRemoveArticle();
    }

    @And("je clique sur le bouton Chekout")
    public void jeCliqueSurLeBoutonChekout() {
        Checkout.setCheckout();
    }

    @And("je dois saisir firstname")
    public void jeDoisSaisirFirstname() {
        Checkout.setFirstName("Ben Abid");

    }

    @And("je dois saisir lastname")
    public void jeDoisSaisirLastname() {
        Checkout.setLastname("Amal");

    }

    @And("je dois saisir le code postal")
    public void jeDoisSaisirLeCodePostal() {
        Checkout.setPostalCode("4011");
    }

    @And("je clique sur le bouton continuer")
    public void jeCliqueSurLeBoutonContinuer() {
        Checkout.setContinuer();
    }



    @Then("je clique sur le bouton finish et je serais redirigé vers la page chekoutComplet")
    public void jeCliqueSurLeBoutonFinishEtJeSeraisRedirigéVersLaPageChekoutComplet() {
        Checkout.setFinish();
    }

    @And("je clique sur back home")
    public void jeCliqueSurBackHome() {
        Checkout.setbackhome();
    }

    @And("je clique sur le bouton menu")
    public void jeCliqueSurLeBoutonMenu() {
        Logout.setMenu();
        
    }

    @And("je clique sur le bouton logout")
    public void jeCliqueSurLeBoutonLogout() throws InterruptedException {
        Logout.setlogoutBtn();
    }

    @When("je suis pointé sur la page home")
    public void jeSuisPointéSurLaPageHome() {
        driver.get("https://www.saucedemo.com/inventory.html");
    }

    @And("j'accède sur le panier")
    public void jAccèdeSurLePanier() {
        Produit.setPanier();
    }

    @Then("vérifier l'existance du produit supprimé")
    public void vérifierLExistanceDuProduitSupprimé() {
        String pagecontent = driver.findElement(By.tagName("html")).getText();
        List<String> motrecherche = new ArrayList<>();
        motrecherche.add("Sauce Labs Onesie");
        for (String mot : motrecherche)
        {
            if(pagecontent.contains(mot))
            {
                System.out.println("le produit à supprimé" + mot + "existe sur la page");

            }else
            {
                System.out.println("le produità supprimé" + mot + "n'existe sur la page");
            }
        }
    }


    @Then("je serais reg=dirig vers l'interface de connexion")
    public void jeSeraisRegDirigVersLInterfaceDeConnexion() {
        {
            String urlattendu = "https://www.saucedemo.com/";
            String urlactuelle = driver.getCurrentUrl();
            if(urlactuelle.equals(urlattendu))
            {
                System.out.println("Logout successful");
            }
            else
            {
                System.out.println("Logout failed");
            }
        }
    }





   /* @And("je clique sur le bouton Continuer shopping")
    public void jeCliqueSurLeBoutonContinuerShopping() {
        Produit.setContinueShopping();
    }*/

    @Before("@logout")
    public void beforeScenario(){
        System.out.println("Before Scenario");
    }

   @After
    public void afterScenario() {
        /*System.out.println("After Scenario");*/
       /* driver.quit();*/
    }
}
