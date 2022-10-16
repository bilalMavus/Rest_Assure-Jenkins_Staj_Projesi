package Tools;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Locale;

public class Random {

    public static String getRandomName() {

        return RandomStringUtils.randomAlphabetic(8).toUpperCase();
    }

    public static String getRandomStringCode(int number) {

        return RandomStringUtils.randomAlphabetic(number).toUpperCase();
    }

    public static String getRandomIntCode(int number) {

        return RandomStringUtils.randomNumeric(number);
    }


}
