package org.example.core;

import org.example.Model.ParsedQueryFields;
import org.example.Validator.ValidSyntax;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class QueryParser {
    private final ValidSyntax validSyntax;

    public QueryParser(){
        this.validSyntax = new ValidSyntax();
    }


    public Map<String,Object> parseQuery(String query){

        Map<String,Object> result = new HashMap<>();
        Set<Object> selectFields = new HashSet();
        Set<Object> aliasFields = new HashSet();

        ParsedQueryFields parsedQueryFields =  validSyntax.parse(query);
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

        if(alias.contains(",")){
            String[] fields = alias.split(",");
            for(String field : fields){
                String trimmedField = field.trim();
                Object aliasItem = parseField(trimmedField);
                aliasFields.add(aliasItem);
            }
        }

        result.put("select",selectFields);
        result.put("from",from);
        result.put("where",where);
        result.put("alias",aliasFields);

        return result;
    }

    //helper class to convert to object
    private Object parseField(String field) {
        try {
            return Double.parseDouble(field);
        } catch (NumberFormatException e) {
            return field;
        }
    }

    }

