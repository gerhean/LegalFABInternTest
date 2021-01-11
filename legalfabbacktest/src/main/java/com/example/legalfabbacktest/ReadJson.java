package com.example.legalfabbacktest;

import com.google.gson.*;
import java.io.*;
import java.util.*;

public class ReadJson {
    public static String readFile(String name) throws IOException {
        return new Scanner(WriteJsonExample.class.getResourceAsStream(name), "UTF-8")
            .useDelimiter("\\A").next();
    }

    public static ParentJson[] readParent() {
        try {
            String json = WriteJsonExample.readFile("/Parent.json");
            System.out.println(json);
            Gson gson = new Gson();
            return gson.fromJson(json, ParentWrapper.class).data;
        } catch (IOException ioException) {
            return new ParentJson[0];
        }
        // ParentJson obj = new ParentJson();
        // String json = gson.toJson(obj);
    }

    public static ChildJson[] readChildren() {
        try {
            String json = WriteJsonExample.readFile("/Child.json");
            System.out.println(json);
            Gson gson = new Gson();
            return gson.fromJson(json, ChildWrapper.class).data;
        } catch (IOException ioException) {
            return new ChildJson[0];
        }
        // ParentJson obj = new ParentJson();
        // String json = gson.toJson(obj);
    }
}
