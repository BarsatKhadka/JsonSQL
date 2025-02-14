package org.example.core;

import org.example.Model.ParsedQueryFields;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class QueryExecutor {
    Set<Object> selectFields = new HashSet();
    public void executeQuery(ParsedQueryFields parsedQueryFields) {
        String select = parsedQueryFields.getSelect();
        String from = parsedQueryFields.getFrom();
        String where = parsedQueryFields.getWhere();
        String alias  = parsedQueryFields.getAlias();

        if(select.isEmpty()  || from.isEmpty()){
            throw new IllegalArgumentException("select or from statement missing in JsonSQL.");
        }

        if(select.contains(",")){
            String[] fields = select.split(",");
            for(String field : fields){
                String trimmedField = field.trim();
                Object selectItem = parseField(trimmedField);
                selectFields.add(selectItem);
            }
        }
        else{
            Object selectItem = parseField(select);
            selectFields.add(selectItem);
        }

        System.out.println(selectFields);

    }
    private Object parseField(String field) {
        try {
            return Double.parseDouble(field);
        } catch (NumberFormatException e) {
            return field;
        }
    }
}
