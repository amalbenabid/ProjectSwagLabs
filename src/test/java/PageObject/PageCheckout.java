package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageCheckout {
    WebDriver driver;

    @FindBy(id = "checkout")
    WebElement Checkout;

    @FindBy(id ="first-name")
    WebElement FirstName;

    @FindBy(id="last-name")
    WebElement Lastname;

    @FindBy(id = "postal-code")
    WebElement PostalCode;

    @FindBy(id="continue")
    WebElement Continuer;

    @FindBy(id = "finish")
    WebElement Finish;

    @FindBy(id="back-to-products")
    WebElement BackToProductshome;


    public PageCheckout(WebDriver driver)
    {
        this.driver =  driver;
        PageFactory.initElements(driver, this);
    }

    public void setCheckout()
    {
        Checkout.click();
    }
    public void setFirstName(String firstName)
    {
        FirstName.sendKeys(firstName);
    }
    public  void setLastname(String lastname)
    {
        Lastname.sendKeys(lastname);
    }
    public void setPostalCode (String postalcode)
    {
        PostalCode.sendKeys(postalcode);
    }
    public void setContinuer()
    {
        Continuer.click();
    }
    public void setFinish()
    {
        Finish.click();
        String urlattendu = "https://www.saucedemo.com/checkout-complete.html";
        String urlactuelle = driver.getCurrentUrl();
        if(urlactuelle.equals(urlattendu))
        {
            System.out.println("Checkout completed");
        }
        else
        {
            System.out.println("Checkout not completed");
        }

    }
    public void setbackhome()
    {
        BackToProductshome.click();

    }
}
