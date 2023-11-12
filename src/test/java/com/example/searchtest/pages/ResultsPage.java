package com.example.searchtest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

//https://www.bing.com/search

public class ResultsPage {
    @FindBy(css = "#sb_form_q")
    private WebElement searchPageField;

    @FindBy(css = ":not(.b_adurl) >cite")
    private List<WebElement> results;

    public void clickElement(WebDriver driver, int num) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(results.get(num)));
        element.click();
        System.out.println("Выполнен клик на результат номер " + num);
    }
    public String getTextFromSearchField(){
        String val = searchPageField.getAttribute("value");
        System.out.println("В строке поиска текст: " + val);
        return val;
    }
    public ResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}