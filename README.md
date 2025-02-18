# JsonSQL 🔍

A lightweight Java library for querying JSON data using SQL-like syntax. Query JSON from files, URLs, or strings using SQL - no database required.

## Features 

- SQL-like queries for JSON data
- Flexible input: Files, URLs, or JSON strings
- Supports SELECT, WHERE, and AS clauses 
- Zero configuration required

## Installation 📦

Add to your Maven project:

```xml
<dependency>
    <groupId>io.github.barsatkhadka</groupId>
    <artifactId>JsonSQL</artifactId>
    <version>1.0.0</version>
</dependency>
```
## Usage 
Basic Usage
```
// Import the library
import org.barsatKhadka.core.JsonBySQL;

// Query from JSON string
String jsonString = "{\"fruits\": [{\"name\": \"apple\"}, {\"name\": \"banana\"}]}";
JsonNode result = JsonBySQL.jsonBySQL(jsonString, "SELECT * FROM fruits");
```
## Input Types

JsonSQL accepts three types of inputs:

1. <b> Json String</b>
```

String jsonString = "{\"data\": [{\"id\": 1}, {\"id\": 2}]}";
JsonBySQL.jsonBySQL(jsonString, "SELECT * FROM data");

```
2. <b> File Path</b>
```

String filePath = "path/to/data.json";
JsonBySQL.jsonBySQL(filePath, "SELECT * FROM data");

```
3. <b> Url (Rest API)</b>
```

String url = "https://api.example.com/data";
JsonBySQL.jsonBySQL(url, "SELECT * FROM data");

```

## Query Types

1. <b> Select All field </b>
```
SELECT * FROM fruits
```

2. <b> Select Specific Fields </b>
```
SELECT name, color FROM fruits
```

3. <b>Select with Where Clause</b>
```
SELECT * FROM fruits WHERE color = red
```

## Example JSON Structure
```
{
  "fruits": [
    {"name": "apple", "color": "red", "age": 10},
    {"name": "banana", "color": "yellow", "age": 5},
    {"name": "orange", "color": "orange", "age": 8}
  ],
  "vegetables": [
    {"name": "carrot", "color": "orange", "age": 15},
    {"name": "broccoli", "color": "green", "age": 20}
  ]
}
```

## Complete Example

```
import org.barsatKhadka.core.JsonBySQL;
import com.fasterxml.jackson.databind.JsonNode;

public class Example {
    public static void main(String[] args) {
        // Your JSON data source (string, file path, or URL)
        String input = "data.json";
        
        // Execute query
        JsonNode result = JsonBySQL.jsonBySQL(input, "SELECT name, color FROM fruits WHERE age > 7");
        
        // Print result
        System.out.println(result.toPrettyString());
    }
}
```

## Limitations

Currently supports:

- `SELECT` operations
- `WHERE` clauses
- Field aliases (`AS`)

### Future versions will include:

- `JOIN` operations
- `GROUP BY` clauses
- Aggregate functions
- `ORDER BY` clauses
- A javascript (npm) version ? 

## Contributing

1. Fork the repository
2. Create your feature branch:
   ```bash
   git checkout -b feature/AmazingFeature
   ```
3. Commit your changes:
   ``bash
   git commit -m 'Add some AmazingFeature'
   ``
4.Push to the branch:
  ``bash
    git push origin feature/AmazingFeature
   ``
5.Open a Pull Request.

## License
This project is licensed under the MIT License - see the LICENSE file for details.

## THANK YOU

