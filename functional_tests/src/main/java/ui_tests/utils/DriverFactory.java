package ui_tests.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class DriverFactory {
    private final String input = System.getProperty("user.dir");
    private final String projectRoot = input.substring(0, input.indexOf("webchat_widget") + "webchat_widget".length());

    protected WebDriver loadDriver(boolean enableJavascript, String browser) {
        switch (browser) {
            case "firefox":
                return createFirefoxDriver(enableJavascript);

            default:
                return createChromeDriver(enableJavascript);
        }
    }

    private WebDriver createFirefoxDriver(boolean enableJavascript) {
        WebDriverManager.firefoxdriver().setup();

        FirefoxProfile profile = new FirefoxProfile();
        profile.setAcceptUntrustedCertificates(true);
        profile.setPreference("javascript.enabled", enableJavascript);

        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        //options.setHeadless(true);
        return new FirefoxDriver(options);
    }

    private WebDriver createChromeDriver(boolean enableJavascript) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");
        if (enableJavascript) {
            chromeOptions.addArguments("enable-javascript");
        }
        chromeOptions.addArguments("--whitelisted-ips=''");
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(chromeOptions);
    }
}
