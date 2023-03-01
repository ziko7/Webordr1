import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.Random;


public class WebOrderTest {
    //launch Chrome browser
    static WebDriver driver=new ChromeDriver();

    public static void main(String[] args)throws InterruptedException {
        // Navigate to login page
        Thread.sleep(3000);
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
        Thread.sleep(3000);
        driver.manage().window().maximize();
        // Login with username and password

        WebElement usernameField = driver.findElement(By.id("ctl00_MainContent_username"));
        usernameField.sendKeys("Tester");
        WebElement passwordField = driver.findElement(By.id("ctl00_MainContent_password"));
        passwordField.sendKeys("test");
        WebElement loginButton = driver.findElement(By.id("ctl00_MainContent_login_button"));
        loginButton.click();
        //Click on Order link
        Thread.sleep(3000);
        WebElement orderLink=driver.findElement(By.linkText("Order"));
        orderLink.click();
        //Enter a random product quantity between 1 and 100
        Thread.sleep(3000);
        int quantity=(int)(Math.random()*100)+1;
        WebElement quantityField=driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity"));
        quantityField.sendKeys(String.valueOf(quantity));
        //Click on Calculate and verify that the Total value is correct.
        Thread.sleep(3000);
        WebElement calculateButton = driver.findElement(By.xpath("//input[@value='Calculate']"));
        calculateButton.click();

        // Verify that the Total value is correct
        double pricePerUnit = 100.0;
        double expectedTotal;
        if (quantity >= 10) {
            expectedTotal = quantity * pricePerUnit * 92; // apply 8% discount
        } else {
            expectedTotal = quantity * pricePerUnit;
        }
        WebElement totalField = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal"));
        double actualTotal = Double.parseDouble(totalField.getAttribute("value"));
        if (actualTotal == expectedTotal) {
            System.out.println("Total value is correct: " + actualTotal);
        } else {
            System.out.println("Total value is incorrect: " + actualTotal);
        }

        // Close browser
       // driver.quit();
        Faker faker=new Faker();




        driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).sendKeys( faker.name().fullName());
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).sendKeys( faker.address().streetAddress());
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys(faker.address().city());
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4")).sendKeys( faker.address().state());
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(faker.address().zipCode());


        // Step 11-12: Select card type randomly and enter card number
        List<WebElement> cardTypes = driver.findElements(By.cssSelector("input[type='radio'][name='ctl00$MainContent$fmwOrder$cardList']"));
        WebElement randomCardType = cardTypes.get(new Random().nextInt(cardTypes.size()));
        randomCardType.click();
        String cardNumber = "";
        switch (randomCardType.getAttribute("value")) {
            case "Visa":
                cardNumber = "4" + faker.number().digits(15);
                break;
            case "MasterCard":
                cardNumber = "5" + faker.number().digits(15);
                break;
            case "American Express":
                cardNumber = "3" + faker.number().digits(14);
                break;
        }
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(cardNumber);
        driver.findElement(By.xpath("//input[@id='ctl00_MainContent_fmwOrder_TextBox1']")).sendKeys("10/24");
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();






    }






    }













