import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class SeleniumDemo {
    static WebDriver driver=new ChromeDriver();
static public void logout () throws InterruptedException {
    Thread.sleep(3000);
    driver.findElement(By.id("rafael")).click();
    Thread.sleep(3000);
    if (driver.getCurrentUrl().equals("http://duotify.us-east-2.elasticbeanstalk.com/register.php")){
        System.out.println("User logged out successfully ");
    }else{
        System.out.println("Logged out failed");

    }
}
    public static void main(String[] args) throws InterruptedException {

        driver.get("http://duotify.us-east-2.elasticbeanstalk.com/register.php");
        driver.manage().window().maximize();
        driver.getTitle().contains("Welcome to Duotify!");
        String actual= driver.getTitle();
        String expectedTitle="Welcome to Duotify!";

        Faker faker=new Faker();
        if (actual.equalsIgnoreCase(expectedTitle)){
            System.out.println("Title matches");
        }else{
            System.out.println("Title doesn't match");

        }

        Thread.sleep(2000);
        WebElement username =  driver.findElement(By.id("username"));
        WebElement pwd =  driver.findElement(By.id("password"));
        WebElement firstname =    driver.findElement(By.id("firstName"));
        WebElement lastname =    driver.findElement(By.id("lastName"));
         WebElement signOn=driver.findElement(By.id("hideLogin"));
         signOn.click();
          username.sendKeys(faker.name().username());
String username1 = username.getAttribute("value");
         firstname.sendKeys(faker.name().firstName());
         lastname.sendKeys(faker.name().lastName());
String frstname = firstname.getAttribute ("value");
        String lsttname = lastname.getAttribute ("value");

        String fullname = frstname + " " + lsttname;


    WebElement email=    driver.findElement(By.id("email"));
    email.sendKeys(faker.internet().emailAddress());
        String last = email.getAttribute("value");


        email.click();

Thread.sleep(3000);
        driver.findElement(By.id("email2")).sendKeys(last);


         pwd.sendKeys("12345");
String password = pwd.getAttribute("value");
         driver.findElement(By.id("password2")).sendKeys("12345");

         driver.findElement(By.name("registerButton")).click();

         String actualUrl= driver.getCurrentUrl();

        Assert.assertEquals(actualUrl,"http://duotify.us-east-2.elasticbeanstalk.com/browse.php?");

        String fullnamefrommainpage = driver.findElement(By.id("nameFirstAndLast")).getText();
        if(fullnamefrommainpage.equals(fullname))
        {
            System.out.println("First and Last name are matching in the Log in Page");
        }else {

            System.out.println("First and Last name are not matching in the Log in Page");
        }

        driver.findElement(By.id("nameFirstAndLast")).click();
Thread.sleep(3000);
String fullnamemainwindow = driver.findElement(By.className("userInfo")).getText();
        if(fullnamemainwindow.equals(fullname))
        {
            System.out.println("First and Last name is correct in Main Window");
        }else {

            System.out.println("First and Last name is not correct in the Main window");
        }

SeleniumDemo.logout();

driver.findElement(By.id("loginUsername")).sendKeys(username1);
driver.findElement(By.id("loginPassword")).sendKeys(password);
Thread.sleep(3000);
driver.findElement(By.name("loginButton")).click();
Thread.sleep(3000);
      String textlogin =  driver.findElement(By.xpath("//*[@id='mainContent']/h1")).getText();
      String expectedtext = "You Might Also Like";
if (textlogin.equals(expectedtext)){
    System.out.println("Logged in  , text is matching " + textlogin + " and " + expectedtext);
}else {
    System.out.println("Text is not matching , it might be wrong log in" +  textlogin + " and " + expectedtext) ;
}

        driver.findElement(By.id("nameFirstAndLast")).click();
        SeleniumDemo.logout();


        driver.close();

}
}
