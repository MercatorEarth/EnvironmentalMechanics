package com.mercator.environmentalmechanics.climateengine;

import org.bukkit.Location;

public class BorderIntegration {

    public double borderStartX;
    public double borderStartZ;
    public double borderEndX;
    public double borderEndZ;

    public BorderIntegration(double borderStartXT, double borderStartZT, double borderEndXT, double borderEndZT) {
        borderStartX = borderStartXT;
        borderStartZ = borderStartZT;
        borderEndX = borderEndXT;
        borderEndZ = borderEndZT;
    }

    public double getEquator() {
        return (borderEndZ + borderStartZ) / 2.0;
    }

    public double getLatitude(Location location) {
        double axisLength = getEquator() - borderStartZ;
        double rawDistance = getEquator() - location.getBlockZ();
        double distanceOnAxis = rawDistance / axisLength;
        double latitude = distanceOnAxis * 90;

        return latitude;
    }
}
