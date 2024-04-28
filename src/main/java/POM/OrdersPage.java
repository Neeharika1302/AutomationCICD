package POM;

import AbstractComponent.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends AbstractComponent {
    WebDriver driver;
    @FindBy(css="tr[class='ng-star-inserted'] td:nth-child(3)")
    List<WebElement> prodNames;

    public OrdersPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public Boolean verifyOrderDisplay(String prodName){
        Boolean match=prodNames.stream().anyMatch(s->s.getText().equals(prodName));
        return match;
    }
}
