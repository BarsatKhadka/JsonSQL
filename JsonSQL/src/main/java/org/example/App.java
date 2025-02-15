package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.Model.ParsedQueryFields;
import org.example.Validator.ValidSyntax;
import org.example.core.QueryExecutor;
import org.example.core.QueryParser;
import org.example.utility.JsonLoader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        JsonLoader jsonLoader = new JsonLoader();
        JsonNode output = jsonLoader.load("https://jsonplaceholder.typicode.com/posts/1");
        System.out.println(output);
        ValidSyntax validSyntax = new ValidSyntax();
        String query = "SELECT name, age FROM users  WHERE age > 25 AS dkfkjdahfjds ";
        QueryExecutor queryExecutor = new QueryExecutor();
        queryExecutor.executeQuery(query);

    }
}
