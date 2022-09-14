package ui_tests.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import constants.EnvConstants;
import common_utils.TestEnvironmentUtils;

public class TestWebDriver {
    private static Duration DEFAULT_WAIT_TIME;
    private WebDriver webDriver;

    public TestWebDriver(WebDriver driver) {
        webDriver = driver;
        TestEnvironmentUtils.loadTestEnvProperties();
        DEFAULT_WAIT_TIME = Duration.ofSeconds(Long.parseLong(EnvConstants.DEFAULT_WAIT));
        maximizeWindows();
    }

    public void goToBaseUrl() {
        webDriver.manage().deleteAllCookies();
        navigateTo(EnvConstants.PROTOCOL + EnvConstants.BASE_URL);
    }

    public void maximizeWindows() {
        webDriver.manage().window().maximize();
    }

    public boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void waitForElementToAppear(final WebElement element) {
        (new WebDriverWait(webDriver, DEFAULT_WAIT_TIME)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return (element.isDisplayed());
            }
        });
    }

    public void waitForElementToDisappear(final By locator) {
        new WebDriverWait(webDriver, DEFAULT_WAIT_TIME).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForElementToDisappear(final WebElement element, int waitTime) {
        (new WebDriverWait(webDriver, Duration.ofSeconds(waitTime))).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return (!element.isDisplayed());
            }
        });
    }

    public void waitForElementToDisappear(final WebElement element) {
        (new WebDriverWait(webDriver, DEFAULT_WAIT_TIME)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return (!element.isDisplayed());
            }
        });
    }

    public void enterInput(final WebElement element, String input) {
        waitForElementToAppear(element);
        element.clear();
        element.sendKeys(input);
    }

    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    public List<WebElement> findElements(By by) {
        return webDriver.findElements(by);
    }

    public WebElement findElement(By by) {
        return webDriver.findElement(by);
    }

    public void quitDriver() {
        try {
            webDriver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebDriver getDriver() {
        return webDriver;
    }

    public void navigateTo(String url) {
        webDriver.navigate().to(url);
    }

    public void sleep(int timeInMS) {
        try {
            Thread.sleep(timeInMS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickOnElement(WebElement element) {
        waitForElementToAppear(element);
        element.click();
    }

    public String getText(WebElement element) {
        waitForElementToAppear(element);
        return element.getText().trim();
    }

    public void switchIFrame(WebElement iframe) {
        switchToDefault();
        waitForElementToAppear(iframe);
        webDriver.switchTo().frame(iframe);
    }

    public void switchToDefault() {
        webDriver.switchTo().defaultContent();
    }

    public void switchIFrame(String iframeID) {
        switchToDefault();
        if (isDisplayed(webDriver.findElement(By.id(iframeID)))) {
            webDriver.switchTo().frame(iframeID);
        }
    }

    public String getAttribute(WebElement element, String attribute) {
        waitForElementToAppear(element);
        return element.getAttribute(attribute);
    }

    public String getPlaceholder(WebElement element) {
        return getAttribute(element, "placeholder");
    }

    public void hoverOnElement(WebElement element) {
        waitForElementToAppear(element);
        Actions action = new Actions(webDriver);
        action.moveToElement(element).perform();
    }
}
