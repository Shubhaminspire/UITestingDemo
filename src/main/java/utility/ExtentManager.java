package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;

    public static ExtentReports getInstance(){
        if(extent == null){
            sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/extent.html");
            sparkReporter.config().setDocumentTitle("Automation Test Reports");
            sparkReporter.config().setReportName("Test Report");
            sparkReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Host Name","localHost");
            extent.setSystemInfo("Environment","QA");
            extent.setSystemInfo("Username","Shubham");
        }
        return extent;
    }
}
