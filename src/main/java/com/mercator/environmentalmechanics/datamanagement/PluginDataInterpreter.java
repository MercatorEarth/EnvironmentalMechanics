package com.mercator.environmentalmechanics.datamanagement;

import com.google.gson.Gson;

import java.io.*;
import java.util.Map;

public class PluginDataInterpreter {

    public static void genDataDir(String module) {
        File mainDataDir = new File("plugins/EnvironmentalMechanics");
        File moduleDataDir = new File("plugins/EnvironmentalMechanics/"+module);

        mainDataDir.mkdir();
        moduleDataDir.mkdir();
    }

    public static void write(File file, Object value, String module) {
        try {
            genDataDir(module);

            FileWriter fw = new FileWriter(file);
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

    public static Map<?, ?> genMapFromJson(String path) {
        Map<?, ?> returnValue = null;

        Gson gson = new Gson();

        try {
            InputStream is = Class.forName("com.mercator.environmentalmechanics.datamanagement.PluginDataInterpreter").getClassLoader().getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            returnValue = gson.fromJson(reader, Map.class);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return returnValue;
    }
}
