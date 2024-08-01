import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.CosmodeHomepage;
import utility.ExplicitWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

public class Cosmocode extends ExplicitWait {
    public WebDriver driver = null;
    CosmodeHomepage cosmodeHomepage = null;

    public Cosmocode(WebDriver driver) {
        super(driver);

    }

    @BeforeTest
    public void launchHomePage() {
        driver = new InitaiteDriver(driver).launchBrowser("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String url = "https://cosmocode.io/automation-practice-webtable/";
        driver.get(url);
//        Set<String> winwods = driver.getWindowHandles();
//        for(String win: winwods){
//            if(!win.equals(driver.getWindowHandle())){
//
//            }
//        }
    }

    @Test
    public void getTableRecords() throws IOException {
        cosmodeHomepage = new CosmodeHomepage(driver);
        cosmodeHomepage.getTableRecords("src/main/resources/dataFile/Countries.xlsx");
    }

    @AfterTest
    public void terminateSession() {
        if (driver != null) {
            driver.quit();
        }
    }
}
