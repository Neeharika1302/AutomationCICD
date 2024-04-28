package TestComponents;

import POM.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingPage;
    public WebDriver initializeDriver() throws IOException {

        Properties prop=new Properties();
        FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Resources//GlobalData.properties");
        prop.load(fis);
        String browserName=System.getProperty("browser")!= null ?System.getProperty("browser") : prop.getProperty("browser");
        //String browserName=prop.getProperty("browser");

        if(browserName.contains("chrome")){
            ChromeOptions chromeOptions=new ChromeOptions();
            if(browserName.contains("headless")) {
                chromeOptions.addArguments("headless");
            }
            driver = new ChromeDriver(chromeOptions);
            driver.manage().window().setSize(new Dimension(1440,900));
        } else if (browserName.equalsIgnoreCase("edge")){
            driver = new EdgeDriver() ;
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver=initializeDriver();
        landingPage=new LandingPage(driver);
        landingPage.getLandingPageURL();
        return landingPage;
    }
    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
        driver.close();
    }
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
    }
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts= (TakesScreenshot)driver;
        File source=ts.getScreenshotAs(OutputType.FILE);
        File file= new File(System.getProperty("user.dir")+"//Reports//"+testCaseName+".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir")+"//Reports//"+testCaseName+".png";
    }
}
