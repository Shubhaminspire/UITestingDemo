package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class ExplicitWait {
    public WebDriver driver;
    WebDriverWait wait = null;
    public ExplicitWait(WebDriver driver){
        this.driver = driver;
    }

    public WebElement locateElementByVisibility(By element){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public WebElement locateButton(By element){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public Boolean textToBePresent(By element, String value){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.textToBePresentInElementValue(element, value));
    }
}
