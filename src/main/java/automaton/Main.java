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
            rule = Integer.parseInt(args[2]);
        }

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
        Statistics statistics = new Statistics();

        Point initialCenterOfMass = world.getCenterOfMass();
        for (int i = 0; i < iterations; i++) {
            System.out.println(world);
            Point centerOfMass = world.getCenterOfMass();
            statistics.addCenterOfMassDataPoint(centerOfMass);
            statistics.addMovementSeriesDataPoint(world.calculateDistance(initialCenterOfMass,centerOfMass));
            statistics.addOcupationSeriesDataPoint(world.getOccupied());
            statistics.addReachSeriesDataPoint(world.getRadiusFrom(centerOfMass));

            System.out.println("Center of mass: " + statistics.getCenterOfMassSeries().get(i));
            System.out.println("Movement: " + statistics.getMovementSeries().get(i));
            System.out.println("Ocupation: " + statistics.getOccupationSeries().get(i));
            System.out.println("Reach: " + statistics.getReachSeries().get(i));
            fm.writeWorldStatus(bw, world);
            world = transformation.transform(world);
        }



        bw.close();
    }
}
