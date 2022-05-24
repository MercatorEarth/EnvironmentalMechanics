package com.mercator.environmentalmechanics.datamanagement;

import java.util.ArrayList;
import java.util.List;

public class LinearEquation {

    public double slope;
    public double yIntercept;
    public double xIntercept;

    public void generate(double x1, double y1, double x2, double y2) {
        slope = ((y2 - y1) / (x2 - x1));

        yIntercept = (y1 - (slope * x1));
        xIntercept = (((slope * x1) - y1) / slope);
    }

    public List<Double> lineIntersect(LinearEquation equation) {
        double x = (equation.yIntercept - yIntercept) / (slope - equation.slope);
        double y = ((((slope * equation.yIntercept) - (slope * yIntercept)) / (slope - equation.slope)) + yIntercept);

        List<Double> coordinates = new ArrayList<>();

        coordinates.add(0, x);
        coordinates.add(1, y);

        return coordinates;
    }
}
