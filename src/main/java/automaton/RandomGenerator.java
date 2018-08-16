package automaton;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

public class RandomGenerator {

    public static World getRandomWorld(int width, int height, int depth) {
//        width = 10;
//        height = 10;
//        depth = 1;


        World world = new World(width, height, depth);


        Random r = new Random();
        for (int z = 0; z < depth; z++) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (x < width/4 && y < height/4) {
                        State state = r.nextInt() % 2 == 0 ? State.DEAD : State.ALIVE;
                        world.setCellState(x, y, z, state);
                    } else {
                        world.setCellState(x, y, z, State.DEAD);
                    }
                }
            }
        }

        return world;

    }

}
