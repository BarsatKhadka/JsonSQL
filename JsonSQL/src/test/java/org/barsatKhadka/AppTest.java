package org.barsatKhadka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.barsatKhadka.core.QueryExecutor;


public class AppTest extends TestCase {

    private QueryExecutor queryExecutor;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        queryExecutor = new QueryExecutor();
    }

    /**
     * Test SELECT * FROM simple array of strings
     */
    public void testSelectAllFromStringArray() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode testData = mapper.readTree("{\"fruits\": [\"apple\", \"banana\", \"orange\"]}");
        JsonNode result = queryExecutor.executeQuery("SELECT * FROM fruits", testData);
        assertEquals("[\"apple\",\"banana\",\"orange\"]", result.get("fruits").toString());
    }

    /**
     * Test SELECT * FROM simple array of numbers
     */
    public void testSelectAllFromNumberArray() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode testData = mapper.readTree("{\"numbers\": [1, 2, 3, 4, 5]}");
        JsonNode result = queryExecutor.executeQuery("SELECT * FROM numbers", testData);
        assertEquals("[1,2,3,4,5]", result.get("numbers").toString());
    }

    /**
     * Test SELECT * FROM mixed-type array
     */
    public void testSelectAllFromMixedArray() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode testData = mapper.readTree("{\"mixed\": [1, \"apple\", true, null]}");
        JsonNode result = queryExecutor.executeQuery("SELECT * FROM mixed", testData);
        assertEquals("[1,\"apple\",true,null]", result.get("mixed").toString());
    }

    /**
     * Test SELECT * FROM empty array
     */
    public void testSelectAllFromEmptyArray() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode testData = mapper.readTree("{\"emptyArray\": []}");
        JsonNode result = queryExecutor.executeQuery("SELECT * FROM emptyArray", testData);
        assertEquals("[]", result.get("emptyArray").toString());
    }

    /**
     * Test SELECT * FROM nested object
     */
    public void testSelectAllFromNestedObject() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode testData = mapper.readTree("{\"person\": {\"name\": \"John\", \"age\": 30}}");
        JsonNode result = queryExecutor.executeQuery("SELECT * FROM person", testData);
        assertEquals("{\"name\":\"John\",\"age\":30}", result.get("person").toString());
    }

    /**
     * Test SELECT * FROM deeply nested object
     */
    public void testSelectAllFromDeeplyNestedObject() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode testData = mapper.readTree("{\"company\": {\"name\": \"Tech Corp\", \"employees\": [{\"name\": \"John\", \"age\": 30}]}}");
        JsonNode result = queryExecutor.executeQuery("SELECT * FROM company", testData);
        assertEquals("{\"name\":\"Tech Corp\",\"employees\":[{\"name\":\"John\",\"age\":30}]}", result.get("company").toString());
    }

    /**
     * Test SELECT * FROM mixed JSON
     */
    public void testSelectAllFromMixedJson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode testData = mapper.readTree("{\"mixed\": {\"array\": [1, 2, 3], \"object\": {\"key\": \"value\"}}}");
        JsonNode result = queryExecutor.executeQuery("SELECT * FROM mixed", testData);
        assertEquals("{\"array\":[1,2,3],\"object\":{\"key\":\"value\"}}", result.get("mixed").toString());
    }

    /**
     * Test SELECT * FROM empty object
     */
    public void testSelectAllFromEmptyObject() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode testData = mapper.readTree("{\"emptyObject\": {}}");
        JsonNode result = queryExecutor.executeQuery("SELECT * FROM emptyObject", testData);
        assertEquals("{}", result.get("emptyObject").toString());
    }



    /**
     * Test SELECT invalidField FROM array
     */
    public void testSelectInvalidFieldFromArray() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode testData = mapper.readTree("{\"fruits\": [\"apple\", \"banana\"]}");
        JsonNode result = queryExecutor.executeQuery("SELECT invalidField FROM fruits", testData);
        assertEquals("[]", result.get("fruits").toString());
    }

    /**
     * Test SELECT * FROM JSON with null values
     */
    public void testSelectAllFromJsonWithNullValues() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode testData = mapper.readTree("{\"data\": [null, null, null]}");
        JsonNode result = queryExecutor.executeQuery("SELECT * FROM data", testData);
        assertEquals("[null,null,null]", result.get("data").toString());
    }

    /**
     * Test SELECT * FROM JSON with special characters
     */
    public void testSelectAllFromJsonWithSpecialCharacters() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode testData = mapper.readTree("{\"special\": [\"@#$%\", \"&*()\", \"!@#\"]}");
        JsonNode result = queryExecutor.executeQuery("SELECT * FROM special", testData);
        assertEquals("[\"@#$%\",\"&*()\",\"!@#\"]", result.get("special").toString());
    }

    /**
     * Test SELECT * FROM large array
     */
    public void testSelectAllFromLargeArray() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder largeArray = new StringBuilder("[");
        for (int i = 1; i <= 1000; i++) {
            largeArray.append(i).append(",");
        }
        largeArray.deleteCharAt(largeArray.length() - 1).append("]");
        JsonNode testData = mapper.readTree("{\"largeArray\": " + largeArray + "}");
        JsonNode result = queryExecutor.executeQuery("SELECT * FROM largeArray", testData);
        assertEquals(largeArray.toString(), result.get("largeArray").toString());
    }
}