import beans.YandexSpellerAnswer;
import core.YandexSpellerApi;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static core.YandexSpellerConstants.*;

public class TestYandexSpellerREST {

    @DataProvider
    public Object[][] SpellTestData() {
        return new Object[][]{
                {WRONG_CITY_NEW_YORK, CITY_NEW_YORK_LIST},
                {WRONG_TOMATO, TOMATO_LIST}
        };
    }

    @Test(dataProvider = "SpellTestData")
    public void checkFixingTypos(String sentText, String expectedText) {
        List<YandexSpellerAnswer> answersList =
                YandexSpellerApi.getYandexSpellerAnswers(YandexSpellerApi
                        .with()
                        .options(IGNORE_CAPITALIZATION)
                        .options(IGNORE_DIGITS)
                        .text(sentText)
                        .callApi());

        MatcherAssert.assertThat(answersList.get(0).word, Matchers.equalTo(sentText));
        MatcherAssert.assertThat(answersList.get(0).s.toString(), Matchers.equalTo(expectedText));
    }
}
