import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

@Test
public class AlertBox {
   public void handleAlertBox() throws InterruptedException {
       WebDriver driver = new ChromeDriver();
       driver.get("https://the-internet.herokuapp.com/ javascript_alerts");
       driver.manage().window().maximize();
         driver.findElement(By.className("jsConfirm()")).click();

       Thread.sleep(2000);
       Alert alert = driver.switchTo().alert();
       alert.accept();
       Thread.sleep(2000);
       System.out.println("Alert accepted");
   }
}
