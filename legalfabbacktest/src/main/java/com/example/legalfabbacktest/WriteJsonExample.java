package com.example.legalfabbacktest;

import com.google.gson.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class WriteJsonExample
{
    public static String readFile(String name) throws IOException {
        return new Scanner(WriteJsonExample.class.getResourceAsStream(name), "UTF-8")
            .useDelimiter("\\A").next();
    }

    public static void main(String[] args) throws IOException {
        String json = WriteJsonExample.readFile("/Parent.json");
        System.out.println(json);
        Gson gson = new Gson();
        ParentJson[] parents = gson.fromJson(json, ParentWrapper.class).data;
        System.out.println(parents[1].sender);
        // ParentJson obj = new ParentJson();
        // String json = gson.toJson(obj);
    }
}
