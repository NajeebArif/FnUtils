package narif.personal.work.fnutils.test.utlities;

import narif.personal.work.fnutils.components.Effect;
import narif.personal.work.fnutils.components.Result;
import narif.personal.work.fnutils.functions.Fn;

import java.util.regex.Pattern;

public class EmailValidation {

    private static Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

    private static Fn<String, Result<String>> emailChecker = s -> {
        if (s == null)
            return Result.failure("Email must not be null.");
        else if (s.isEmpty())
            return Result.failure("Email must not be empty.");
        else if (emailPattern.matcher(s).matches())
            return Result.success(s);
        else
            return Result.failure(() -> "Email " + s + " is invalid");
    };

    private static Effect<String> failure = f -> System.err.println("Error Message Logged: " + f);
    private static Effect<String> success = s -> System.out.println("Mail Sent to user: " + s);

    public static void main(String[] args) {
        emailChecker.apply("najeeb.arif@oracle.com").bind(success, failure);
        emailChecker.apply(null).bind(success, failure);
        emailChecker.apply("").bind(success, failure);
    }

}
