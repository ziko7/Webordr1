import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDemo2 {
public static void main(String[] args) throws IllegalStateException, InterruptedException  {
        System.setProperty("web-driver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.cargurus.com/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
    WebElement search = driver.findElement(By.xpath("//input[@id='search']"));

























    }
}
