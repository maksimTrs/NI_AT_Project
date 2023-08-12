package nisfappui.utils;

import net.datafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AppDataFaker {

    public static String getRandomEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    public static String getRandomTradeName() {
        Faker faker = new Faker();
        return "AT TEST " + faker.company().name();
    }

    public static int getRandomIntValue(int start, int end) {
        Faker faker = new Faker();
        return faker.number().numberBetween(start, end);
    }

    public static double getRandomDoubleValue(int start, int end) {
        Faker faker = new Faker();
        return faker.number().randomDouble(2, start, end);
    }

    public static String getRandomPhone() {
        // Faker faker = new Faker(new Locale("ar-AE"));
        Faker faker = new Faker();
        return faker.numerify("971########");
    }

    public static String getRandomPOBox() {
        Faker faker = new Faker();
        return faker.address().postcode();
    }

    public static String getRandomWebURL() {
        Faker faker = new Faker();
        return faker.internet()
                .url(false, false, true, false, false, false);
    }

    public static String getRandomAddress() {
        Faker faker = new Faker();
        return faker.address().fullAddress();
    }

    public static long getRandomTLN() {
        Faker faker = new Faker();
        return faker.number().randomNumber(15, true);
    }

    public static String getRandomDateOfEstablishment() {
        Faker faker = new Faker();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date initialDate = dateFormatter.parse("25/01/2018");
            Date currentDate = new Date();
            Date endDate = new Date(currentDate.getTime() - TimeUnit.DAYS.toMillis(4));

            return dateFormatter.format(faker.date().between(initialDate, endDate));
        } catch (Exception e) {
            e.printStackTrace();
            return String.valueOf(new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(7)));
        }
    }


    public static String getRandomDateOfBirth() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Faker faker = new Faker();
        return sdf.format(faker.date().birthday(18, 79));
    }


    public static String getRandomLicenceExpirationDate() {
        Faker faker = new Faker();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date currentDate = new Date();
            Date initDate = new Date(currentDate.getTime() + TimeUnit.DAYS.toMillis(4));
            Date endDate = new Date(currentDate.getTime() + TimeUnit.DAYS.toMillis(2520));

            return dateFormatter.format(faker.date().between(initDate, endDate));
        } catch (Exception e) {
            e.printStackTrace();
            return String.valueOf(new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(900)));
        }
    }


    public static String getRandomFirstName() {
        Faker faker = new Faker();
        return faker.name().firstName();
    }

    public static String getRandomLastName() {
        Faker faker = new Faker();
        return faker.name().lastName();
    }

    public static String getRandomPassport() {
        Faker faker = new Faker();
        return faker.passport().valid();
    }
}
