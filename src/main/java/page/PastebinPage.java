package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PastebinPage {

    private WebDriver driver;

    @FindBy(id = "postform-text")
    private WebElement codeTextArea;

    @FindBy(id = "select2-postform-format-container")
    private WebElement syntaxHighlightDropdown;

    @FindBy(xpath = "//li[text()='Bash']")
    private WebElement syntaxHighlightingSelect;

    @FindBy(id = "select2-postform-expiration-container")
    private WebElement pasteExpirationDropdown;

    @FindBy(xpath = "//li[text()='10 Minutes']")
    private WebElement pasteExpirationSelect;

    @FindBy(id = "postform-name")
    private WebElement pasteNameField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement createButton;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div[1]/h1")
    private WebElement pasteTitle;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div[1]/div[4]/div[1]/div[1]/a[1]")
    private WebElement pasteSyntaxHighlighting;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[1]/div[1]/div[4]/div[2]/ol/li/div")
    private WebElement pasteCode;


    public PastebinPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openPage(String url) {
        driver.get(url);
    }

    public void writePaste(String code) {
        codeTextArea.sendKeys(code);

//        Select syntaxHighlightingSelect = new Select(syntaxHighlightingDropdown);
//        syntaxHighlightingSelect.selectByVisibleText(syntaxHighlighting);

//        Select pasteExpirationSelect = new Select(pasteExpirationDropdown);
//        pasteExpirationSelect.selectByVisibleText(pasteExpiration);

//        pasteNameField.sendKeys(pasteName);

//        createButton.click();
    }

    public void syntaxHighlightClick(){
        syntaxHighlightDropdown.click();
        syntaxHighlightingSelect.click();

    }


    public void setPasteExpiration(){
        waitForElementLocatedBy(driver, Duration.ofSeconds(10)  ,By.id("select2-postform-expiration-container")).click();
        pasteExpirationSelect.click();
    }

    public void pasteNameWriter(String pasteName){
        pasteNameField.sendKeys(pasteName);
    }

    public void CreateButtonClick(){
        createButton.click();
    }


    public String getPasteTitle() {
        return pasteTitle.getText();
    }

    public String getPasteSyntaxHighlighting() {
        return pasteSyntaxHighlighting.getText();
    }

    public String getPasteCode() {
        return pasteCode.getText();
    }

    private static WebElement waitForElementLocatedBy(WebDriver driver, Duration timeout, By by) {
        return new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
