    package org.example.utility;

    import com.fasterxml.jackson.databind.JsonNode;
    import com.fasterxml.jackson.databind.ObjectMapper;

    import java.io.File;
    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;

    //this is a helper class to load your json data. It identifies which type your data is then parses your json accordingly.
    //Types of datasources for json:
    //1) Files with .json  2) String  3) Url
    public class JsonLoader {
        private static final ObjectMapper mapper = new ObjectMapper();


        public static JsonNode load(String input){
            if(isFilePath(input)){
                return loadJsonFromFile(input);
            }
            else{
                throw new RuntimeException("File not found");
            }
        }


        private static boolean isFilePath(String input){
            try{
                Path filePath = Paths.get(input);
                return Files.exists(filePath);
            }
            catch (Exception e){
                return false;
            }
        }

        private static JsonNode loadJsonFromFile(String filePath) {
            try {
                return mapper.readTree(new File(filePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }



    }
