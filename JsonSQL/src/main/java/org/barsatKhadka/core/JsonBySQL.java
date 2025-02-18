package org.barsatKhadka.core;

import com.fasterxml.jackson.databind.JsonNode;
import org.barsatKhadka.utility.JsonLoader;

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
