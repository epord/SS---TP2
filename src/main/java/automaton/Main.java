package automaton;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Integer iterations = 50;
        Integer rule = 5766;

//        World world = new World(4,5, 1);
//        world.setCellState(1,0, 0, State.ALIVE);
//        world.setCellState(2,1,  0,State.ALIVE);
//        world.setCellState(0,2,  0,State.ALIVE);
//        world.setCellState(1,2,  0,State.ALIVE);
//        world.setCellState(2,2,  0,State.ALIVE);

        FileManager fm = new FileManager();

        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        World world = fm.readWorld(br);

        File fout = new File("p5/empty-example/output.txt");
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        bw.write(iterations + "\n");
        fm.writeWorldInformation(bw, world);
        fm.writeWorldStatus(bw, world);

        System.out.println(world);
        GOLTransformation transformation = new GOLTransformation(rule);
        for (int i = 0; i < iterations; i++) {
            world = transformation.transform(world);
            System.out.println(world);
            fm.writeWorldStatus(bw, world);
        }



        bw.close();
    }
}
