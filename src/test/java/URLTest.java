import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLTest {
    public WebDriver driver = null;

    @BeforeTest
    public void runBeforeTest() {
        InitaiteDriver initaiteDriver = new InitaiteDriver(driver);
        driver = initaiteDriver.launchBrowser("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6000));

    }

    @Test(enabled = false)
    public void firstTest() {

//        driver = new ChromeDriver();
//        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6000));
        String websiteLink = "https://www.amazon.in/";
        driver.get(websiteLink);
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));

        long startTime = System.currentTimeMillis();
        allLinks.parallelStream().forEach(link -> {
            String url = link.getAttribute("href");
            if (url != null && !url.isEmpty()) {
                try {
                    URL urls = new URL(url);
                    HttpURLConnection urlConnection = (HttpURLConnection) urls.openConnection();
                    urlConnection.connect();
                    if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                        System.out.println("Link is: " + url + " Response Status Code: " + urlConnection.getResponseCode());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
//        for (WebElement link : allLinks) {
//            String url = link.getAttribute("href");
//            if (url != null && !url.isEmpty()) {
//                try {
//                    URL urls = new URL(url);
//                    HttpURLConnection urlConnection = (HttpURLConnection) urls.openConnection();
//                    urlConnection.setRequestMethod("HEAD");
//                    urlConnection.connect();
//                    if (urlConnection.getResponseCode() != urlConnection.HTTP_OK) {
//                        System.out.println(url+" "+urlConnection.getResponseCode());
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total Time Taken in Printing all broken links is: " + totalTime);
        driver.quit();
    }

    @Test
    public void findAllProducts() throws InterruptedException {
        By searchBox = By.id("twotabsearchtextbox");
        By prices = By.xpath("//span[@class='a-price-whole']");
        List<Integer> phonePrices = new ArrayList<>();
//        driver = new ChromeDriver();
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable")
        String websiteLink = "https://www.amazon.in/";
        driver.get(websiteLink);
        WebElement searchProduct = driver.findElement(searchBox);
        searchProduct.sendKeys("Mobile");
//        Actions action = new Actions(driver);
        searchProduct.sendKeys(Keys.ENTER);
//        action.keyDown(Keys.CONTROL).keyDown(Keys.ADD).keyUp(Keys.ADD).keyUp(Keys.CONTROL);
        Thread.sleep(6000);
        List<WebElement> price = driver.findElements(prices);
        for (WebElement element : price) {
            try {
                Integer cost = Integer.parseInt(element.getText().replaceAll("[^\\d]", ""));
                phonePrices.add(cost);
            } catch (NumberFormatException e) {
                System.out.println("Failed to parse Price: " + element.getText());
            }

        }
        System.out.println("The Phone Prices before sort: " + phonePrices);
        Collections.sort(phonePrices);
        System.out.println("The Phone Prices after sort: " + phonePrices);
        String miniumPriceProductValue = String.format("%,d", phonePrices.get(0));
        System.out.println(miniumPriceProductValue);

        By minimumPriceXpath = By.xpath("//span[@class='a-price-whole' and contains(text(),'" + (miniumPriceProductValue) + "')]");
        List<WebElement> clickOnMinimumProduct = driver.findElements(minimumPriceXpath);
        for (WebElement items : clickOnMinimumProduct) {
            Thread.sleep(3000);
            items.click();
            String mainWindow = driver.getWindowHandle();
            for (String window : driver.getWindowHandles()) {
                driver.switchTo().window(window);
                Thread.sleep(6000);
            }
            driver.switchTo().window(mainWindow);
        }

    }

    @Test
    public void firstSolution() {
        //https://api-pub.bitfinex.com/v2/status/deriv?keys=ALL
        //https:///www.cnn.com/ads.txt
        driver.get("https://api-pub.bitfinex.com/v2/status/deriv?keys=ALL");
        String text = driver.findElement(By.xpath("//pre")).getText().trim();
        System.out.println(text);
//        Pattern pattern = Pattern.compile("\\bDIRECT\\b");
        Pattern pattern1 = Pattern.compile("null", Pattern.CASE_INSENSITIVE);
        Matcher matcher1 = pattern1.matcher(text);
//        Matcher matcher = pattern.matcher(text);
        int count = 0;
//        while (matcher.find()) {
//            count++;
//        }
//        System.out.println("The count of DIRECT is: " + count);
        while (matcher1.find()) {
            count++;
        }

        System.out.println("The count of RESELLER is: " + count);
//

    }

    @Test
    public void fetchYouTubeAutoSuggestionAllOptions() throws InterruptedException {
        By searchBox = By.xpath("//div[@id='search-input']/input");
        By options = By.xpath("//div[contains(@id,'sbse')]/div/following-sibling::div");
        driver.get("https://www.youtube.com/");
        WebElement search = driver.findElement(searchBox);
        search.sendKeys("Aksha");
        search.sendKeys("y");
        Thread.sleep(2000);
        List<WebElement> list = driver.findElements(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        for (WebElement web : list) {
            wait.until(ExpectedConditions.visibilityOf(web));
            System.out.println(web.getText());
        }

    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
