package Test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.PastebinPage;

import java.util.concurrent.TimeUnit;

public class PastebinTestJUnit {
    private WebDriver driver;
    private PastebinPage pastebinPageObj;


    @Before
    public void setUp() {
        driver = new ChromeDriver();
        pastebinPageObj = new PastebinPage(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        pastebinPageObj.openPage("https://pastebin.com");
    }

    @Test
    public void testCreateNewPaste() {
        pastebinPageObj.writePaste(
                """
                        git config --global user.name  "New Sheriff in Town"
                        git reset $(git commit-tree HEAD^{tree} -m "Legacy code")
                        git push origin master --force"""
                );
        pastebinPageObj.syntaxHighlightClick();
//        pastebinPageObj.setSyntaxHighlighting();
        pastebinPageObj.setPasteExpiration();
        pastebinPageObj.pasteNameWriter("how to gain dominance among developers");
        pastebinPageObj.CreateButtonClick();



        String expectedTitle = "how to gain dominance among developers";
        String expectedSyntax = "Bash";
        String expectedCode = """
                git config --global user.name  "New Sheriff in Town"
                git reset $(git commit-tree HEAD^{tree} -m "Legacy code")
                git push origin master --force""";

        Assert.assertEquals(expectedTitle, pastebinPageObj.getPasteTitle());
        Assert.assertEquals(expectedSyntax, pastebinPageObj.getPasteSyntaxHighlighting());
        Assert.assertEquals(expectedCode, pastebinPageObj.getPasteCode());


    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
