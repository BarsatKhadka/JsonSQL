package org.example;

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
        jsonLoader.load("https://jsonplaceholder.typicode.com/posts/1");

    }
}
