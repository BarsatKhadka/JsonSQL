package org.barsatKhadka;

import com.fasterxml.jackson.databind.JsonNode;
import org.barsatKhadka.core.JsonBySQL;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        String input = ("{\n" +
                "  \"fruits\": [\n" +
                "    {\"name\": \"apple\", \"color\": \"red\", \"age\": 10},\n" +
                "    {\"name\": \"banana\", \"color\": \"yellow\", \"age\": 5},\n" +
                "    {\"name\": \"orange\", \"color\": \"orange\", \"age\": 8},\n" +
                "    {\"name\": \"grape\", \"color\": \"purple\", \"age\": 12}\n" +
                "  ],\n" +
                "  \"vegetables\": [\n" +
                "    {\"name\": \"carrot\", \"color\": \"orange\", \"age\": 15},\n" +
                "    {\"name\": \"broccoli\", \"color\": \"green\", \"age\": 20},\n" +
                "    {\"name\": \"spinach\", \"color\": \"green\", \"age\": 7},\n" +
                "    {\"name\": \"tomato\", \"color\": \"red\", \"age\": 10}\n" +
                "  ],\n" +
                "  \"animals\": [\n" +
                "    {\"name\": \"dog\", \"color\": \"brown\", \"age\": 3},\n" +
                "    {\"name\": \"cat\", \"color\": \"black\", \"age\": 2},\n" +
                "    {\"name\": \"elephant\", \"color\": \"gray\", \"age\": 25},\n" +
                "    {\"name\": \"lion\", \"color\": \"yellow\", \"age\": 12}\n" +
                "  ],\n" +
                "  \"numbers\": [1, 2, 3, 4, 5],\n" +
                "  \"colors\": [\"red\", \"green\", \"blue\", \"yellow\"]\n" +
                "}\n");

        JsonNode jsonSql = JsonBySQL.jsonBySQL(input, "SELECT * FROM fruits ");
        System.out.println(jsonSql);


    }
}