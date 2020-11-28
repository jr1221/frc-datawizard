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
    public static String[] allKey = new String[100000];
    public static String[] allVal = new String[100000];
    public static int index = 0;
    public static int index3 = 0;
    public static final String base = "https://frc-api.firstinspires.org/v2.0/";
    public void caller(String urlstr) throws IOException {
        String json = null;
        int respCode = 199;
        try {
            URL url = new URL(urlstr);
            HttpsURLConnection urlMaker = (HttpsURLConnection)url.openConnection();
            apiKEY apiget = new apiKEY();
            String encodeBytes = apiget.encodeBytes();
            urlMaker.setRequestProperty("Authorization", " Basic " + encodeBytes);
            urlMaker.setRequestProperty("Accept", " application/json");
            urlMaker.connect();
            respCode = urlMaker.getResponseCode();
            BufferedReader in1 = new BufferedReader(new InputStreamReader(urlMaker.getInputStream()));
            if (urlstr.equals(base))
                System.out.println("Info regarding the API request: \n " + urlMaker.getHeaderFields() +"\n "+urlMaker.getURL());
            json = in1.lines().collect(Collectors.joining());
            in1.close();
            urlMaker.disconnect();
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
            System.out.println("Make sure you entered the year, it is required!");
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
            System.out.println("Make sure the data you entered exists in the correct combinations.");
        }
        if (respCode!=200 || json==null) {
            if (respCode == 400)
                System.out.println("400 Response. \n ");
            else if (respCode== 401)
                System.out.println("401 Response. \n ");
            else if (respCode == 404)
                System.out.println("404 Response. \n ");
            else if (respCode == 500)
                System.out.println("500 Response. \n ");
            else if (respCode == 501)
                System.out.println("501 Response. \n ");
            else if (respCode == 503)
                System.out.println("503 Response. \n ");
            else
                System.out.println("Unknown Response??\n URL:"+urlstr);
            System.exit(1);
        }
        call arrDis11 = new call();
        String arrStr;
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        JsonNode rootNode = mapper.readTree(json);
        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
        while (fieldsIterator.hasNext()) {
            Map.Entry<String, JsonNode> field = fieldsIterator.next();
            if (field.getValue().isArray()) {
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
                            allKey[index] = "--- "+field2.getKey()+":";
                            allVal[index] = "";
                            index++;
                            arrDis11.arrDis1(arrStr);
                        } else {
                            allKey[index] = " "+field2.getKey();
                            allVal[index] = String.valueOf(field2.getValue());
                            index++;
                        }
                    }
                }
            }
        }
        System.out.println("And here is your data: \n");
        int index2=0;
        while (index2<index) {
            System.out.printf("   %-35s %35s %n", allKey[index2], allVal[index2]);
            index2++;
        }
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
                            allVal[index] = String.valueOf(field2.getValue());
                            index++;
                        }
                    }
                }
            }
        }
    }
}
