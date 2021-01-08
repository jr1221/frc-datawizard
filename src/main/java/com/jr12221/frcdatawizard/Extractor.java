package com.jr12221.frcdatawizard;


import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;

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
    String getOuter(String getName) {
        return any.get(getName).toString();
    }
}

