import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class SubscriptionTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Set up the driver and open the URL
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://subscribe.stctv.com/");
    }

    @AfterMethod
    public void teardown() {
        // Close the browser
        driver.quit();
    }

    @Test
    public void validateSubscriptionPackages() {
        // Validate the subscription packages for each country
        validateSubscriptionPackage("SA");
        validateSubscriptionPackage("Kuwait");
        validateSubscriptionPackage("Bahrain");
    }

    public void validateSubscriptionPackage(String country) {
        // Locate the subscription packages section
        WebElement subscriptionPackages = driver.findElement(By.id("subscription-packages"));

        // Locate the subscription package elements for the given country
        WebElement packageType = subscriptionPackages
                .findElement(By.xpath("//div[contains(@class, 'package-type') and text()='" + country + "']"));
        WebElement packagePrice = subscriptionPackages
                .findElement(By.xpath("//div[contains(@class, 'package-price') and text()='" + country + "']"));
        WebElement packageCurrency = subscriptionPackages
                .findElement(By.xpath("//div[contains(@class, 'package-currency') and text()='" + country + "']"));

        // Retrieve the subscription package details
        String actualType = packageType.getText();
        String actualPrice = packagePrice.getText();
        String actualCurrency = packageCurrency.getText();

        // Validate the subscription package details against the expected values
        switch (country) {
            case "SA":
                assert actualType.equals("Basic") : "Subscription package type doesn't match for " + country;
                assert actualPrice.equals("50") : "Subscription package price doesn't match for " + country;
                assert actualCurrency.equals("SAR") : "Subscription package currency doesn't match for " + country;
                break;
            case "Kuwait":
                assert actualType.equals("Premium") : "Subscription package type doesn't match for " + country;
                assert actualPrice.equals("100") : "Subscription package price doesn't match for " + country;
                assert actualCurrency.equals("KWD") : "Subscription package currency doesn't match for " + country;
                break;
            case "Bahrain":
                assert actualType.equals("Ultimate") : "Subscription package type doesn't match for " + country;
                assert actualPrice.equals("150") : "Subscription package price doesn't match for " + country;
                assert actualCurrency.equals("BHD") : "Subscription package currency doesn't match for " + country;
                break;
        }
    }
}
