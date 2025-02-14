package org.example.core;

import org.example.Model.ParsedQueryFields;

public class QueryExecutor {
    public void executeQuery(ParsedQueryFields parsedQueryFields) {
        String select = parsedQueryFields.getSelect();
        String from = parsedQueryFields.getFrom();
        String where = parsedQueryFields.getWhere();
        String alias  = parsedQueryFields.getAlias();

        System.out.println(select);
        System.out.println(from);
        System.out.println(where);
        System.out.println(alias);
    }
}
