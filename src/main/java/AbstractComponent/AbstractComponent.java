package AbstractComponent;

import POM.CartPage;
import POM.CheckoutPage;
import POM.OrdersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {
    WebDriver driver;
    public AbstractComponent(WebDriver driver) {
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = "[routerlink*='/cart']")
    WebElement cartButton;

    @FindBy(css=".totalRow button")
    WebElement checkoutButton;
    By toastMessage=By.cssSelector("#toast-container");

    @FindBy(css="[routerlink='/dashboard/myorders']")
    WebElement ordersButton;

    public void waitForElementToAppear(By findBY){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBY));
    }
    public void waitForWebElementToAppear(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(webElement  ));
    }
    public void waitForElementToDisappear(By findBY){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(findBY));
    }
    public CartPage goToCartPage(){
        cartButton.click();
        CartPage cartPage=new CartPage(driver);
        return cartPage;
    }
    public CheckoutPage goToCheckoutPage(){
        waitForElementToDisappear(toastMessage);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkoutButton);
        CheckoutPage checkoutPage=new CheckoutPage(driver);
        return checkoutPage;
    }
    public OrdersPage goTOOrdersPage(){
        ordersButton.click();
        OrdersPage ordersPage=new OrdersPage(driver);
        return ordersPage;
    }

}
