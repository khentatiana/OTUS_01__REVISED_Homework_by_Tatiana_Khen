import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FirstTest {

    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(FirstTest.class);
    private static String browser;

    @BeforeClass
    public static void oneTimeSetUp() throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream("config/project.config"));
        browser = prop.getProperty("browser");
        System.out.println(browser);
    }

    @Before
    public void setUp() {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            logger.info("Running browser is : " + browser);
        }

        else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            logger.info("Running browser is : " + browser);

        } else {
            logger.error("Wrong browser");
        }
    }

    @Test
    public void openPage() {
        String url = "https://otus.ru/";
        driver.get(url);
        logger.info("This is JUnit 4.13. Webpage '" + url + "' is opened");
    }

    @Test
    public void getTitle() {
        String url = "https://otus.ru/";
        driver.get(url);
        String title = driver.getTitle();
        logger.info("This is JUnit 4.13. Title of the page is '" + title + "'");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
