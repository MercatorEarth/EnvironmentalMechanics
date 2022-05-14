package com.mercator.environmentalmechanics;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.HashMap;
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

    public static void genFloatMapFromConfig(YamlConfiguration config, Map<String, Float> map) {
        for (String key : config.getKeys(false)) {
            map.put(key, (Float) config.get(key));
        }
    }

    public static void genIntMapFromConfig(YamlConfiguration config, Map<String, Integer> map) {
        for (String key : config.getKeys(false)) {
            map.put(key, (Integer) config.get(key));
        }
    }

    public static void genDoubleMapFromConfig(YamlConfiguration config, Map<String, Double> map) {
        for (String key : config.getKeys(false)) {
            map.put(key, (Double) config.get(key));
        }
    }
}
