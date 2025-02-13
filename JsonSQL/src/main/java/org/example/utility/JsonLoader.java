package org.example.utility;

import com.fasterxml.jackson.databind.ObjectMapper;

//this is a helper class to load your json data. It identifies which type your data is then parses your json accordingly.
//Types of datasources for json:
//1) Files with .json  2) String  3) Url
public class JsonLoader {
    private static final ObjectMapper mapper = new ObjectMapper();


}
