package org.example.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidSyntax {
    private static final Pattern QUERY_PATTERN = Pattern.compile(
            "^\\s*SELECT\\s+(?<select>.+?)\\s+FROM\\s+(?<from>\\S+(?:\\s+AS\\s+\\S+)?)(?:\\s+WHERE\\s+(?<where>.+?))?\\s*$",
            Pattern.CASE_INSENSITIVE
    );

    public ValidSyntax() {

    }

    public boolean isValid(String query) {
        if(query == null || query.trim().isEmpty()){ return false; };
        Matcher matcher = QUERY_PATTERN.matcher(query);
        return matcher.matches();
    }


}
