import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class XpathDemo {

@Test
    public void warmUp(){

        WebDriver driver=new ChromeDriver();
        driver.get("http://www.istagram.com/");
         driver.findElement(By.xpath("html/body/div")).getText();




    }
}
