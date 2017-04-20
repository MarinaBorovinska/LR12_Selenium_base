import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumBase {
    public static void main(String[] args) throws InterruptedException {

        String login = "marina.borovinska";
        final String password = "March62017";
        String summary = "testMarina";

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Марина\\Documents\\geckodriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        WebDriver driver = new FirefoxDriver(capabilities);

        driver.get("http://soft.it-hillel.com.ua:8080/secure/Dashboard.jspa");

        WebElement loginElement = driver.findElement(By.id("login-form-username"));
        loginElement.sendKeys(login);

        WebElement passwordElement = driver.findElement(By.id("login-form-password"));
        passwordElement.sendKeys(password);

        WebElement loginJira = driver.findElement(By.id("login"));
        loginJira.submit();

        System.out.println("Page title is: " + driver.getTitle());

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply (WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("system");
            }
        });

        System.out.println("Page title is: " + driver.getTitle());

        Thread.sleep(1000);

        WebElement searchElement = driver.findElement(By.xpath(".//*[@id='quickSearchInput']"));
        System.out.println("Find Search element: " + searchElement);
        WebElement dashboardElement = driver.findElement(By.xpath(".//*[@id='dashboard-content']/div[1]/div/div[1]/h1"));
        System.out.println("Find Dashboard element: " + dashboardElement);
        WebElement createElement = driver.findElement(By.xpath(".//*[@id='create_link']"));
        System.out.println("Find Create element: " + createElement);

        Thread.sleep(2000);


        driver.get("http://soft.it-hillel.com.ua:8080/browse/QAAUT-60");
        driver.manage().window().maximize();

        driver.findElement(By.xpath(".//*[@id='opsbar-operations_more']/span")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath(".//*[@id='create-subtask']/span")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@id='summary']")).clear();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@id='summary']")).sendKeys(summary);
        driver.findElement(By.xpath(".//*[@id='create-issue-submit']")).click();

        Thread.sleep(2000);

        driver.quit();
    }
}