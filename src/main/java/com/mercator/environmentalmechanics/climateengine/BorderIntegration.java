package com.mercator.environmentalmechanics.climateengine;

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

    public double getStartX() {
        return borderStartX;
    }

    public double getStartZ() {
        return borderStartZ;
    }

    public double getEndX() {
        return borderEndX;
    }

    public double getEndZ() {
        return borderEndZ;
    }
}
