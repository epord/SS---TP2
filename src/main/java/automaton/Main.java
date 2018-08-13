package automaton;

import java.io.*;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) throws IOException {

        if (args.length < 1) {
            System.out.println("Filename required");
            exit(1);
        }
        String filename = args[0];


        if (args.length < 2) {
            System.out.println("Iteration count required");
            exit(1);
        }
        Integer iterations = Integer.parseInt(args[1]);


        Integer rule = 2333;
        if (args.length >= 3) {
            rule = Integer.parseInt(args[1]);
        }

//        World world = new World(4,5, 1);
//        world.setCellState(1,0, 0, State.ALIVE);
//        world.setCellState(2,1,  0,State.ALIVE);
//        world.setCellState(0,2,  0,State.ALIVE);
//        world.setCellState(1,2,  0,State.ALIVE);
//        world.setCellState(2,2,  0,State.ALIVE);

        FileManager fm = new FileManager();

        BufferedReader br = new BufferedReader(new FileReader(filename));
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
