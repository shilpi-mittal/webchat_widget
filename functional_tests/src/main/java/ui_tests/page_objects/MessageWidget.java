package ui_tests.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ui_tests.utils.TestWebDriver;

import static org.openqa.selenium.support.How.*;

public class MessageWidget extends Page {
    @FindBy(how = ID, using = "ComposeMessage")
    private static WebElement messageModal = null;

    @FindBy(how = CLASS_NAME, using = "SendSmsPage__HeaderContainer")
    private static WebElement smsPageHeader = null;

    @FindBy(how = CLASS_NAME, using = "SendSmsPage__CurrentLocationName")
    private static WebElement currentLocationName = null;

    @FindBy(how = CLASS_NAME, using = "SendSmsPage__ArrowIcon")
    private static WebElement backButton = null;

    @FindBy(how = CLASS_NAME, using = "SendSmsPage__CurrentLocationAddress")
    private static WebElement currentLocationAddress = null;

    @FindBy(how = ID, using = "Name")
    private static WebElement nameInput = null;

    @FindBy(how = CSS, using = "#Name ~ label")
    private static WebElement nameInputLabel = null;

    @FindBy(how = CSS, using = "#Name ~ div.TextInput__Checkmark")
    private static WebElement nameCheckmark = null;

    @FindBy(how = CSS, using = ".TextInput.TextInput--tel input")
    private static WebElement mobilePhoneInput = null;

    @FindBy(how = CSS, using = ".TextInput.TextInput--tel label")
    private static WebElement mobilePhoneInputLabel = null;

    @FindBy(how = CSS, using = ".TextInput.TextInput--tel .TextInput__Checkmark")
    private static WebElement mobilePhoneCheckmark = null;

    @FindBy(how = CLASS_NAME, using = "flag-picker")
    private static WebElement flagPicker = null;

    @FindBy(how = ID, using = "Message")
    private static WebElement messageTextArea = null;

    @FindBy(how = CSS, using = "#Message ~ label")
    private static WebElement messageTextAreaLabel = null;

    @FindBy(how = CLASS_NAME, using = "message-char-count")
    private static WebElement messageCharCount = null;

    private static String submittedMessageSelector = ".SubmittedMessage div:nth-child(2)";

    @FindBy(how = CSS, using = ".SendSmsPage__TextInvitation div")
    private static WebElement textInvitation = null;

    @FindBy(how = CLASS_NAME, using = "SendButton")
    private static WebElement sendButton = null;

    @FindBy(how = CLASS_NAME, using = "SendButton--incomplete")
    private static WebElement sendButtonIncomplete = null;

    @FindBy(how = CLASS_NAME, using = "SendButton--valid")
    private static WebElement sendButtonValid = null;

    private static final Logger LOG = LoggerFactory.getLogger(MessageWidget.class);

    public MessageWidget(TestWebDriver testWebDriver) {
        super(testWebDriver);
        PageFactory.initElements(new AjaxElementLocatorFactory(testWebDriver.getDriver(), 1), this);
    }

    public boolean isComposeMessageWidgetDisplayed() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        testWebDriver.waitForElementToAppear(messageModal);
        testWebDriver.waitForElementToAppear(nameInput);
        return testWebDriver.isDisplayed(messageModal);
    }

    public String getCurrentLocationName() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.getText(currentLocationName);
    }

    public String getCurrentLocationAddress() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.getText(currentLocationAddress);
    }

    public boolean isBackButtonDisplayed() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.isDisplayed(backButton);
    }

    public void clickOnBackButton() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        testWebDriver.clickOnElement(backButton);
        LOG.debug("Back button is clicked");
    }

    public String getNameInputLabel() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.getText(nameInputLabel);
    }

    public String getName() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.getAttribute(nameInput, "value");
    }

    public void enterName(String name) {
        testWebDriver.switchIFrame(widgetModalIframeID);
        testWebDriver.enterInput(nameInput, name);
        LOG.debug("Name is entered as " + name);
    }

    public boolean isNameCheckmarkDisplayed() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.isDisplayed(nameCheckmark);
    }

    public boolean isNameInputRequired() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.getAttribute(nameInput, "required") != null;
    }

    public String getMobilePhoneInputLabel() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.getText(mobilePhoneInputLabel);
    }

    public String getMobilePhone() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.getAttribute(mobilePhoneInput, "value");
    }

    public void enterMobilePhone(String number) {
        testWebDriver.switchIFrame(widgetModalIframeID);
        testWebDriver.enterInput(mobilePhoneInput, number);
        LOG.debug("Mobile phone is entered as " + number);
    }

    public boolean isMobilePhoneCheckmarkDisplayed() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.isDisplayed(mobilePhoneCheckmark);
    }

    public boolean isFlagPickerDisplayed() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.isDisplayed(flagPicker);
    }

    public String getMessageInputLabel() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.getText(messageTextAreaLabel);
    }

    public String getMessage() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.findElement(By.cssSelector(submittedMessageSelector)).getAttribute("textContent");
    }

    public void enterMessage(String message) {
        testWebDriver.switchIFrame(widgetModalIframeID);
        testWebDriver.enterInput(messageTextArea, message);
        LOG.debug("Message is entered as " + message);
    }

    public boolean isMessageCharCountDisplayed() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.isDisplayed(messageCharCount);
    }

    public boolean isSendButtonDisplayed() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.isDisplayed(sendButton);
    }

    public boolean isSendButtonDisabled() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.isDisplayed(sendButtonIncomplete);
    }

    public boolean isSendButtonEnabled() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.isDisplayed(sendButtonValid);
    }

    public boolean isTextInvitationDisplayed() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.isDisplayed(textInvitation);
    }

    public String getTextInvitationMessage() {
        testWebDriver.switchIFrame(widgetModalIframeID);
        return testWebDriver.getText(textInvitation);
    }
}
