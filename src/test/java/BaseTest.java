import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utility.ExtentManager;

import java.lang.reflect.Method;

public class BaseTest implements ITestListener {
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setUp() {
        extent = ExtentManager.getInstance();

    }

    @AfterSuite
    public void flush() {
        extent.flush();

    }

    @BeforeMethod
    public void startTest(Method method) {
        test = extent.createTest(method.getName());
    }

    @AfterMethod
    public void endTest(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed: " + result.getName());
            test.log(Status.FAIL, "Test Failure Cause: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Passed: " + result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Skipped: " + result.getName());
        }
    }

}
