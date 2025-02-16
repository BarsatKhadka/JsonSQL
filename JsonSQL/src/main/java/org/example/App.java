package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.Validator.ValidSyntax;
import org.example.core.QueryExecutor;
import org.example.utility.JsonLoader;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) {
        JsonLoader jsonLoader = new JsonLoader();
        JsonNode output = jsonLoader.load("{\n" +
                "  \"fruits\": [\"apple\", \"banana\", \"orange\", \"grape\"],\n" +
                "  \"vegetables\": [\"carrot\", \"broccoli\", \"spinach\", \"tomato\"],\n" +
                "  \"animals\": [\"dog\", \"cat\", \"elephant\", \"lion\"],\n" +
                "  \"numbers\": [1, 2, 3, 4, 5],\n" +
                "  \"colors\": [\"red\", \"green\", \"blue\", \"yellow\"]\n" +
                "}\n");
        ValidSyntax validSyntax = new ValidSyntax();
        String query = "SELECT apple FROM fruits";
        QueryExecutor queryExecutor = new QueryExecutor();
        System.out.println(queryExecutor.executeQuery(query, output));


    }
}
