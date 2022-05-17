package com.mercator.environmentalmechanics;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class PluginDataInterpreter {

    public static void write(File file, Object value) {
        try {
            FileWriter fw = new java.io.FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);

            pw.println(value);
            pw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String read(File file) {
        String value = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            value = br.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }

    public static Map<?, ?> genMapFromJson(Path path) {
        Map<?, ?> returnValue = null;

        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(path);
            returnValue = gson.fromJson(reader, Map.class);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return returnValue;
    }
}
