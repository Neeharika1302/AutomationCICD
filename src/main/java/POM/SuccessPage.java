package POM;

import AbstractComponent.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SuccessPage extends AbstractComponent {
    WebDriver driver;

    public SuccessPage(WebDriver driver) {
        super(driver);
        this.driver=driver;

    }
    @FindBy(className = "hero-primary")
    WebElement successsMsgHolder;
    public String getThankYouText(){
        String thankyouMsg=successsMsgHolder.getText();
        return thankyouMsg;
    }
}
