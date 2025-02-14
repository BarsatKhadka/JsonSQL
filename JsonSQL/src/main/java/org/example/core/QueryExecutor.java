package org.example.core;

import org.example.Model.ParsedQueryFields;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class QueryExecutor {

    public void executeQuery(String query) {
        QueryParser queryParser = new QueryParser();
        Set<Object> selectedFields = queryParser.parseQuery(query);
        System.out.println(selectedFields);

    }

}
