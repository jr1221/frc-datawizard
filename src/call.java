import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

public class call {
    static String[] allKey = new String[10000];
    static String[] allVal = new String[10000];
    static int index = 0;
    static final String base = "https://frc-api.firstinspires.org/v2.0/";
    public void caller(String urlstr) throws IOException {
        String json = null;
        int respCode;
        try {
            URL url = new URL(urlstr);
            HttpsURLConnection urlMaker = (HttpsURLConnection) url.openConnection();
            apiKEY apiget = new apiKEY();
            String encodeBytes = apiget.encodeBytes();
            urlMaker.setRequestProperty("Authorization", " Basic " + encodeBytes);
            urlMaker.setRequestProperty("Accept", " application/json");
            urlMaker.connect();
            respCode = urlMaker.getResponseCode();
            BufferedReader in1;
            if (respCode == 200) {
                in1 = new BufferedReader(new InputStreamReader(urlMaker.getInputStream()));
                json = in1.lines().collect(Collectors.joining());
            } else {
                System.out.println("Error in URL: "+urlstr);
                in1 = new BufferedReader(new InputStreamReader(urlMaker.getErrorStream()));
                json = in1.lines().collect(Collectors.joining());
                System.out.println(json);
                System.exit(1);
            }
            in1.close();
            if (urlstr.equals(base))
                System.out.println("Info regarding the API request: \n " + urlMaker.getHeaderFields() + "\n " + urlMaker.getURL());
            urlMaker.disconnect();
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
        call arrDis11 = new call();
        boolean indexStart = true;
        boolean first = true;
        String arrStr;
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        JsonNode rootNode = mapper.readTree(json);
        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
        while (fieldsIterator.hasNext()) {
            Map.Entry<String, JsonNode> field = fieldsIterator.next();
            if (field.getValue().isArray()) {
                if (indexStart) {
                    allKey[index] = "++ " + field.getKey();
                    allVal[index] = "";
                    index++;
                }
                assert json != null;
                first=true;
                JsonObject ac = JsonParser.parseString(json).getAsJsonObject();
                JsonArray ad = ac.getAsJsonArray(field.getKey());
                for (JsonElement jsonElement : ad) {
                    JsonObject a = jsonElement.getAsJsonObject();
                    JsonFactory factory2 = new JsonFactory();
                    ObjectMapper mapper2 = new ObjectMapper(factory2);
                    JsonNode rootNode2 = mapper2.readTree(String.valueOf(a));
                    Iterator<Map.Entry<String, JsonNode>> fieldsIterator2 = rootNode2.fields();
                    while (fieldsIterator2.hasNext()) {
                        Map.Entry<String, JsonNode> field2 = fieldsIterator2.next();
                        if (field2.getValue().isArray()) {
                            arrStr = "{\"" + field2.getKey() + "\":" + field2.getValue() + "}";
                            allKey[index] = "--- " + field2.getKey() + ":";
                            if (String.valueOf(field2.getValue()).equals("[]"))
                                allVal[index] = "None";
                            else
                                allVal[index] = "";
                            index++;
                            arrDis11.arrDis1(arrStr);
                        } else {
                            allKey[index] = " " + field2.getKey();
                            allVal[index] = field2.getValue().asText();
                            index++;
                        }
                    }
                }
            } else {
                allKey[index] = " " + field.getKey() + ":";
                allVal[index] = " " + field.getValue().asText();
                index++;
            }
        }
        mainStarter.ReturnData(allKey, allVal, index);
    }
    void arrDis1 (String arrStr) throws IOException {
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        JsonNode rootNode = mapper.readTree(arrStr);
        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
        while (fieldsIterator.hasNext()) {
            Map.Entry<String, JsonNode> field = fieldsIterator.next();
            if (field.getValue().isArray()) {
                JsonObject ac = JsonParser.parseString(arrStr).getAsJsonObject();
                JsonArray ad = ac.getAsJsonArray(field.getKey());
                for (JsonElement jsonElement : ad) {
                    if (!(jsonElement.isJsonObject())) {
                        allKey[index] = " ";
                        allVal[index] = jsonElement.getAsString();
                        index++;
                        break;
                    }
                    JsonObject a = jsonElement.getAsJsonObject();
                    JsonFactory factory2 = new JsonFactory();
                    ObjectMapper mapper2 = new ObjectMapper(factory2);
                    JsonNode rootNode2 = mapper2.readTree(String.valueOf(a));
                    Iterator<Map.Entry<String, JsonNode>> fieldsIterator2 = rootNode2.fields();
                    while (fieldsIterator2.hasNext()) {
                        Map.Entry<String, JsonNode> field2 = fieldsIterator2.next();
                        if (field2.getValue().isArray()) {
                            arrStr = "{\"" + field2.getKey() + "\":" + field2.getValue() + "}";
                            System.out.println("Note this string error:   " + arrStr);
                        } else {
                            allKey[index] = "------ "+field2.getKey();
                            allVal[index] = field2.getValue().asText();
                            index++;
                        }
                    }
                }
            }
        }
    }
}