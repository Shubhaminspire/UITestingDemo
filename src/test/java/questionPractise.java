import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class questionPractise {

    public WebDriver driver = null;


    @BeforeTest
    @Parameters("browserName")
    public void launchBrowser(String browser) {
        driver = new InitaiteDriver(driver).launchBrowser(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String url = "https://www.tutorialspoint.com/tutor_connect/index.php";
        driver.get(url);

    }

    @Test(priority = 1)
    public void firstTest() {
        By PhoneNumberDropDown = By.xpath("//select[@name='country_code']");
        Select select = new Select(driver.findElement(PhoneNumberDropDown));
        List<WebElement> options = select.getOptions();
        for (int i = 0; i < options.size(); i++) {
            String value = options.get(i).getText();
            System.out.println(value);
        }


    }

//    @DataProvider(name = "loginTest")
//    public Object[][] getLoginDetails() {
//        Object[][] objects = null;
//        objects = new Object[][]{
//                {"Shubham", "Anish@878"},
//                {"Anish", "Ragh$5"},
//                {"Akhil", "78765"}
//        };
//        return objects;
//    }

//    @Test(dataProvider = "loginTest", priority = 2)
//    public void getData(String username, String password) {
//        System.out.println("Username is: " + username + ". Password is: " + password);
//    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
