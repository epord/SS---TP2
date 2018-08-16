package automaton;

import java.util.ArrayList;
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

    public Statistics(){
        centerOfMassSeries = new ArrayList<>();
        occupationSeries = new ArrayList<>();
        movementSeries = new ArrayList<>();
        reachSeries = new ArrayList<>();
    }

    public void addCenterOfMassDataPoint(Point centerOfMassPosition){
        centerOfMassSeries.add(centerOfMassPosition);
    }

    public void addOcupationSeriesDataPoint(Double occupationPercentage){
        occupationSeries.add(occupationPercentage);
    }

    public void addMovementSeriesDataPoint(Double distance){
        movementSeries.add(distance);
    }

    public void addReachSeriesDataPoint(Double reach){
        reachSeries.add(reach);
    }

    public List<Point> getCenterOfMassSeries() {
        return centerOfMassSeries;
    }

    public List<Double> getOccupationSeries() {
        return occupationSeries;
    }

    public List<Double> getMovementSeries() {
        return movementSeries;
    }

    public List<Double> getReachSeries() {
        return reachSeries;
    }
}
