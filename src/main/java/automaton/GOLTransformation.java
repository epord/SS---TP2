package automaton;

public class GOLTransformation implements Transformation{

    private int rule;

    public GOLTransformation(int rule) {
        this.rule = rule;
    }

    private int getMinToSurvive() {
        return rule/1000;
    }

    private int getMaxToSurvive() {
        return (rule%1000)/100;
    }

    private int getMinToRevive() {
        return (rule%100)/10;
    }

    private int getMaxToRevive() {
        return rule%10;
    }

    public World transform(World world) {
        World constructed = new World(world.getWidth(),world.getHeight(), world.getDepth());
        for (int k = 0; k < world.getDepth(); k++) {
            for (int j = 0; j < world.getHeight(); j++) {
                for (int i = 0; i < world.getWidth(); i++) {
                    Integer alive = countNeighboursInState(world, i, j, k, State.ALIVE);

                    if (State.ALIVE.equals(world.getCellAt(i, j, k).getState())) {
                        if (alive < getMinToSurvive()) {
                            constructed.setCellState(i, j, k, State.DEAD);
                        } else if (alive >= getMinToSurvive() && alive <= getMaxToSurvive()) {
                            constructed.setCellState(i, j, k, State.ALIVE);
                        } else if (alive > getMaxToSurvive()) {
                            constructed.setCellState(i, j, k, State.DEAD);
                        }
                    } else {
                        if (alive >= getMinToRevive() && alive <= getMaxToRevive()) {
                            constructed.setCellState(i, j, k, State.ALIVE);
                        }
                    }

                }
            }
        }

        return constructed;
    }

    private Integer countNeighboursInState(World world, Integer x, Integer y, Integer z, State state){
        Integer count= 0;
        if(world.isPeriodic()){
            for (int i = (x-1); i < (x+1); i++) {
                for (int j = (y-1); j < (y+1); j++) {
                    for (int k = (z - 1); k < (z + 1); k++) {
                        if (x != i || y != j || z != k) {
                            Cell cell = world.getCellAt(i % world.getWidth(), j % world.getHeight(), k % world.getDepth());
                            if (state.equals(cell.state)) {
                                count++;
                            }
                        }
                    }
                }
            }
        } else {
            for (int i = x - 1 < 0 ? 0 : x - 1; i <= (x+1 >= world.getWidth() ? world.getWidth()-1 : x+1); i++) {
                for (int j = y - 1 < 0 ? 0 : y - 1; j <= (y + 1 >= world.getHeight() ? world.getHeight() - 1 : y + 1); j++) {
                    for (int k = z - 1 < 0 ? 0 : z - 1; k <= (z + 1 >= world.getDepth() ? world.getDepth() - 1 : z + 1); k++) {
                        if (x != i || y != j || z != k) {
                            Cell cell = world.getCellAt(i, j, k);
                            if (state.equals(cell.state)) {
                                count++;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
}
