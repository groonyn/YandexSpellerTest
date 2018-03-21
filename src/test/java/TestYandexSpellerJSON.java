import beans.YandexSpellerAnswer;
import core.YandexSpellerApi;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static core.YandexSpellerConstants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;


/**
 * Simple usage of RestAssured library
 */
public class TestYandexSpellerJSON {

    // simple usage of RestAssured library: direct request call and response validations in test.
    @Test
    public void simpleSpellerApiCall() {
        RestAssured
                .given()
                .queryParam(PARAM_TEXT, WRONG_WORD_EN)
                .params(PARAM_LANG, Languages.EN, CUSTOM_PARAMETER, VALUEOF_PARAMETER)
                .accept(ContentType.JSON)
                .auth().basic(USERNAME_ABC_NAME, USERPASSWORD_ABC_PASSWORD)
                .header(HEADERNAME_CUSTOM_HEADER_1, HEADERVALUE_HEADER_1_VALUE)
                .and()
                .body(BODY_SOME_BODY_PAYROLL)
                .log().everything()
                .when()
                .get(YANDEX_SPELLER_API_URI)
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(Matchers.allOf(
                        Matchers.stringContainsInOrder(Arrays.asList(WRONG_WORD_EN, RIGHT_WORD_EN)),
                        Matchers.containsString("\"code\":1")))
                .contentType(ContentType.JSON)
                .time(lessThan(20000L)); // Milliseconds
    }

    // different http methods calls
    @Test
    public void spellerApiCallsWithDifferentMethods() {
        //GET
        RestAssured
                .given()
                .param(PARAM_TEXT, WRONG_WORD_EN)
                .log().everything()
                .get(YANDEX_SPELLER_API_URI)
                .prettyPeek();
        System.out.println("\n=====================================================================");

        //POST
        RestAssured
                .given()
                .param(PARAM_TEXT, WRONG_WORD_EN)
                .log().everything()
                .post(YANDEX_SPELLER_API_URI)
                .prettyPeek();
        System.out.println("\n=====================================================================");

        //HEAD
        RestAssured
                .given()
                .param(PARAM_TEXT, WRONG_WORD_EN)
                .log().everything()
                .head(YANDEX_SPELLER_API_URI)
                .prettyPeek();
        System.out.println("\n=====================================================================");

        //OPTIONS
        RestAssured
                .given()
                .param(PARAM_TEXT, WRONG_WORD_EN)
                .log().everything()
                .options(YANDEX_SPELLER_API_URI)
                .prettyPeek();
        System.out.println("\n=====================================================================");

        //PUT
        RestAssured
                .given()
                .param(PARAM_TEXT, WRONG_WORD_EN)
                .log().everything()
                .put(YANDEX_SPELLER_API_URI)
                .prettyPeek();
        System.out.println("\n=====================================================================");

        //PATCH
        RestAssured
                .given()
                .param(PARAM_TEXT, WRONG_WORD_EN)
                .log()
                .everything()
                .patch(YANDEX_SPELLER_API_URI)
                .prettyPeek();
        System.out.println("\n=====================================================================");

        //DELETE
        RestAssured
                .given()
                .param(PARAM_TEXT, WRONG_WORD_EN)
                .log()
                .everything()
                .delete(YANDEX_SPELLER_API_URI).prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED)
                .statusLine(HTTP_405_METHOD_NOT_ALLOWED);
    }


    // use base request and response specifications to form request and validate response.
    @Test
    public void useBaseRequestAndResponseSpecifications() {
        RestAssured
                .given(YandexSpellerApi.baseRequestConfiguration())
                .param(PARAM_TEXT, WRONG_WORD_EN)
                .get().prettyPeek()
                .then().specification(YandexSpellerApi.successResponse());
    }

    @Test
    public void reachBuilderUsage() {
        YandexSpellerApi.with()
                .language(Languages.UK)
                .options(OPTION_5)
                .text(WRONG_WORD_UK)
                .callApi()
                .then().specification(YandexSpellerApi.successResponse());
    }


    //validate an object we've got in API response
    @Test
    public void validateSpellerAnswerAsAnObject() {
        List<YandexSpellerAnswer> answers =
                YandexSpellerApi.getYandexSpellerAnswers(
                        YandexSpellerApi.with().text(WRONG_WORD_MOTHER_FATHER + WRONG_WORD_EN).callApi());
        assertThat(EXPECTED_NUMBER_OF_ANSWERS_IS_WRONG, answers.size(), equalTo(3));
        assertThat(answers.get(0).word, equalTo(WRONG_WORD_MOTHER));
        assertThat(answers.get(1).word, equalTo(WRONG_WORD_FATHER));
        assertThat(answers.get(0).s.get(0), equalTo(RIGHT_WORD_MOTHER));
        assertThat(answers.get(1).s.get(0), equalTo(RIGHT_WORD_FATHER));
        assertThat(answers.get(2).s.get(0), equalTo(RIGHT_WORD_EN));
    }


    @Test
    public void optionsValueIgnoreDigits() {
        List<YandexSpellerAnswer> answers =
                YandexSpellerApi.getYandexSpellerAnswers(
                        YandexSpellerApi.with().
                                text(WORD_WITH_LEADING_DIGITS)
                                .options(IGNORE_DIGITS)
                                .callApi());
        assertThat(EXPECTED_NUMBER_OF_ANSWERS_IS_WRONG, answers.size(), equalTo(0));
    }

    @Test
    public void optionsIgnoreWrongCapital() {
        List<YandexSpellerAnswer> answers =
                YandexSpellerApi.getYandexSpellerAnswers(
                        YandexSpellerApi.with().
                                text(WORD_WITH_WRONG_CAPITAL)
                                .options(IGNORE_CAPITALIZATION)
                                .callApi());
        assertThat(EXPECTED_NUMBER_OF_ANSWERS_IS_WRONG, answers.size(), equalTo(0));
    }
}
