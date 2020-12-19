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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

public class call {
     void caller(String urlstr, boolean debug, boolean gui) throws IOException {
        String json = null;
        int respCode;
        try {
            URL url = new URL(urlstr);
            HttpsURLConnection urlMaker = (HttpsURLConnection) url.openConnection();
            String encodeBytes = null;
                if (apiKEY.Username.contains("REPLACE")) {
                    System.out.println("Add your api key to apiKEY.java");
                    System.exit(2);
                }
                else
                     encodeBytes = Base64.getEncoder().encodeToString((apiKEY.Username + ":" + apiKEY.Authorization_Token).getBytes());
            urlMaker.setRequestProperty("Authorization", " Basic " + encodeBytes);
            urlMaker.setRequestProperty("Accept", " application/json");
            urlMaker.connect();
            respCode = urlMaker.getResponseCode();
            BufferedReader in1 = null;
            if (respCode == 200) {
                in1 = new BufferedReader(new InputStreamReader(urlMaker.getInputStream()));
                json = in1.lines().collect(Collectors.joining());
            } else {
                System.out.println("Error in URL: " + urlstr);
                System.out.println("Response Code: "+respCode);
                try {
                    InputStream in2 = urlMaker.getErrorStream();
                    in1 = new BufferedReader(new InputStreamReader(in2));
                    json = in1.lines().collect(Collectors.joining());
                    System.out.println(json);
                }
                catch (Exception e) {}
                System.exit(1);
            }
            in1.close();
            if (debug) {
                System.out.println("Info regarding the API request: \n " + urlMaker.getHeaderFields() + "\n " + urlMaker.getURL());
                System.out.println("The JSON Response\n"+json);
            }
            urlMaker.disconnect();
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
        make m1 = new make();
        m1.make1(json, debug, gui);
    }

}
class make {
     static String[] allKey = new String[10000];
     static String[] allVal = new String[10000];
     int index = 0;
    JsonFactory factory = new JsonFactory();
    ObjectMapper mapper = new ObjectMapper(factory);
    void make1(String json, boolean debug, boolean gui) throws IOException {
        JsonNode rootNode = mapper.readTree(json);
        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
        while (fieldsIterator.hasNext()) {
            Map.Entry<String, JsonNode> field = fieldsIterator.next();
            if (field.getValue().isArray()) {
                allKey[index] = " +++"+field.getKey()+":";
                allVal[index] = "";
                index++;
                JsonArray ad = JsonParser.parseString(json).getAsJsonObject().getAsJsonArray(field.getKey());
                for (JsonElement jsonElement : ad) {
                    JsonObject a = jsonElement.getAsJsonObject();
                    JsonNode rootNode2 = mapper.readTree(String.valueOf(a));
                    Iterator<Map.Entry<String, JsonNode>> fieldsIterator2 = rootNode2.fields();
                    Map.Entry<String, JsonNode> field2 = fieldsIterator2.next();
                    allKey[index] = "   ++-"+field2.getKey()+":";
                    allVal[index] = String.valueOf(field2.getValue());
                    index++;
                    while (fieldsIterator2.hasNext()) {
                        field2 = fieldsIterator2.next();
                        if ((String.valueOf(field2.getValue()).charAt(0) == '[')) {
                            String arrStr = "{\"" + field2.getKey() + "\":" + field2.getValue() + "}";
                            make2(arrStr, field2);
                        } else {
                            if (field2.getKey().contains("encodedAvatar")&& !String.valueOf(field2.getValue()).contains("null")) {
                                String imb4Q = String.valueOf(field2.getValue());
                                String baseImg = imb4Q.substring(1,imb4Q.length()-1);
                                results.renderImage(baseImg);
                            }
                            else {
                                allKey[index] = "     +-" + field2.getKey();
                                allVal[index] = String.valueOf(field2.getValue());
                                index++;
                            }
                        }
                    }
                }
            }
            else {
                allKey[index] = "-"+field.getKey();
                allVal[index] = String.valueOf(field.getValue());
                index++;
            }
        }
        if (gui)
        results.UI_ReturnData(allKey,allVal,index, debug);
        else
            results.TERM_ReturnData(allKey,allVal,index, debug);
    }
    void make2 (String json, Map.Entry<String,JsonNode> field) throws IOException {
        JsonArray ad = JsonParser.parseString(json).getAsJsonObject().getAsJsonArray(field.getKey());
        for (JsonElement jsonElement : ad) {
            if (jsonElement.isJsonPrimitive()) {
                allKey[index] = field.getKey();
                allVal[index] = String.valueOf(field.getValue());
                index++;
                break;
            }
            else {
                allKey[index] = "      +++++"+field.getKey()+":";
                allVal[index] = "";
                index++;
                JsonObject a = jsonElement.getAsJsonObject();
                JsonNode rootNode2 = mapper.readTree(String.valueOf(a));
                Iterator<Map.Entry<String, JsonNode>> fieldsIterator2 = rootNode2.fields();
                while (fieldsIterator2.hasNext()) {
                    Map.Entry<String, JsonNode> field2 = fieldsIterator2.next();
                    allKey[index] = "           +-"+field2.getKey();
                    allVal[index] = String.valueOf(field2.getValue());
                    index++;
                }
            }
        }
    }
}