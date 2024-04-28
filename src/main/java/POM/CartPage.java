package POM;

import AbstractComponent.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class CartPage extends AbstractComponent {
    public CartPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css=".cartSection h3")
    List<WebElement> cartProds;

    public void prodMatches(String prodName){
        Boolean matches=cartProds.stream().anyMatch(cartProd->cartProd.getText().equalsIgnoreCase(prodName));
        Assert.assertTrue(matches);
    }

}

