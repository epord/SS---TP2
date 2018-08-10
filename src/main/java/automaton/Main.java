package automaton;

public class Main {
    public static void main(String[] args) {
        World world = new World(4,5, 80);
//        world.setCellState(0,0, State.ALIVE);
        world.setCellState(0,1, 0, State.ALIVE);
        world.setCellState(1,0,  0,State.ALIVE);

        System.out.println(world);
        GOLTransformation transformation = new GOLTransformation();
        for (int i = 0; i < 1000; i++) {
            world = transformation.transform(world);
            System.out.println(world);
        }
    }
}
