package automaton;

import java.util.List;

public class Statistics {
    //The position of the center of mass depending of the generation
    private List<Point> centerOfMassSeries;

    //What percentage of the world is occupied by the pattern
    private List<Double> occupationSeries;

    //What is the distance between the center of mass of the gen 0 and the current gen
    private List<Double> movementSeries;

    //The distance between the center of mass at gen 0 and the furthest cell alive al gen t
    private List<Double> reachSeries;


}
