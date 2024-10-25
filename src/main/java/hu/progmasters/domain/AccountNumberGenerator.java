package hu.progmasters.domain;

import java.security.SecureRandom;
import java.util.List;

public class AccountNumberGenerator {


    private static final List<String> ACCOUNT_NUMBER_COMPONENTS = List.of("A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N", "O", "P");
    private static final int ITER_NUMBERS = 5;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateAccountNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ITER_NUMBERS; i++) {
            int randomNumber = RANDOM.nextInt(ACCOUNT_NUMBER_COMPONENTS.size());
            sb.append(ACCOUNT_NUMBER_COMPONENTS.get(randomNumber));
        }
        return sb.append("-").append(System.nanoTime()).toString();
    }


}
