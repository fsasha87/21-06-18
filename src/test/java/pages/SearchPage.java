package pages;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage extends BasePage {
    @FindBy(name = "q")
    private WebElement searchField;

    @FindBy(name = "btnK")
    private WebElement searchButton;

    @FindBy(xpath = "//body")
    private WebElement pageBody;

    @FindBy(css = "div.XDyW0e")
    private WebElement searchByVoiceButton;

    //    private By searchField = By.name("q");//создали єлемент типа By, єто еще не вебєлемент
    public SearchPage() {
        super();
    }

    public void fillSearchField(String text) {
        searchField.click();
        searchField.sendKeys(text);
    }

    public void pasteToSearchField(String text) {
        pasteTextToElementFromClipboard(searchField, text);
    }

    public void pressEnter() {
        searchField.sendKeys(Keys.RETURN);
    }

    public void clickSearchButtonOrPressEnter() throws InterruptedException {
        if (isElementFound(By.name("btnK"), 3)) {
            wait.until(ExpectedConditions.elementToBeClickable(searchButton));
//            searchButton.click();
            clickWithJavaScript(searchButton);
        } else {
            pressEnter();
        }
    }

    public void moveToVoiceSearchButton() {
        builder.moveToElement(searchByVoiceButton).build().perform();
    }//наводим курсор на єлемент для подсказки

    public void assertThatVoiceSearchTooltipContainsText(String text) {
        Assertions.assertThat(pageBody.findElements(By.cssSelector("div.XDyW0e")).size())
                .as("Expected tooltip was not found").isNotZero();
    }//проверка найден ли елемент на странице с заданым текстом

}
