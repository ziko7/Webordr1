import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v107.css.model.CSSContainerQuery;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static java.awt.SystemColor.text;

public class WarmUp {

    public void testCarsCom() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.cars.com/");
        driver.manage().window().maximize();
        new Select(driver.findElement(By.id("models"))).selectByIndex(7);
        new Select(driver.findElement(By.id("make-model-max-price"))).selectByVisibleText("No max price");

        driver.findElement(By.id("make-model-zip")).clear();
        driver.findElement(By.id("make-model-zip")).sendKeys("22031", Keys.ENTER);
        String actual = new Select(driver.findElement(By.id("sort-dropdown"))).getFirstSelectedOption().getText();
        Assert.assertEquals(actual, "Best Match");

        new Select(driver.findElement(By.id("sort-dropdown"))).selectByVisibleText("Lowest Price ");

       List <WebElement> prices = (List<WebElement>) driver.findElement(By.xpath("div[@class='vehicle-card-main-gallery-click-card']//span[@class='primary-price']"));
        List<Integer> original=new ArrayList<>();

       for (WebElement price : prices) {
            System.out.println(price.getText());
           System.out.println(price.getText().replace("$", "").replace(",", ""));
            original.add(Integer.parseInt(price.getText().replace("$", "").replace(",", "")));
        }

        System.out.println(original);
       List<Integer> sorted=new ArrayList<>(original);
        sorted.sort(Integer::compareTo);
        System.out.println(sorted);
        Assert.assertEquals(original, sorted);

      Thread.sleep(3000);
        new Select(driver.findElement(By.id("sort-dropdown"))).selectByVisibleText("Highest mileage ");
        List <WebElement> mileage = (List<WebElement>) driver.findElement(By.xpath("div[@class='vehicle-card-main-gallery-click-card']//span[@class='primary-price']"));
        List<Integer> original1=new ArrayList<>();
        for (WebElement mile : mileage) {
            String text = mile.getText();
            originalMiles (Integer.parseInt(text));
        }
        List<Integer>sortedMiles=new ArrayList<>(original1);
        sortedMiles.sort(Integer::compareTo);
        System.out.println(sortedMiles);
        Assert.assertEquals(original1, sortedMiles);
        driver.quit();


    }

    private void originalMiles(int parseInt) {
    }

}