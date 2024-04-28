package POM;

import AbstractComponent.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
    WebDriver driver;

    public LandingPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement userPass;

    @FindBy(id="login")
    WebElement submit;

    @FindBy(css="[class*='flyInOut']")
    WebElement errorMessage;

    public void getLandingPageURL(){
        driver.get("https://rahulshettyacademy.com/client/");
    }

    public CatalogPage loginApplication(String email, String password){
        userEmail.sendKeys(email);
        userPass.sendKeys(password);
        submit.click();
        CatalogPage catalogPage=new CatalogPage(driver);
        return catalogPage;
    }
    public String getErrorMessage(){
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();

    }
}
