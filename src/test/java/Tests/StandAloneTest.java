package Tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args ){
        String prodName="IPHONE 13 PRO";
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client/");
        driver.findElement(By.id("userEmail")).sendKeys("pneeharika2000@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Honey@1302");
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> prodNames=driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod= prodNames.stream().filter(s->s.findElement(By.xpath(".//div[@class='card-body']//b")).getText().equals(prodName)).findFirst().orElse(null);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
        prod.findElement(By.xpath(".//div/button[contains(text(),'Add To Cart')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.tagName("ngx-spinner"))));
        driver.findElement(By.cssSelector("[routerlink*='/cart']")).click();
        List<WebElement> cartProds=driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean matches=cartProds.stream().anyMatch(cartProd->cartProd.getText().equalsIgnoreCase(prodName));
        Assert.assertTrue(matches);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
        WebElement checkout = driver.findElement(By.cssSelector(".totalRow button"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkout);
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")));
        js.executeScript("arguments[0].click();",driver.findElement(By.cssSelector(".action__submit")));
        String thankyouMsg=driver.findElement(By.className("hero-primary")).getText();
        Assert.assertTrue(thankyouMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.close();
    }
}
