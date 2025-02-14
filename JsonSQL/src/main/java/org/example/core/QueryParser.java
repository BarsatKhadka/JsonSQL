package org.example.core;

import org.example.Model.ParsedQueryFields;
import org.example.Validator.ValidSyntax;

public class QueryParser {
    private final ValidSyntax validSyntax;

    public QueryParser(){
        this.validSyntax = new ValidSyntax();
    }

    public ParsedQueryFields parseQuery(String query){
        return validSyntax.parse(query);
    }



}
