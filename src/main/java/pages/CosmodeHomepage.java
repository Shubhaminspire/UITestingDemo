package pages;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import utility.ExplicitWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class CosmodeHomepage extends ExplicitWait {
    @FindBy(id="username")
    WebElement username;

    @FindBys({
            @FindBy(id="username"),
            @FindBy(xpath = "//div[contains(text(),'Click')]")
    })
    WebElement password;
    @FindAll({
            @FindBy(id="username"),
            @FindBy(xpath = "//div[contains(text(),'Click')]")
    })
    List<WebElement> title;
    public WebDriver driver = null;
    By tableRecords = By.xpath("//*[@id='countries']//tr");
    private FileInputStream fileInputStream;
    private Workbook workbook = null;
    private FileOutputStream fileOutputStream = null;

    public CosmodeHomepage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver,this);

    }

    public void getTableRecords(String filePath) throws IOException {
        List<WebElement> tableContents = driver.findElements(tableRecords);

        File file = new File(filePath);
        fileInputStream = new FileInputStream(file);
        workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet("Records");
        int rowCount = 1;
        Row headerRow = sheet.getRow(0);

        int columnIndex = headerRow.getLastCellNum();
        int countryColumnIndex = -1;
        int capitalColumnIndex = -1;
        int serialNumberColumnIndex = -1;
        int currencyColumnIndex = -1;
        int primaryLanguageColumnIndex = -1;
        for (Cell cell : headerRow) {
            if (cell.getStringCellValue().equals("Country")) {
                countryColumnIndex = cell.getColumnIndex();
            } else if (cell.getStringCellValue().equals("Capital")) {
                capitalColumnIndex = cell.getColumnIndex();

            } else if (cell.getStringCellValue().equals("S.No")) {
                serialNumberColumnIndex = cell.getColumnIndex();
            } else if (cell.getStringCellValue().equals("Currency")) {
                currencyColumnIndex = cell.getColumnIndex();
            } else if (cell.getStringCellValue().equals("Primary Language")) {
                primaryLanguageColumnIndex = cell.getColumnIndex();
            }
        }


        int index = 1;
        for (int rows = 2; rows <= tableContents.size(); rows++) {
            WebElement country = driver.findElement(By.xpath("//*[@id='countries']//tr[" + (rows) + "]/td[2]"));
            WebElement capital = driver.findElement(By.xpath("//*[@id='countries']//tr[" + (rows) + "]/td[3]"));
            WebElement currency = driver.findElement(By.xpath("//*[@id='countries']//tr[" + (rows) + "]/td[4]"));
            WebElement primaryLanguage = driver.findElement(By.xpath("//*[@id='countries']//tr[" + (rows) + "]/td[5]"));
            String countryName = country.getText();
            String capitalName = capital.getText();
            String currencyName = currency.getText();
            String primaryLanguageName = primaryLanguage.getText();
            System.out.println(index + ":" + countryName + ":" + capitalName);
            Row newRow = sheet.createRow(rowCount++);
            newRow.createCell(serialNumberColumnIndex).setCellValue(index);
            newRow.createCell(countryColumnIndex).setCellValue(countryName);
            newRow.createCell(capitalColumnIndex).setCellValue(capitalName);
            newRow.createCell(currencyColumnIndex).setCellValue(currencyName);
            newRow.createCell(primaryLanguageColumnIndex).setCellValue(primaryLanguageName);
            index++;
        }

        fileOutputStream = new FileOutputStream(file);
        workbook.write(fileOutputStream);
        workbook.close();
        fileInputStream.close();
        fileOutputStream.close();

    }

}
