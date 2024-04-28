package POM;

import AbstractComponent.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CatalogPage extends AbstractComponent {
    WebDriver driver;

    public CatalogPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css=".mb-3")
    List<WebElement> products;

    By productBy=By.cssSelector(".mb-3");
    By toastMessage=By.cssSelector("#toast-container");
    By spinner= By.tagName("ngx-spinner");


    public List<WebElement> getProductList(){
       waitForElementToAppear(productBy);
        return products;
    }
    public WebElement getProductByName(String prodName){
        WebElement prod= getProductList().stream().filter(s->s.findElement(By.xpath(".//div[@class='card-body']//b")).getText().equals(prodName)).findFirst().orElse(null);
        return prod;
    }
    public void addProductToCart(String prodName){
        WebElement prod= getProductByName(prodName);
        waitForElementToDisappear(toastMessage);
        prod.findElement(By.xpath(".//div/button[contains(text(),'Add To Cart')]")).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }
}
