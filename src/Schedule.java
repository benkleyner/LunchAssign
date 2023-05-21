import javax.sound.sampled.FloatControl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.SortedMap;

public class Schedule {
    private String[] timeSlots;

    private String[][] pairings;

    public Schedule(String[] timeSlots, ArrayList<Faculty> teachers, ArrayList<Location> locations){
        this.timeSlots = timeSlots;
        this.pairings = createMatrix(teachers, locations);
    }

    private String[][] createMatrix(ArrayList<Faculty> teachers, ArrayList<Location> locations){
        String[][] matrix = new String[locations.size()][timeSlots.length];
        for(int i = 0; i < timeSlots.length; i++){

        }
        return matrix;
    }

    public String[] getTimeSlots() {
        return timeSlots;
    }

    public String[][] getPairings() {
        return pairings;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "timeSlots=" + Arrays.toString(timeSlots) +
                ", pairings=" + Arrays.toString(pairings) +
                '}';
    }
}