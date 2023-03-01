import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class WebTable {
    @Test

    public void stables() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        Thread.sleep(3000);
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='currencies-all']//tbody//tr"));
        System.out.println(rows.size());
        for (int i = 1; i <= rows.size(); i++) {

            String xpath = "//table[@id='currencies-all']//tbody//tr[" + i + "]//td[3]";
            driver.findElement(By.xpath(xpath));
            System.out.print(driver.findElement(By.xpath(xpath)).getText());

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,1000)");
        }


    }
}
