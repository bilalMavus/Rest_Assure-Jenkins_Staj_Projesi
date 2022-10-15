package Tools;

import org.apache.commons.lang3.RandomStringUtils;

public class Random {

    public static String getRandomName() {

        return RandomStringUtils.randomAlphabetic(8).toLowerCase();
    }

    public static String getRandomCode() {

        return RandomStringUtils.randomAlphabetic(3).toLowerCase();
    }

    public String getRandomSale() {

        return RandomStringUtils.randomAlphabetic(2).toLowerCase();
    }


}
