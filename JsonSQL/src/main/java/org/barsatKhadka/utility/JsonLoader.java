    package org.barsatKhadka.utility;

    import com.fasterxml.jackson.databind.JsonNode;
    import com.fasterxml.jackson.databind.ObjectMapper;

    import java.io.File;
    import java.io.IOException;
    import java.net.URI;
    import java.net.URISyntaxException;
    import java.net.URL;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;

    //this is a helper class to load your json data. It identifies which type your data is then parses your json accordingly.
    //Types of datasources for json:
    //1) Files with .json  2) String  3) Url
    public class JsonLoader {
        public static final ObjectMapper mapper = new ObjectMapper();


        public static JsonNode load(String input)  {
            if(isFilePath(input)){
                return loadJsonFromFile(input);
            }
            else if(isUrlPath(input)){
                try {
                    return loadJsonFromUrl(input);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else if(isStringPath(input)){
                return loadJsonFromString(input);
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

        private static boolean isStringPath(String input){
            if(input == null || input.trim().isEmpty()){
                return false;
            }
            String trimmedInput = input.trim();
            if (!trimmedInput.startsWith("{") && !trimmedInput.startsWith("[")) {
                return false;
            }
            try {
                mapper.readTree(trimmedInput);
                return true;
            } catch (Exception e) {
                return false;
            }

        }

        private static JsonNode loadJsonFromString(String filePath) {
            try {
                return mapper.readTree(filePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        private static boolean isUrlPath(String input){
            if (input == null || input.trim().isEmpty()) {
                return false;
            }

            try {
                URI uri = new URI(input);
                String scheme = uri.getScheme();
                return scheme != null &&
                        (scheme.equalsIgnoreCase("http") ||
                                scheme.equalsIgnoreCase("https") ||
                                scheme.equalsIgnoreCase("ftp") ||
                                scheme.equalsIgnoreCase("file"));
            } catch (URISyntaxException e) {
                return false;
            }

        }

        private static JsonNode loadJsonFromUrl(String url) throws IOException {
            return mapper.readTree(new URL(url));

        }




    }
