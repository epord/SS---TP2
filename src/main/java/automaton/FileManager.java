package automaton;

import java.io.*;
import java.util.Collection;

public class FileManager {

    public World readWorld(BufferedReader br) throws IOException {
        World world = null;
        try {
            String[] first_line = br.readLine().split(" ");
            int width = Integer.parseInt(first_line[0]);
            int height = Integer.parseInt(first_line[1]);
            int depth = Integer.parseInt(first_line[2]);
            world = new World(width, height, depth);

            for (int z = 0; z < depth; z++) {
                for (int y = 0; y < height; y++) {
                    char[] line  = br.readLine().toCharArray();
                    for (int i = 0; i < line.length; i++) {
                        if (line[i] == 'O') {
                            world.setCellState(i,y, z, State.ALIVE);
                        }
                    }
                }
                br.readLine();
            }

        } finally {
            return world;
        }
    }

    public void writeWorldInformation(BufferedWriter bw, World w) throws IOException {
        try {
            bw.write(w.getWidth() + " " + w.getHeight() + " " + w.getDepth() + "\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeWorldStatus(BufferedWriter bw, World w) throws IOException {
        try {

            for (int z = 0; z < w.getDepth(); z++) {
                for (int y = 0; y < w.getHeight(); y++) {
                    for (int x = 0; x < w.getWidth(); x++) {
                        bw.write(w.getCellAt(x, y, z).getState() == State.ALIVE ? "O" : "X");
                    }
                    bw.write("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
