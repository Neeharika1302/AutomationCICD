package POM;

import AbstractComponent.AbstractComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }
    @FindBy(css="[placeholder='Select Country']")
    WebElement countryField;
    @FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
    WebElement country;
    @FindBy(css=".action__submit")
    WebElement placeOrderButton;

    By countryResult= By.cssSelector(".ta-results");

    public void selectCountry(){
        Actions a=new Actions(driver);
        a.moveToElement(countryField).sendKeys(Keys.ENTER).sendKeys(countryField,"India").build().perform();
        waitForElementToAppear(countryResult);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",country);
    }
    public SuccessPage placeOrder(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",placeOrderButton);
        SuccessPage successPage=new SuccessPage(driver);
        return successPage;
    }
}
