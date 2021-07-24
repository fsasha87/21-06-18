package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import static tests.BaseTest.getDriver;


public abstract class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    Actions builder;
    JavascriptExecutor executor;

    public BasePage() {
        this.driver = getDriver();
        PageFactory.initElements(driver, this);//запускает линивую инициализацию
        wait = new WebDriverWait(driver, 5);
        builder = new Actions(driver);
        executor = (JavascriptExecutor) driver;
    }

    boolean isElementFound(By by, int timeout) throws InterruptedException {
        List<WebElement> elements = driver.findElements(by);//сохраняем в лист найденые элементы
        for (int i = 0; (i < timeout) && (elements.size() == 0); i++) {
            Thread.sleep(1000);//будет повторять ожидание каждую секунду пока найдутся элементы или истечет таймаут
            elements = driver.findElements(by);//повторно ищем элементы, чт об понять появился ли элемент
        }
        return elements.size() > 0;
    }

    void pasteTextToElementFromClipboard(WebElement element, String text) {//метод будет вставлять строку из буфера обмена
        Toolkit toolkit = Toolkit.getDefaultToolkit();//класс отвечает за помещение текста в буфер
        Clipboard clipboard = toolkit.getSystemClipboard();
        StringSelection stringSelection = new StringSelection(text);
        clipboard.setContents(stringSelection, null);
        element.sendKeys(Keys.CONTROL, "v");
    }

    void clickWithJavaScript(WebElement element) {
        executor.executeScript("arguments[0].click()", element);
    }

}
