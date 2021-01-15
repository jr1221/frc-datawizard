package com.jr12221.frcdatawizard;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;
import javax.net.ssl.HttpsURLConnection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jack
 */
public class Extractor {

    Any any;

    public Extractor(String json) {
        any = JsonIterator.deserialize(json);
    }

    public Extractor(boolean get, String url) {
        String json = callUnAuth(url);
        if (json == null) {
            return;
        }
        any = JsonIterator.deserialize(json);
    }

    String getOuter(String getName) {
        return any.get(getName).toString();
    }

    private String callUnAuth(String urlIn) {
        try {
            URL url = new URL(urlIn);
            HttpsURLConnection urlMaker = (HttpsURLConnection) url.openConnection();
            BufferedReader in1 = new BufferedReader(new InputStreamReader(urlMaker.getInputStream()));
            return in1.lines().collect(Collectors.joining());
        } catch (MalformedURLException ex) {
            MessageDialog.main("Error Checking for Updates");
        } catch (IOException ex) {
            MessageDialog.main("Error Checking for Updates");
        }
        return null;
    }
}
