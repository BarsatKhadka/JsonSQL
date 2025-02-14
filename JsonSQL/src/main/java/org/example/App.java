package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.utility.JsonLoader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        JsonLoader jsonLoader = new JsonLoader();
        JsonNode output = jsonLoader.load("https://jsonplaceholder.typicode.com/posts/1");
        System.out.println(output);



    }
}
