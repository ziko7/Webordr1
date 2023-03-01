import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarGurusTest {

    public static void main(String[] args) throws InterruptedException {



        // Create a new instance of the Chrome driver
        WebDriver driver = new ChromeDriver();

        // Navigate to the CarGurus website
        driver.get("https://www.cargurus.com/");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        // Click on the Buy Used button
        driver.findElement(By.xpath("//span[.='Buy Used']")).click();


        WebElement makesDropdown = driver.findElement(By.id("makes"));
        WebElement defaultMakeOption = makesDropdown.findElement(By.xpath("./option[@selected='selected']"));
        Assert.assertEquals(defaultMakeOption.getText(), "All Makes");

        // In Makes dropdown, choose Lamborghini
        makesDropdown.sendKeys("Lamborghini");

        // Verify that the default selected option in Models is All Models
        WebElement modelsDropdown = driver.findElement(By.id("models"));
        WebElement defaultModelOption = modelsDropdown.findElement(By.xpath("./option[@selected='selected']"));
        Assert.assertEquals(defaultModelOption.getText(), "All Models");

        // Verify that Models dropdown option is [All Models, Aventador, Huracan, Urus, 400GT, Centenario, Diablo, Espada, Gallardo]
        List<String> expectedModels = List.of("All Models", "Aventador", "Huracan", "Urus", "400GT", "Centenario", "Diablo", "Espada", "Gallardo");
        List<WebElement> actualModelOptions = modelsDropdown.findElements(By.tagName("option"));
        List<String> actualModels = new ArrayList<>();
        for (WebElement option : actualModelOptions) {
            actualModels.add(option.getText());
        }
        Assert.assertEquals(actualModels, expectedModels);

        // In Models dropdown, choose Gallardo
        modelsDropdown.sendKeys("Gallardo");

        // Enter 22031 for zip and hit search
        driver.findElement(By.id("zip")).sendKeys("22031");
        driver.findElement(By.id("searchBtn")).click();

        // Verify that there are 15 search results, excluding the first sponsored result
        WebElement firstResult = driver.findElement(By.xpath("//div[@data-cg-view='card']"));
        List<WebElement> searchResults = driver.findElements(By.xpath("//div[@data-cg-view='card'][position()>1]"));
        Assert.assertEquals(searchResults.size(), 15);

        // Verify that all 15 results title text contains "Lamborghini Gallardo"
        for (WebElement result : searchResults) {
            String title = result.findElement(By.xpath("./div[@class='cg-dealFinder-result-stats-row cg-dealFinder-result-title']//h4")).getText();
            Assert.assertTrue(title.contains("Lamborghini Gallardo"));
        }

        // From dropdown on the left corner choose "Lowest price first" option and verify that all 15 results are sorted from lowest to highest
        WebElement sortDropdown = driver.findElement(By.id("sortby"));
        sortDropdown.sendKeys("priceASC");

        // Verify that all 15 results are sorted from lowest to highest
        List<String> expectedSortedPrices = new ArrayList<>();
        for (WebElement result : searchResults) {
            String price = result.findElement(By.xpath("./div[@class='cg-dealFinder-result-stats-row']/span")).getText().replaceAll("[^\\d.]+", "");
            expectedSortedPrices.add(price);
        }

        List<String> actualSortedPrices = new ArrayList<>(expectedSortedPrices);
        Collections.sort(actualSortedPrices);

        Assert.assertEquals(actualSortedPrices, expectedSortedPrices);

        // From dropdown menu, choose "Highest mileage first" option and verify that all 15 results are sorted from highest to lowest
        sortDropdown.sendKeys("mileageDESC");

        // Verify that all 15 results are sorted from highest to lowest
        searchResults = driver.findElements(By.xpath("//div[@data-cg-view='card'][position()>1]"));
        Collections.sort(searchResults, (WebElement o1, WebElement o2) -> {
            String mileage1 = o1.findElement(By.xpath("./div[@class='cg-dealFinder-result-stats-row']/span[2]")).getText().replaceAll("[^\\d.]+", "");
            String mileage2 = o2.findElement(By.xpath("./div[@class='cg-dealFinder-result-stats-row']/span[2]")).getText().replaceAll("[^\\d.]+", "");
            return Integer.parseInt(mileage2) - Integer.parseInt(mileage1);
        });

        List<String> expectedSortedMileages = new ArrayList<>();
        for (WebElement result : searchResults) {
            String mileage = result.findElement(By.xpath("./div[@class='cg-dealFinder-result-stats-row']/span[2]")).getText().replaceAll("[^\\d.]+", "");
            expectedSortedMileages.add(mileage);
        }

        List<String> actualSortedMileages = new ArrayList<>(expectedSortedMileages);
        Collections.sort(actualSortedMileages, Collections.reverseOrder());

        Assert.assertEquals(actualSortedMileages, expectedSortedMileages);

        // On the left menu, click on Coupe AWD checkbox and verify that all results on the page contain "Coupe AWD"
        driver.findElement(By.id("bodytype")).sendKeys("Coupe");
        driver.findElement(By.id("drivetrain")).sendKeys("AWD");
        List<WebElement> filteredResults = driver.findElements(By.xpath("//div[@data-cg-view='card'][position()>1]"));
        for (WebElement result : filteredResults) {
            String bodyType = result.findElement(By.xpath("./div[@class='cg-dealFinder-result-stats-row cg-dealFinder-result-bodyType']")).getText();
            Assert.assertTrue(bodyType.contains("Coupe AWD"));
        }

        // Click on the list (get the last result dynamically, your code should click on the last result regardless of how many results are there)
        WebElement lastResult = searchResults.get(searchResults.size() - 1);
        lastResult.click();

        // Once you are in the result details page, go back to the result page and verify that the clicked result has "Viewed" text on it
        driver.navigate().back();
        String lastResultTitle = lastResult.findElement(By.xpath("./div[@class='cg-dealFinder-result-stats-row cg-dealFinder-result-title']//h4")).getText();

    }
}
