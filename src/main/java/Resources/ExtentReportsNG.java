package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.BeforeTest;

public class ExtentReportsNG {
    @BeforeTest
    public static ExtentReports getReportObject(){
        String filePath=System.getProperty("user.dir")+"\\Reports\\index.html";
        ExtentSparkReporter reporter=new ExtentSparkReporter(filePath);
        reporter.config().setReportName("Web automation results");
        reporter.config().setDocumentTitle("Test Results");
        ExtentReports extent=new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Neeha");
        return extent;
    }
}
