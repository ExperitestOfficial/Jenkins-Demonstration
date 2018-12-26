package JenkinsAutomation;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.net.URL;


public class DesktopWebTest {

    private static final String ACCESS_KEY = System.getenv("access_key");
    private WebDriver driver;
    private URL url;
    private DesiredCapabilities dc = new DesiredCapabilities();

    @Parameters({"browser_name"})
    @BeforeTest
    public void setUp(String browser_name) throws Exception {
    	if(System.getenv("cloud").equals("Sales"))
    		url = new URL("https://sales.experitest.com:443/wd/hub");
        else
        	url = new URL("https://cloud.seetest.io:443/wd/hub");
        dc.setCapability(CapabilityType.BROWSER_NAME, browser_name);
        dc.setCapability(CapabilityType.VERSION, "Any");
        dc.setCapability("accessKey", ACCESS_KEY);
        dc.setCapability("testName", "Automation Demo - Web - " + browser_name);
        driver = new RemoteWebDriver(url, dc);
    }


    @Test
    public void testExperitest() {
        driver.get("https://github.com/login");
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("login_field")));
        WebElement username_field = driver.findElement(By.id("login_field"));
        WebElement password_field = driver.findElement(By.id("password"));
        WebElement login_button = driver.findElement(By.xpath("//*[@id=\"login\"]/form/div[3]/input[3]"));
        username_field.click();
        username_field.sendKeys("Experitest");
        password_field.click();
        password_field.sendKeys("Experitest");
        login_button.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"login\"]/form/div[3]/input[3]")));
        WebElement create_account = driver.findElement(By.xpath("//*[@id=\"login\"]/p/a"));
        create_account.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("user_login")));
        username_field = driver.findElement(By.id("user_login"));
        username_field.sendKeys("Experitest");
        WebElement email_field = driver.findElement(By.id("user_email"));
        email_field.sendKeys("experitest@experitest.com");
        password_field = driver.findElement(By.id("user_password"));
        password_field.sendKeys("Experitest");
        WebElement signup_button = driver.findElement(By.id("signup_button"));
        signup_button.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"signup-form\"]/div")));
    }

    @AfterTest
    public void tearDown() {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String urlLink = cap.getCapability("reportUrl").toString();
        System.out.println("The link to the report is ================== " + urlLink + " ==================");
        driver.quit();
    }

}