package tests;

import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test(dataProvider = "dP1")
    public void openGoogleComTest(String text) throws InterruptedException {

        steps.executeSearchByKeyword(text)
                .verifyThatTopResultContainsCorrectText("WebDriver")
                .verifyThatTopResultContainsProperAttributeText("LC20");

//высокоуровневый тест, который содержит шаги, проверки и тестовые данные
    }

    @Test
    public void verifySearchByVoiceTooltipOnGoogle() {
        steps.openTooltip()
                .verifyThatTooltipContainsProperText("Голосовой набор");
    }
}
