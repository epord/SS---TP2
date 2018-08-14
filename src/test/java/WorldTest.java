import automaton.Point;
import automaton.State;
import automaton.World;
import org.junit.Test;

public class WorldTest {
    @Test
    public void testRadius() {
        World world = new World(10,10,1);
        world.setCellState(5,1,0, State.ALIVE);
        world.setCellState(5,2,0, State.ALIVE);

        System.out.println(world.getCenterOfMass());
        new Point(5,1.5,0).equals(world.getCenterOfMass());
    }
}
