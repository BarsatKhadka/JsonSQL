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
        String jsonArrayString = "{"
                + "\"employees\": ["
                + "  {"
                + "    \"id\": 1,"
                + "    \"name\": \"John Doe\","
                + "    \"age\": 30,"
                + "    \"address\": {"
                + "      \"street\": \"123 Main St\","
                + "      \"city\": \"New York\","
                + "      \"state\": \"NY\","
                + "      \"postalCode\": \"10001\""
                + "    },"
                + "    \"skills\": [\"Java\", \"Python\", \"SQL\"],"
                + "    \"projects\": ["
                + "      {"
                + "        \"name\": \"Project A\","
                + "        \"status\": \"Completed\","
                + "        \"teamSize\": 5"
                + "      },"
                + "      {"
                + "        \"name\": \"Project B\","
                + "        \"status\": \"In Progress\","
                + "        \"teamSize\": 8"
                + "      }"
                + "    ]"
                + "  },"
                + "  {"
                + "    \"id\": 2,"
                + "    \"name\": \"Jane Smith\","
                + "    \"age\": 25,"
                + "    \"address\": {"
                + "      \"street\": \"456 Elm St\","
                + "      \"city\": \"Los Angeles\","
                + "      \"state\": \"CA\","
                + "      \"postalCode\": \"90001\""
                + "    },"
                + "    \"skills\": [\"JavaScript\", \"React\", \"Node.js\"],"
                + "    \"projects\": ["
                + "      {"
                + "        \"name\": \"Project X\","
                + "        \"status\": \"In Progress\","
                + "        \"teamSize\": 6"
                + "      }"
                + "    ]"
                + "  }"
                + "],"
                + "\"department\": {"
                + "  \"name\": \"Engineering\","
                + "  \"manager\": \"Alice Johnson\","
                + "  \"location\": \"San Francisco\""
                + "}"
                + "}";
        ;

        JsonLoader jsonLoader = new JsonLoader();
        JsonNode output = jsonLoader.load(jsonArrayString);
        ValidSyntax validSyntax = new ValidSyntax();
        String query = "SELECT id FROM employees";
        QueryExecutor queryExecutor = new QueryExecutor();
        System.out.println(queryExecutor.executeQuery(query, output));


    }
}
