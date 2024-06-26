package com.mercator.environmentalmechanics.climateengine;

import com.mercator.environmentalmechanics.climateeffects.EffectsEngine;
import com.mercator.environmentalmechanics.datamanagement.BorderIntegration;
import com.mercator.environmentalmechanics.datamanagement.PluginDataInterpreter;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;

import java.io.File;
import java.util.Map;

public class ClimateEngine {

    public BorderIntegration borderIntegration;
    public double variance;
    public double sensitivityFactor;

    private Map<String, Double> biomeTemperatures;

    public ClimateEngine() {
        borderIntegration = new BorderIntegration(19407.0, 14283.0, -1490.0, -976.0);
        biomeTemperatures = (Map<String, Double>) PluginDataInterpreter.genMapFromJson("models/environment_models/biomeTemperatures.json");
        variance = 35.0;
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

        double temperatureValue = 0.0;
        sensitivityFactor = EffectsEngine.getSensitivityFactor();

        if (location.getWorld().getName().equals("world")) {

            double carbonDioxideConcentration = Double.parseDouble(PluginDataInterpreter.read(carbonDioxideValueF));
            double methaneConcentration = Double.parseDouble(PluginDataInterpreter.read(methaneValueF));
            double nitrousOxideConcentration = Double.parseDouble(PluginDataInterpreter.read(nitrousOxideValueF));

            double humidityAdjustFactor = 0.772 - (location.getBlock().getHumidity() * 0.25);
            double latitudeAdjustFactorLinear = ((Math.abs(borderIntegration.getLatitude(location)) - 45.0) / 90.0) * variance;
            double latitudeAdjustFactorQuadratic = (((variance / 4050) * Math.pow(borderIntegration.getLatitude(location), 2.0)) - (variance / 2.0));
            double heightAdjustFactor = Math.abs(location.getBlockY() - location.getWorld().getSeaLevel()) / (1024 / location.getBlockY());

            double baseTemperatureValue = convertRawTemperatureToCelsius(location);
            double latitudeAdjustedTemperature = baseTemperatureValue - (humidityAdjustFactor * latitudeAdjustFactorQuadratic);
            double heightAdjustedTemperature = latitudeAdjustedTemperature - heightAdjustFactor;

            temperatureValue = heightAdjustedTemperature + (((1.0 * carbonDioxideConcentration) + (25.0 * methaneConcentration) + (300.0 * nitrousOxideConcentration)) / sensitivityFactor);
        }

        else {
            temperatureValue = convertRawTemperatureToCelsius(location);
        }

        return temperatureValue;
    }
}
