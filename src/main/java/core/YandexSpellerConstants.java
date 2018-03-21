package core;

/**
 * Constants of YandexSpeller
 */
public class YandexSpellerConstants {

    //useful constants for API under test
    public static final String YANDEX_SPELLER_API_URI = "https://speller.yandex.net/services/spellservice.json/checkText";
    public static final String PARAM_TEXT = "text";
    public static final String PARAM_OPTIONS = "options";
    public static final String CUSTOM_PARAMETER = "CustomParameter";
    public static final String VALUEOF_PARAMETER = "valueOfParam";
    public static final String USERNAME_ABC_NAME = "abcName";
    public static final String USERPASSWORD_ABC_PASSWORD = "abcPassword";
    public static final String BODY_SOME_BODY_PAYROLL = "some body payroll";
    public static final String HEADERNAME_CUSTOM_HEADER_1 = "custom header1";
    public static final String HEADERVALUE_HEADER_1_VALUE = "header1.value";
    public static final String HTTP_405_METHOD_NOT_ALLOWED = "HTTP/1.1 405 Method not allowed";
    public static final String PARAM_LANG = "lang";
    public static final String EXPECTED_NUMBER_OF_ANSWERS_IS_WRONG = "expected number of answers is wrong.";

    //request with answers values constants
    public static final String WRONG_WORD_EN = "requisitee";
    public static final String RIGHT_WORD_EN = "requisite";
    public static final String WRONG_WORD_UK = "питаня";
    public static final String WORD_WITH_WRONG_CAPITAL = "moscow";
    public static final String WORD_WITH_LEADING_DIGITS = "11" + RIGHT_WORD_EN;
    public static final String WRONG_WORD_MOTHER_FATHER = "motherr fatherr,";
    public static final String WRONG_WORD_MOTHER = "motherr";
    public static final String WRONG_WORD_FATHER = "fatherr";
    public static final String RIGHT_WORD_MOTHER = "mother";
    public static final String RIGHT_WORD_FATHER = "father";
    public static final String WRONG_CITY_NEW_YORK = "New Yrk";
    public static final String CITY_NEW_YORK_LIST = "[new york, New york, new York, NEW YORK]";
    public static final String TOMATO_LIST = "[tomato, tom3to, TOMATO]";
    public static final String WRONG_TOMATO = "tomato";

    //options constants
    public static final String IGNORE_DIGITS = "2";
    public static final String IGNORE_URLS = "4";
    public static final String FIND_REPEAT_WORDS = "8";
    public static final String OPTION_5 = "5";
    public static final String OPTION_6 = "6";
    public static final String IGNORE_CAPITALIZATION = "512";


    public enum Languages {
        RU("ru"),
        UK("uk"),
        EN("en");
        String languageCode;

        Languages(String lang) {
            this.languageCode = lang;
        }
    }
}
