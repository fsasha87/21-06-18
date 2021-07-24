package steps;

import pages.SearchPage;

public class SearchSteps {
    private SearchPage searchPage = new SearchPage();//обращается к странице SearchPage

    public SearchResultsSteps executeSearchByKeyword(String keyword) throws InterruptedException {
//        searchPage.fillSearchField(keyword);
        searchPage.pasteToSearchField(keyword);
        searchPage.clickSearchButtonOrPressEnter();

        Thread.sleep(3000);//добавили ожидание с исключением
        return new SearchResultsSteps();//метод вернет объект новой страницы, чтоб перенаправится на нее
    }

    public SearchSteps openTooltip() {
        searchPage.moveToVoiceSearchButton();
        return this;
    }

    public SearchSteps verifyThatTooltipContainsProperText(String text) {
        searchPage.assertThatVoiceSearchTooltipContainsText(text);
        return this;
    }
}
