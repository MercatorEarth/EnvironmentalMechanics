package com.mercator.environmentalmechanics.climate;

import com.mercator.environmentalmechanics.PluginDataInterpreter;

import java.io.File;

public class WarmingEffects {

    public static double getTemperature() {
        File carbonDioxideValueF = new File("plugins/EnvironmentalMechanics/globalwarming/carbondioxide.txt");
        File methaneValueF = new File("plugins/EnvironmentalMechanics/globalwarming/methane.txt");
        File nitrousOxideValueF = new File("plugins/EnvironmentalMechanics/globalwarming/nitrousoxide.txt");

        double carbonDioxideConcentration = Double.parseDouble(PluginDataInterpreter.read(carbonDioxideValueF));
        double methaneConcentration = Double.parseDouble(PluginDataInterpreter.read(methaneValueF));
        double nitrousOxideConcentration = Double.parseDouble(PluginDataInterpreter.read(nitrousOxideValueF));

        double temperatureValue = 13 + (((1.0 * carbonDioxideConcentration) + (25.0 * methaneConcentration) + (300 * nitrousOxideConcentration)) / 10000.0f);

        return temperatureValue;
    }
}
