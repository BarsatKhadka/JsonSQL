package org.example.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.*;


public class QueryExecutor {

    public JsonNode executeQuery(String query , JsonNode jsonNode) {
        QueryParser queryParser = new QueryParser();
        Map<String, Object> allFields = queryParser.parseQuery(query);
        Set<Object> selectFields = (Set<Object>) (allFields.get("select"));
        Object fromFields = (allFields.get("from"));
        Object whereFields = (allFields.get("where"));
        Object aliasFields = (allFields.get("alias"));

        JsonNode fromNode = jsonNode.get(fromFields.toString());
        JsonNode whereNode = (whereFields != null) ? jsonNode.get(whereFields.toString()) : null;

        if (selectFields.contains("*")) {
            if ("*".equals(fromFields)) {
                return selectAll(jsonNode, whereNode);
            } else {
                return selectAll(fromNode, whereNode);
            }
//        } else if ("*".equals(fromFields)) {
//            selectFromAll(jsonNode, selectFields, whereFields);
//        } else if (fromNode.isArray()) {
//            selectFromArray(fromNode, selectFields, whereFields);
//        } else if (fromNode.isObject()) {
//            selectFromObject(fromNode, selectFields, whereFields);
        } else {

            throw new RuntimeException("Unsupported structure in FROM.");
        }


    }

    private static JsonNode selectAll(JsonNode fromNode, JsonNode whereFields) {
        ObjectMapper mapper = new ObjectMapper();
        if (fromNode.isArray()) {
            ArrayNode resultArray = mapper.createArrayNode();
            for (JsonNode element : fromNode) {
                if (whereFields != null) {
                    if (element.get(whereFields.toString()) != null) {
                        resultArray.add(element);
                    }
                } else {
                    resultArray.add(element);
                }
            }
            return resultArray;
        } else if (fromNode.isObject()) {
            ObjectNode resultObject = mapper.createObjectNode();
            fromNode.fieldNames().forEachRemaining(field -> {
                resultObject.set(field, fromNode.get(field));
            });
            return resultObject;
        } else {
            return mapper.createObjectNode().put("error", "Unsupported structure for SELECT *.");
        }
    }



}

