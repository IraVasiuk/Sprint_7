package constants;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomData {
    //генерирование случайных значений
    public static String RANDOM_LOGIN = RandomStringUtils.randomAlphabetic(10);
    public static String RANDOM_PASS = RandomStringUtils.randomNumeric(10);
    public static String RANDOM_NAME = RandomStringUtils.randomAlphabetic(10);
}
