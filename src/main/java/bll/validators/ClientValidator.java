package bll.validators;

import model.Client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ClientValidator {
    private static final String EMAIL_PATTERN = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final String NAME_PATTERN = "\\b([A-ZÀ-ÿ][-,a-z. ']+[ ]*)+";
    private static final String PHONE_PATTERN = "^[+]*[(]?[0-9]{1,4}[)]?[-\\s./0-9]*$";

    public static void validate(Client client) {
        Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
        Pattern namePattern = Pattern.compile(NAME_PATTERN);
        Pattern phonePattern = Pattern.compile(PHONE_PATTERN);

        Matcher emailMatcher = emailPattern.matcher(client.getEmail());
        Matcher nameMatcher = namePattern.matcher(client.getName());
        Matcher phoneMatcher = phonePattern.matcher(client.getPhone());

        if(!nameMatcher.matches())
            try {
                throw new RuntimeException("Invalid Name.");
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        if(!emailMatcher.matches())
            try {
                throw new RuntimeException("Invalid Email Address.");
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        if(client.getPhone().length() == 0 || client.getPhone().length() < 7 || client.getPhone().length() > 15 || !phoneMatcher.matches())
            try {
                throw new RuntimeException("Invalid Phone Number.");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
    }
}
