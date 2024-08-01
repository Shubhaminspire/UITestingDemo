import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class InitaiteDriver {
    public WebDriver driver;

    public InitaiteDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver launchBrowser(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        } else {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        }
        return driver;
    }
}
