package org.barsatKhadka.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.*;

public class QueryExecutor {

    public JsonNode executeQuery(String query, JsonNode jsonNode) {
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
                return selectAll(jsonNode, whereNode, fromFields.toString());
            } else {
                return selectAll(fromNode, whereNode, fromFields.toString());
            }
        } else {
            return selectSpecificFields(fromNode, selectFields, whereNode, fromFields.toString());
        }
    }

    private static JsonNode selectAll(JsonNode fromNode, JsonNode whereFields, String fromFieldName) {
        ObjectMapper mapper = new ObjectMapper();
        if (fromNode.isArray()) {
            ArrayNode resultArray = mapper.createArrayNode();
            for (JsonNode element : fromNode) {
                if (whereFields == null || evaluateCondition(element, whereFields)) {
                    resultArray.add(element);
                }
            }
            ObjectNode resultObject = mapper.createObjectNode();

            resultObject.set(fromFieldName, resultArray);
            return resultObject;
        } else if (fromNode.isObject()) {
            ObjectNode resultObject = mapper.createObjectNode();
            resultObject.set(fromFieldName, fromNode);
            return resultObject;
        } else {
            return mapper.createObjectNode().put("error", "Unsupported structure for SELECT *.");
        }
    }

    private static JsonNode selectSpecificFields(JsonNode fromNode, Set<Object> selectFields, JsonNode whereFields, String fromFieldName) {
        ObjectMapper mapper = new ObjectMapper();
        if (fromNode.isArray()) {
            ArrayNode resultArray = mapper.createArrayNode();
            for (JsonNode element : fromNode) {
                if (element.isValueNode()) {
                    if (selectFields.contains(element.asText())) {
                        resultArray.add(element);
                    }
                }
                else if (element.isObject()) {
                    if (whereFields == null || evaluateCondition(element, whereFields)) {
                        ObjectNode filteredObject = mapper.createObjectNode();
                        selectFields.forEach(field -> {
                            if (element.has(field.toString())) {
                                filteredObject.set(field.toString(), element.get(field.toString()));
                            }
                        });
                        resultArray.add(filteredObject);
                    }
                }
            }
            ObjectNode resultObject = mapper.createObjectNode();
            resultObject.set(fromFieldName, resultArray);
            return resultObject;
        } else if (fromNode.isObject()) {
            ObjectNode filteredObject = mapper.createObjectNode();
            selectFields.forEach(field -> {
                if (fromNode.has(field.toString())) {
                    filteredObject.set(field.toString(), fromNode.get(field.toString()));
                }
            });
            ObjectNode resultObject = mapper.createObjectNode();
            resultObject.set(fromFieldName, filteredObject);
            return resultObject;
        } else {
            return mapper.createObjectNode().put("error", "Unsupported structure for SELECT.");
        }
    }

    private static boolean evaluateCondition(JsonNode element, JsonNode whereFields) {
        if (whereFields.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = whereFields.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                if (!element.has(field.getKey()) || !element.get(field.getKey()).equals(field.getValue())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}