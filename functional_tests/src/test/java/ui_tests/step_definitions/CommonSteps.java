package ui_tests.step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ui_tests.page_objects.HomePage;
import ui_tests.page_objects.PageObjectFactory;
import ui_tests.utils.TestCaseHelper;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommonSteps extends TestCaseHelper {
    HomePage homePage;

    @Before
    public void setUp() {
        testWebDriver = super.setup();
    }

    @Given("^I am on Home Page$")
    public void goToHomePage() {
        homePage = PageObjectFactory.getHomePage(testWebDriver);
        homePage.openApplication();
    }

    @After
    public void tearDown() {
        // tear down steps will go here
        testWebDriver.quitDriver();
        PageObjectFactory.clearAllPageObjectReferences();
    }

    @When("^I click on WebChat Widget$")
    public void clickOnWebChatWidget() {
        homePage = PageObjectFactory.getHomePage(testWebDriver);
        homePage.clickOnWebsiteWidget();
    }

    @Then("I should see Widget")
    public void iShouldSeeWidget() {
        assertTrue(homePage.isWidgetDisplayed(), "The widget is not displayed");
    }

    @When("I close WebChat Widget")
    public void iCloseWebChatWidget() {
        homePage.closeWebsiteWidget();
    }

    @Then("Widget should not be displayed")
    public void widgetShouldBeClosed() {
        homePage = PageObjectFactory.getHomePage(testWebDriver);
        assertFalse(homePage.isWidgetDisplayed(), "The widget should not be displayed");
    }
}
