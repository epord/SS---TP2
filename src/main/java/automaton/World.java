package automaton;

import java.util.ArrayList;

public class World {
    private Cell[][][] cells;
    private Integer width;
    private Integer height;
    private Integer depth;
    private Boolean periodic=false;

    public World(Integer width, Integer height, Integer depth){
        cells = new Cell[depth][height][width];
        this.width = width;
        this.height = height;
        this.depth = depth;
        fillCellsWith(State.DEAD);
    }

    public Cell getCellAt(Integer x, Integer y, Integer z){
        if(x >= width || x < 0 || y >= height || y < 0){
            throw new IllegalStateException("Illegal cell index x:" + x + " y:"+y);
        }
        return cells[z][y][x];
    }

    public void fillCellsWith(State state){

        for (int k = 0; k < getDepth(); k++) {
            for (int j = 0; j < getHeight(); j++) {
                for (int i = 0; i < getWidth(); i++) {
                    cells[k][j][i] = new Cell(i, j, k, state);
                }
            }
        }
    }

    public void setCellState(Integer x, Integer y, Integer z, State state){
        Cell cell = getCellAt(x,y,z);
        cell.setState(state);
    }

    public Double getOccupied(){
        Integer occupied = 0;
        for (int k = 0; k < getDepth(); k++) {
            for (int j = 0; j < getHeight(); j++) {
                for (int i = 0; i < getWidth(); i++) {
                    if(State.ALIVE.equals(getCellAt(i,j,k).getState())){
                        occupied++;
                    }
                }
            }
        }
        return occupied*1.0/(getDepth()*getHeight()*getWidth());
    }

    public Boolean isPeriodic(){
        return periodic;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getDepth() {
        return depth;
    }

    @Override
    public String toString() {
        System.out.println("=========");
        StringBuilder sb = new StringBuilder();
        ArrayList<StringBuilder> builderArrayList = new ArrayList<StringBuilder>();
        for (int i = 0; i < getHeight(); i++) {
            builderArrayList.add(new StringBuilder());
        }
        for (int k = 0; k < getDepth(); k++) {
            for (int j = 0; j < getHeight(); j++) {
                for (int i = 0; i < getWidth(); i++) {
                    builderArrayList.get(j).append(getCellAt(i, j, k).getState().toString(), 0, 1);
                    builderArrayList.get(j).append(" ");
                }
            }
            for (int i = 0; i < builderArrayList.size(); i++) {
                builderArrayList.get(i).append(" | ");
            }
        }

        for (int i = 0; i < builderArrayList.size(); i++) {
            sb.append(builderArrayList.get(i));
            sb.append("\n");
        }
        return sb.toString();
    }


    public Point getCenterOfMass() {
        double x = 0.0, y = 0.0, z = 0.0;
        int count = 0;
        for (int k = 0; k < getDepth(); k++) {
            for (int j = 0; j < getHeight(); j++) {
                for (int i = 0; i < getWidth(); i++) {
                    Cell cell = getCellAt(i, j, k);
                    if (cell.getState() == State.ALIVE) {
                        count++;
                        x += i;
                        y += j;
                        z += k;
                    }
                }
            }
        }
        return new Point(x/count, y/count, z/count);
    }

    public double getRadiusFrom(Point p) {
        double radius = 0.0;
        for (int k = 0; k < getDepth(); k++) {
            for (int j = 0; j < getHeight(); j++) {
                for (int i = 0; i < getWidth(); i++) {
                    Cell cell = getCellAt(i, j, k);
                    if (cell.getState() == State.ALIVE) {
                        double tmp_radius = calculateDistance(new Point(cell.getX(), cell.getY(), cell.getZ()), p);
                        radius = tmp_radius > radius ? tmp_radius : radius;
                    }
                }
            }
        }
        return radius;
    }

    public double calculateDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2)
                        + Math.pow(p1.getY() - p2.getY(),2)
                        + Math.pow(p1.getZ() - p2.getZ(), 2));
    }


}
