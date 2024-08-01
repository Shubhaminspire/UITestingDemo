package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TutorialPointHomePage {
    By PhoneNumberDropDown = By.xpath("//select[@name='country_code']");
    private WebDriver driver;

    public TutorialPointHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void getAllCountries(){
        Select select = new Select(driver.findElement(PhoneNumberDropDown));
        List<WebElement> options = select.getOptions();
        for (WebElement option : options) {
            String value = option.getText();
            System.out.println(value);
        }
    }
}
