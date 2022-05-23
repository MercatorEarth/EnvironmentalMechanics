package com.mercator.environmentalmechanics.climateengine;

import com.mercator.environmentalmechanics.PluginDataInterpreter;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;

import java.io.File;
import java.util.Map;

public class ClimateEngine implements Listener {

    public BorderIntegration borderIntegration;

    private Map<String, Double> biomeTemperatures;

    public ClimateEngine() {
        borderIntegration = new BorderIntegration(19407.0, 14283.0, -1490.0, -976.0);
        biomeTemperatures = (Map<String, Double>) PluginDataInterpreter.genMapFromJson("models/biomeTemperatures.json");
    }

    public double rawTemperature(Location location) {
        Block reference = location.getBlock();
        Biome biome = reference.getBiome();

        double rawTemperature = biomeTemperatures.get(biome.name());

        return rawTemperature;
    }

    public double convertRawTemperatureToCelsius(Location location) {
        double rawTemperature = rawTemperature(location);
        double convertedTemperature = (21.2992 * rawTemperature) + 0.55273;

        return convertedTemperature;
    }

    public double getTemperatureAt(Location location) {
        File carbonDioxideValueF = new File("plugins/EnvironmentalMechanics/globalwarming/carbondioxide.txt");
        File methaneValueF = new File("plugins/EnvironmentalMechanics/globalwarming/methane.txt");
        File nitrousOxideValueF = new File("plugins/EnvironmentalMechanics/globalwarming/nitrousoxide.txt");

        double carbonDioxideConcentration = Double.parseDouble(PluginDataInterpreter.read(carbonDioxideValueF));
        double methaneConcentration = Double.parseDouble(PluginDataInterpreter.read(methaneValueF));
        double nitrousOxideConcentration = Double.parseDouble(PluginDataInterpreter.read(nitrousOxideValueF));

        double temperatureValue = convertRawTemperatureToCelsius(location) + (((1.0 * carbonDioxideConcentration) + (25.0 * methaneConcentration) + (300 * nitrousOxideConcentration)) / 10000.0f);

        return temperatureValue;
    }
}
