package com.example.legalfabbacktest;

import com.google.gson.*;
import java.io.*;
import java.util.*;

public class ReadJson {
    public static String readFile(String name) throws IOException {
        return new Scanner(ReadJson.class.getResourceAsStream(name), "UTF-8")
            .useDelimiter("\\A").next();
    }

    public static ParentJson[] readParent() {
        try {
            String json = ReadJson.readFile("/Parent.json");
            Gson gson = new Gson();
            return gson.fromJson(json, ParentWrapper.class).data;
        } catch (IOException ioException) {
            return new ParentJson[0];
        }
    }

    public static ChildJson[] readChildren() {
        try {
            String json = ReadJson.readFile("/Child.json");
            Gson gson = new Gson();
            return gson.fromJson(json, ChildWrapper.class).data;
        } catch (IOException ioException) {
            return new ChildJson[0];
        }
    }
}
