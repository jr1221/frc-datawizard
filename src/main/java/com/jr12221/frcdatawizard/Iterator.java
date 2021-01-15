package com.jr12221.frcdatawizard;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jack
 */
public class Iterator {

    ArrayList<String> allKey = new ArrayList<>();
    ArrayList<String> allVal = new ArrayList<>();
    ArrayList<Integer> allInfo = new ArrayList<>();
    int index = 0;

    private JsonFactory factory = new JsonFactory();
    private ObjectMapper mapper = new ObjectMapper(factory);
    private JsonNode rootNode;

    public Iterator(String json) {
        try {
            rootNode = mapper.readTree(json);
        } catch (JsonMappingException j) {
            System.out.println("JSON Mapping Error (Jackson)\n" + j.getMessage());
            return;
        } catch (JsonProcessingException j) {
            System.out.println("JSON Processing Error (Jackson)\n" + j.getMessage());
            return;
        }
        assert rootNode != null;
        java.util.Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
        while (fieldsIterator.hasNext()) {
            Map.Entry<String, JsonNode> field = fieldsIterator.next();
            if (field.getValue().isArray()) {
                allKey.add(" +++" + field.getKey() + ":");
                allVal.add("");
                allInfo.add(1);
                index++;
                JsonArray ad = JsonParser.parseString(json).getAsJsonObject().getAsJsonArray(field.getKey());
                for (JsonElement jsonElement : ad) {
                    JsonObject a = jsonElement.getAsJsonObject();
                    JsonNode rootNode2;
                    try {
                        rootNode2 = mapper.readTree(String.valueOf(a));
                    } catch (JsonMappingException j) {
                        System.out.println("JSON Mapping Error (Jackson)\n" + j.getMessage());
                        return;
                    } catch (JsonProcessingException j) {
                        System.out.println("JSON Processing Error (Jackson)\n" + j.getMessage());
                        return;
                    }
                    java.util.Iterator<Map.Entry<String, JsonNode>> fieldsIterator2 = rootNode2.fields();
                    Map.Entry<String, JsonNode> field2 = fieldsIterator2.next();
                    allKey.add("   ++-" + field2.getKey() + ":");
                    allVal.add(String.valueOf(field2.getValue()));
                    allInfo.add(3);
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
                            }
                            allKey.add("     +-" + field2.getKey());
                            allVal.add(String.valueOf(field2.getValue()));
                            allInfo.add(4);
                            index++;

                        }
                    }
                }
            } else {
                allKey.add("-" + field.getKey());
                allVal.add(String.valueOf(field.getValue()));
                allInfo.add(2);
                index++;
            }
        }
    }

    private void make2(String json, Map.Entry<String, JsonNode> field) {
        JsonArray ad = JsonParser.parseString(json).getAsJsonObject().getAsJsonArray(field.getKey());
        for (JsonElement jsonElement : ad) {
            if (jsonElement.isJsonPrimitive()) {
                allKey.add(field.getKey());
                allVal.add(String.valueOf(field.getValue()));
                allInfo.add(0);
                index++;
                break;
            } else {
                allKey.add("      +++++" + field.getKey() + ":");
                allVal.add("");
                allInfo.add(5);
                index++;
                JsonObject a = jsonElement.getAsJsonObject();
                JsonNode rootNode2 = null;
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
                java.util.Iterator<Map.Entry<String, JsonNode>> fieldsIterator2 = rootNode2.fields();
                while (fieldsIterator2.hasNext()) {
                    Map.Entry<String, JsonNode> field2 = fieldsIterator2.next();
                    allKey.add("           +-" + field2.getKey());
                    allVal.add(String.valueOf(field2.getValue()));
                    allInfo.add(6);
                    index++;
                }
            }
        }
    }
}
