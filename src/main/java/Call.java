
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import javax.net.ssl.HttpsURLConnection;

public class Call {

    String[] allKey = new String[10000];
    String[] allVal = new String[10000];
    int[] allInfo = new int[10000];
    int index = 0;
    JsonFactory factory = new JsonFactory();
    ObjectMapper mapper = new ObjectMapper(factory);

    int caller(String urlstr, boolean debug, String base2) {
        String json;
        json = null;
        int respCode;
        try {
            URL url = new URL(urlstr);
            HttpsURLConnection urlMaker = (HttpsURLConnection) url.openConnection();
            PreferenceReadWrite apiGET = new PreferenceReadWrite();
            String encodeBytes = apiGET.encodedKey(base2);
            if (encodeBytes==null)
                return -1;
            System.out.println("Texst");
            urlMaker.setRequestProperty("Authorization", " Basic " + encodeBytes);
            urlMaker.setRequestProperty("Accept", " application/json");
            urlMaker.connect();
            respCode = urlMaker.getResponseCode();
            BufferedReader in1;
            if (respCode == 200) {
                in1 = new BufferedReader(new InputStreamReader(urlMaker.getInputStream()));
                json = in1.lines().collect(Collectors.joining());
            } else {
                System.out.println("Error in URL: " + urlstr);
                System.out.println("Response Code: " + respCode);
                InputStream in2 = urlMaker.getErrorStream();
                in1 = new BufferedReader(new InputStreamReader(in2));
                json = in1.lines().collect(Collectors.joining());
                System.out.println(json);
                System.out.println("Exiting...");
                return -1;
            }
            in1.close();
            if (debug) {
                System.out.println("Info regarding the API request: \n " + urlMaker.getHeaderFields() + "\n " + urlMaker.getURL());
                System.out.println("The JSON Response\n" + json);
            }
            urlMaker.disconnect();
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
            return -1;
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
            return -1;
        }
        JsonNode rootNode;
        rootNode = null;
        try {
            rootNode = mapper.readTree(json);
        } catch (JsonMappingException j) {
            System.out.println("JSON Mapping Error (Jackson)\n" + j.getMessage());
            return -1;
        } catch (JsonProcessingException j) {
            System.out.println("JSON Processing Error (Jackson)\n" + j.getMessage());
            return -1;
        }
        assert rootNode != null;
        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
        while (fieldsIterator.hasNext()) {
            Map.Entry<String, JsonNode> field = fieldsIterator.next();
            if (field.getValue().isArray()) {
                allKey[index] = " +++" + field.getKey() + ":";
                allVal[index] = "";
                allInfo[index] = 1;
                index++;
                JsonArray ad = JsonParser.parseString(json).getAsJsonObject().getAsJsonArray(field.getKey());
                for (JsonElement jsonElement : ad) {
                    JsonObject a = jsonElement.getAsJsonObject();
                    JsonNode rootNode2;
                    rootNode2 = null;
                    try {
                        rootNode2 = mapper.readTree(String.valueOf(a));
                    } catch (JsonMappingException j) {
                        System.out.println("JSON Mapping Error (Jackson)\n" + j.getMessage());
                        return -1;
                    } catch (JsonProcessingException j) {
                        System.out.println("JSON Processing Error (Jackson)\n" + j.getMessage());
                        return -1;
                    }
                    Iterator<Map.Entry<String, JsonNode>> fieldsIterator2 = rootNode2.fields();
                    Map.Entry<String, JsonNode> field2 = fieldsIterator2.next();
                    allKey[index] = "   ++-" + field2.getKey() + ":";
                    allVal[index] = String.valueOf(field2.getValue());
                    allInfo[index] = 3;
                    index++;
                    while (fieldsIterator2.hasNext()) {
                        field2 = fieldsIterator2.next();
                        if ((String.valueOf(field2.getValue()).charAt(0) == '[')) {
                            String arrStr = "{\"" + field2.getKey() + "\":" + field2.getValue() + "}";
                            make2(arrStr, field2);
                        } else {
                            if (field2.getKey().contains("encodedAvatar") && !String.valueOf(field2.getValue()).contains("null")) {
                                String imb4Q = String.valueOf(field2.getValue());
                                String baseImg = imb4Q.substring(1, imb4Q.length() - 1);
                                Results.renderImage(baseImg);
                            } else {
                                allKey[index] = "     +-" + field2.getKey();
                                allVal[index] = String.valueOf(field2.getValue());
                                allInfo[index] = 4;
                                index++;
                            }
                        }
                    }
                }
            } else {
                allKey[index] = "-" + field.getKey();
                allVal[index] = String.valueOf(field.getValue());
                allInfo[index] = 2;
                index++;
            }
        }
        return 0;
    }

    void make2(String json, Map.Entry<String, JsonNode> field) {
        JsonArray ad = JsonParser.parseString(json).getAsJsonObject().getAsJsonArray(field.getKey());
        for (JsonElement jsonElement : ad) {
            if (jsonElement.isJsonPrimitive()) {
                allKey[index] = field.getKey();
                allVal[index] = String.valueOf(field.getValue());
                allInfo[index] = 0;
                index++;
                break;
            } else {
                allKey[index] = "      +++++" + field.getKey() + ":";
                allVal[index] = "";
                allInfo[index] = 5;
                index++;
                JsonObject a = jsonElement.getAsJsonObject();
                JsonNode rootNode2;
                rootNode2 = null;
                try {
                    rootNode2 = mapper.readTree(String.valueOf(a));
                } catch (JsonMappingException j) {
                    System.out.println("JSON Mapping Error (Jackson)\n" + j.getMessage());
                    System.out.println("Cannot Recover... Exiting.");
                    System.exit(1);
                } catch (JsonProcessingException j) {
                    System.out.println("JSON Processing Error (Jackson)\n" + j.getMessage());
                    System.out.println("Cannot Recover... Exiting.");
                    System.exit(1);
                }
                Iterator<Map.Entry<String, JsonNode>> fieldsIterator2 = rootNode2.fields();
                while (fieldsIterator2.hasNext()) {
                    Map.Entry<String, JsonNode> field2 = fieldsIterator2.next();
                    allKey[index] = "           +-" + field2.getKey();
                    allVal[index] = String.valueOf(field2.getValue());
                    allInfo[index] = 6;
                    index++;
                }
            }
        }
    }
}