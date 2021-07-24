package pages;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResultPage extends BasePage {
    @FindBy(xpath = "//*[@id='rso']/div[@class='g']//h3")
    private WebElement resultRow;
//    private By resultRow = By.xpath("//*[@id='rso']/div[2]/div/div/div[1]/a/h3");

    @FindBy(xpath = "//*[@id='rso']/div[@class='g']//h3")
    private List<WebElement> resultRows;//получили список веб-єлементов

    public SearchResultPage() {
        super();
    }

    //    public void assertThatTopResultContainsCorrectText(String expected) {
//        Assertions.assertThat(resultRow.isDisplayed()).as("Wrong text").isTrue();
//        Assertions.assertThat(resultRow.getText()).as("Wrong text").isEqualTo(expected);
//    }
//
    public void assertThatTopResultContainsCorrectText(String expected) {
        wait.until(ExpectedConditions.visibilityOfAllElements(resultRows));//ждем пока будут видні все єлементы списка
//        Assertions.assertThat(resultRow.isDisplayed()).as("Wrong text").isTrue();
        Assertions.assertThat(resultRows.stream().map(e -> e.getText()).collect(Collectors.toList()).toString())
                .as("Wrong text").contains(expected);//превращаем список вебэлементов в список строк
    }

    public void assertThatTopResultContainsProperAttributeText(String expected) {
        Assertions.assertThat(resultRow.getAttribute("class")).as("Wrong text").contains(expected);
    }
}
