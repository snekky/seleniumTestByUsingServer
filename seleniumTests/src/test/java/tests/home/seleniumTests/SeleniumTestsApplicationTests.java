package tests.home.seleniumTests;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class SeleniumTestsApplicationTests {


    private WebDriver webDriver;

    @Before
    public void initWebDriver() {

    }


    @Test(expected = org.openqa.selenium.NoSuchElementException.class)
    public void CheckIncorrectInput() throws MalformedURLException {

        webDriver.get("http://localhost:8080");
        webDriver.findElement(By.id("input")).sendKeys("React1");
        String name = webDriver.findElement(By.className("contact-name")).getText();
        assertEquals(name, "React1");
    }


    @Test
    public void CheckCorrectInput() {
        webDriver.get("http://localhost:8080");
        webDriver.findElement(By.id("input")).sendKeys("React");
        String name = webDriver.findElement(By.className("contact-name")).getText();
        assertEquals(name, "React");
    }

    // BeforeClass не канает, т.к. нужен static
    @Before
    public  void beforeClass() throws MalformedURLException {
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setBrowserName("chrome");
        capability.setPlatform(Platform.LINUX);
        webDriver= new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);

    }

    @After
    public void quitDriver() {

        webDriver.quit();
    }
}
