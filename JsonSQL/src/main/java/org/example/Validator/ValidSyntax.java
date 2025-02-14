package org.example.Validator;

import org.example.Model.ParsedQueryFields;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidSyntax {
    private static final Pattern QUERY_PATTERN = Pattern.compile(
            "^\\s*SELECT\\s+(?<select>.+?)\\s+FROM\\s+(?<from>\\S+(?:\\s+AS\\s+\\S+)?)(?:\\s+WHERE\\s+(?<where>.+?))?\\s*$",
            Pattern.CASE_INSENSITIVE
    );

    public ValidSyntax() {

    }

    ParsedQueryFields parsedQueryFields = new ParsedQueryFields();

    public ParsedQueryFields parse(String query) {
        if (query == null || query.trim().isEmpty()) {
            throw new IllegalArgumentException("Query cannot be null or empty");
        }

        Matcher matcher = QUERY_PATTERN.matcher(query.trim());
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid query format");
        }

        ParsedQueryFields parsedQueryFields = new ParsedQueryFields();
        parsedQueryFields.setSelect(matcher.group("select").trim());
        parsedQueryFields.setFrom(matcher.group("from").trim());

        if (matcher.group("alias") != null) {
            parsedQueryFields.setAlias(matcher.group("alias").trim());
        }

        if (matcher.group("where") != null) {
            parsedQueryFields.setWhere(matcher.group("where").trim());
        }

        return parsedQueryFields;

    }







}
