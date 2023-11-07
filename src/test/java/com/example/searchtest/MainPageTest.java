package com.example.searchtest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class MainPageTest {
    private WebDriver driver;

    @BeforeEach
        public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.bing.com/");
        System.out.println("Начало проверки");
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
        System.out.println("Проверка завершена");
    }

    public void clickElement(List<WebElement> results, int num) {
        results.get(num).click();
        System.out.println("Происходит клик на результат");
    }

    @Test
    @DisplayName("Тест поисковой строки Bing")
    public void searchTest() {
        String input = "Selenium";
        WebElement searchField = driver.findElement(By.cssSelector("#sb_form_q"));
        searchField.sendKeys(input);
        searchField.submit();
        List<WebElement> results = driver.findElements(By.cssSelector(":not(.b_adurl) >cite"));
        clickElement(results, 0);
        new WebDriverWait(driver, Duration.ofSeconds(10));
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.selenium.dev/";
        assertTrue(currentUrl.contains(expectedUrl), "Ссылка не соответствует необходимой");
    }
}
