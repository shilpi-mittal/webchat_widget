package ui_tests.step_definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui_tests.page_objects.LocationWidget;
import ui_tests.page_objects.MessageWidget;
import ui_tests.page_objects.PageObjectFactory;
import ui_tests.utils.TestCaseHelper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class LocationWidgetStepDefinitions extends TestCaseHelper {
    LocationWidget locationWidget;
    MessageWidget messageWidget;
    private static final Logger LOG = LoggerFactory.getLogger(LocationWidgetStepDefinitions.class);

    @Then("I should see Location Widget")
    public void iShouldSeeLocationWidget() {
        locationWidget = PageObjectFactory.getLocationWidget(testWebDriver);
        assertTrue(locationWidget.isLocationSelectorDisplayed(), "The location widget is not displayed");
    }

    @And("should see title as {string}")
    public void shouldSeeTitleAs(String title) {
        assertEquals(title, locationWidget.getLocationWidgetTitle(), "Location Widget title is not as expected");
    }

    @And("should see subtitle as {string}")
    public void shouldSeeSubtitleAs(String subtitle) {
        assertEquals(subtitle, locationWidget.getLocationWidgetSubTitle(),
                "Location Widget subtitle is not as expected");
    }

    @And("should see Location search input placeholder as {string}")
    public void shouldSeeLocationSearchInputPlaceholderAs(String placeholder) {
        assertEquals(placeholder, locationWidget.getLocationSearchInputPlaceholder(),
                "Location Widget search input placeholder is not as expected");
    }

    @And("should see Terms text as {string} and link as {string}")
    public void shouldSeeTermsTextAsAndLinkAs(String text, String link) {
        assertTrue(locationWidget.areTermsDisplayed(), "Terms should be displayed");
        assertEquals(text, locationWidget.getTermsText(),
                "Location Widget Terms text is not as expected");
        assertEquals(link, locationWidget.getTermsLink(),
                "Location Widget Terms link is not as expected");
    }

    @And("should see Location list with {int} expected items")
    public void shouldSeeLocationListWithExpectedItems(int locationCount) {
        assertTrue(locationWidget.isLocationsListDisplayed(), "location list should be displayed");
        assertEquals(locationCount, locationWidget.getNumberOfLocationsInList(),
                "Location Widget Locations count is not as expected");
    }

    @And("should see locations in list as:")
    public void shouldSeeLocationsInListAs(DataTable table) {
        List<List<String>> rows = table.asLists(String.class);
        List<String> locations = locationWidget.getLocations();

        for (List<String> row : rows) {
            assertTrue(locations.contains(row.get(0)), row.get(0)+" location is not displayed in the list. " +
                    "List contains " + locations);
        }
    }

    @When("I enter location search input as {string}")
    public void iEnterLocationSearchInputAs(String input) {
        locationWidget.enterLocationSearchInput(input);
    }

    @Then("I should see first item in list contains {string}")
    public void iShouldSeeFirstItemInListContains(String input) {
        assertTrue(locationWidget.isLocationsListDisplayed(), "location list should be displayed");
        assertTrue(locationWidget.getLocations().get(0).contains(input), "Location search results are ordered not as expected");
    }

    @And("should see arrow icon on hover")
    public void shouldSeeArrowIconOnHover() {
        assertTrue(locationWidget.isArrowDisplayedOnHover(0), "arrow is not displayed on location on hover");
    }

    @When("I click on first location")
    public void iClickOnFirstLocation() {
        messageWidget = locationWidget.selectLocationFromList(0);
    }

    @Then("I should see message compose widget")
    public void iShouldSeeMessageComposeWidget() {
        assertTrue(messageWidget.isComposeMessageWidgetDisplayed());
    }

    @And("message widget header should have {string} in title")
    public void messageWidgetHeaderShouldHaveInTitle(String input) {
        assertTrue(messageWidget.getCurrentLocationName().contains(input),
                "Message Widget Current Location is expected to contain " + input + ", but is " + messageWidget.getCurrentLocationName());
    }

    @And("should see Location search icon and clear button")
    public void shouldSeeLocationSearchIconAndClearButton() {
        assertTrue(locationWidget.isClearLocationButtonDisplayed(), "clear location button should be displayed");
        assertTrue(locationWidget.isLocationSearchIconDisplayed(), "search location icon should be displayed");
    }

    @And("I should see Location search input as {string}")
    public void iShouldSeeLocationSearchInputAs(String input) {
        assertEquals(input, locationWidget.getLocationSearchInput(), "Location search input is not as expected");
    }
}
