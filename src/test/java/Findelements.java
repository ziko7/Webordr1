import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Findelements {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("http://google.com");

        //driver.findElement(By.name("w")).sendKeys("cdbvsg");
        //if findElement() cannot find element,if trows NoSuchElementExeption

       List<WebElement> list = (List<WebElement>) driver.findElement(By.tagName("h1"));

       if(list.size()==0){
           throw new RuntimeException("no elements were found");
       }
    }

}
