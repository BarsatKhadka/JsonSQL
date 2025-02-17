package org.example.core;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.utility.JsonLoader;

public class JsonBySQL {
    public static JsonNode jsonBySQL(String filePathOrUrlOrJsonString, String sqlCommand) {
        JsonLoader jsonLoader = new JsonLoader();
        JsonNode jsonData = jsonLoader.load(filePathOrUrlOrJsonString);

        if (jsonData == null) {
            throw new IllegalArgumentException("Invalid JSON input.");
        }

        QueryExecutor queryExecutor = new QueryExecutor();
        return queryExecutor.executeQuery(sqlCommand, jsonData);
    }

}
