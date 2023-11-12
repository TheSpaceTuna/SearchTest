package com.example.searchtest.tests;

import com.example.searchtest.pages.MainPage;
import com.example.searchtest.pages.ResultsPage;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BingSearchTest {
    private WebDriver driver;

    @BeforeEach
        public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.bing.com");
        System.out.println("Начало проверки");
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
        System.out.println("Проверка завершена");
    }

    @Test
    @DisplayName("Тест поисковой строки Bing")
    public void searchTest() {
        String input = "Selenium";
        MainPage mp = new MainPage(driver);
        mp.sendText(input);

        ResultsPage rp = new ResultsPage(driver);
        assertEquals(input, rp.getTextFromSearchField(), "Текст не совпал");
    }
    @Test
    @DisplayName("Тест клика по результату в Bing")
    public void resultTest() {
        String input = "Selenium";
        MainPage mp = new MainPage(driver);
        mp.sendText(input);

        ResultsPage rp = new ResultsPage(driver);
        rp.clickElement(driver,0);

        assertEquals("https://www.selenium.dev/",driver.getCurrentUrl(), "Ссылка не соответствует необходимой");
    }

}