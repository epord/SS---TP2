package automaton;

import java.util.ArrayList;

public class World {
    private Cell[][][] cells;
    private Integer width;
    private Integer height;
    private Integer depth;
    private Boolean periodic=true;

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
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                for (int k = 0; k < cells[i][j].length; k++) {
                    cells[i][j][k] = new Cell(state);
                }
            }
        }
    }

    public void setCellState(Integer x, Integer y, Integer z, State state){
        Cell cell = getCellAt(x,y,z);
        cell.setState(state);
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
}
