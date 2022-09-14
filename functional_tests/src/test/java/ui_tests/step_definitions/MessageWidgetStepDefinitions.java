package ui_tests.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ui_tests.page_objects.MessageWidget;
import ui_tests.page_objects.PageObjectFactory;
import ui_tests.utils.TestCaseHelper;

import static org.junit.jupiter.api.Assertions.*;


public class MessageWidgetStepDefinitions extends TestCaseHelper {
    MessageWidget messageWidget;
    private static final Logger LOG = LoggerFactory.getLogger(MessageWidgetStepDefinitions.class);

    @And("should see current location name as {string}")
    public void shouldSeeCurrentLocationNameAs(String name) {
        messageWidget = PageObjectFactory.getMessageWidget(testWebDriver);
        assertEquals(name, messageWidget.getCurrentLocationName(), "Current Location Name is incorrect");
    }

    @And("should see current location address as {string}")
    public void shouldSeeCurrentLocationAddressAs(String address) {
        assertEquals(address, messageWidget.getCurrentLocationAddress(), "Current Location Address is incorrect");
    }

    @And("should see back button")
    public void shouldSeeBackButton() {
        assertTrue(messageWidget.isBackButtonDisplayed(), "Back button should be displayed");
    }

    @And("should see text invitation with message containing {string}")
    public void shouldSeeTextInvitationWithMessageContaining(String text) {
        assertTrue(messageWidget.getTextInvitationMessage().contains(text), "Invitation Text is not as expected, " +
                "expected to contain " + text + ", but found: " + messageWidget.getTextInvitationMessage());
    }

    @And("should see required input field for Name with label as {string}")
    public void shouldSeeRequiredInputFieldForNameWithLabelAs(String label) {
        assertEquals(label, messageWidget.getNameInputLabel(), "Name input label is incorrect");
        assertTrue(messageWidget.isNameInputRequired(), "Name input should be a required field");
    }

    @Then("I should not see name checkmark")
    public void iShouldNotSeeNameCheckmark() {
        assertFalse(messageWidget.isNameCheckmarkDisplayed(), "Name checkmark should not be displayed");
    }

    @And("should see input field for Mobile Phone with label as {string}")
    public void shouldSeeInputFieldForMobilePhoneWithLabelAs(String label) {
        assertEquals(label, messageWidget.getMobilePhoneInputLabel(), "Mobile Phone input label is incorrect");
    }

    @Then("I should not see mobile phone checkmark")
    public void iShouldNotSeeMobilePhoneCheckmark() {
        assertFalse(messageWidget.isMobilePhoneCheckmarkDisplayed(), "Mobile Phone checkmark should not be displayed");
    }

    @And("should see input field for Message with label as {string}")
    public void shouldSeeInputFieldForMessageWithLabelAs(String label) {
        assertEquals(label, messageWidget.getMessageInputLabel(), "Message input label is incorrect");
    }


    @And("should see inactive Send button")
    public void shouldSeeInactiveSendButton() {
        assertTrue(messageWidget.isSendButtonDisabled(), "Send button should be displayed");
        assertTrue(messageWidget.isSendButtonDisabled(), "Send button should be disabled");
    }

    @When("I enter Name input as {string}")
    public void iEnterNameInputAs(String input) {
        messageWidget = PageObjectFactory.getMessageWidget(testWebDriver);
        messageWidget.enterName(input);
    }

    @Then("I {string} see checkmark against name input field")
    public void iSeeNameCheckmark(String status) {
        switch (status) {
            case "should not" -> assertFalse(messageWidget.isNameCheckmarkDisplayed(),
                    "Checkmark against Name field should not be displayed");
            case "should" -> assertTrue(messageWidget.isNameCheckmarkDisplayed(),
                    "Checkmark against Name field should be displayed");
            default -> LOG.error("Incorrect expectation");
        }
    }

    @When("I enter Mobile Phone input as {string}")
    public void iEnterMobilePhoneInputAs(String input) {
        messageWidget = PageObjectFactory.getMessageWidget(testWebDriver);
        messageWidget.enterMobilePhone(input);
    }

    @Then("I {string} see checkmark against mobile phone input field")
    public void iSeeMobilePhoneCheckmark(String status) {
        switch (status) {
            case "should not" -> assertFalse(messageWidget.isMobilePhoneCheckmarkDisplayed(),
                    "Checkmark against Mobile Phone field should not be displayed");
            case "should" -> assertTrue(messageWidget.isMobilePhoneCheckmarkDisplayed(),
                    "Checkmark against Mobile Phone field should be displayed");
            default -> LOG.error("Incorrect expectation");
        }
    }

    @Then("I {string} see flag picker")
    public void iSeeFlag(String status) {
        switch (status) {
            case "should not" -> assertFalse(messageWidget.isFlagPickerDisplayed(),
                    "Flag picker against Mobile Phone field should not be displayed");
            case "should" -> assertTrue(messageWidget.isFlagPickerDisplayed(),
                    "Flag picker against Mobile Phone field should be displayed");
            default -> LOG.error("Incorrect expectation");
        }
    }

    @And("I enter Message input as {string}")
    public void iEnterMessageInputAs(String input) {
        messageWidget.enterMessage(input);
    }

    @Then("I should see Send button {string}")
    public void iShouldSeeSendButton(String status) {
        switch (status) {
            case "disabled" -> assertTrue(messageWidget.isSendButtonDisabled(),
                    "Flag picker against Mobile Phone field should not be displayed");
            case "enabled" -> assertTrue(messageWidget.isSendButtonEnabled(),
                    "Flag picker against Mobile Phone field should be displayed");
            default -> LOG.error("Incorrect expectation");
        }
    }

    @And("click on back button")
    public void clickOnBackButton() {
        messageWidget.clickOnBackButton();
    }

    @And("I should see Name input as {string}")
    public void iShouldSeeNameInputAs(String input) {
        assertEquals(input, messageWidget.getName(), "Name input is not as expected");
    }

    @And("I should see Mobile Phone input as {string}")
    public void iShouldSeeMobilePhoneInputAs(String input) {
        assertEquals(input, messageWidget.getMobilePhone(), "Mobile Phone input is not as expected");
    }

    @And("I should see Message input as {string}")
    public void iShouldSeeMessageInputAs(String input) {
        messageWidget = PageObjectFactory.getMessageWidget(testWebDriver);
        assertEquals(input, messageWidget.getMessage(), "Message input is not as expected");
    }
}
