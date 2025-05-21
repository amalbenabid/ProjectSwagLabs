package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class PageDeletePrd {
    WebDriver driver;

    @FindBy(id="remove-sauce-labs-onesie")
    WebElement removeArticle;


    public  PageDeletePrd(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void setverifyexistance() {
        String pagecontent = driver.findElement(By.tagName("html")).getText();
        List<String> pdtrecherche = new ArrayList<>();
        pdtrecherche.add("Sauce Labs Onesie");
        for (String mot : pdtrecherche) {
            if (pagecontent.contains(mot)) {
                System.out.println("le produit" + mot + "existe sur la page");

            }
        }
    }

    public void  setRemoveArticle()
    {
        removeArticle.click();
    }
}
