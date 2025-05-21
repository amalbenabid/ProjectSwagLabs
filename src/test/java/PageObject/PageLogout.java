package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageLogout {
    WebDriver driver;

    @FindBy(id = "react-burger-menu-btn")
    WebElement logoutMenu;
    @FindBy(id = "logout_sidebar_link")
    WebElement logoutBtn;

    public PageLogout(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void setMenu()
    {
        logoutMenu.click();
    }
    public void setlogoutBtn()
    {
        logoutBtn.click();
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
