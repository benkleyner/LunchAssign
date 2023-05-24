import java.util.*;

public class Schedule {
    private TimeSlot[] timeSlots;

    private String[][] pairings;

    public Schedule(TimeSlot[] timeSlots, ArrayList<Faculty> teachers, ArrayList<Location> locations, ArrayList<Tag> tags){
        this.timeSlots = timeSlots;
        this.pairings = createMatrix(teachers, locations, tags);
    }

    private String[][] createMatrix(ArrayList<Faculty> teachers, ArrayList<Location> locations, ArrayList<Tag> tags){
        String[][] matrix = new String[locations.size()][timeSlots.length];
        ArrayList<SchedulableFaculty> schedulableFaculty = createSchedulableFaculty(teachers);

        return matrix;
    }

    private ArrayList<SchedulableFaculty> createSchedulableFaculty(ArrayList<Faculty> teachers){
        ArrayList<SchedulableFaculty> result = new ArrayList<>();
        for(Faculty teacher : teachers){
            result.add(new SchedulableFaculty(teacher));
        }
        return result;
    }


    private int matchScore(Faculty faculty, Location location, ArrayList<Tag> tags) {
        int score = 0;
        for(Tag tag : tags){
            if(Objects.equals(location.getReqs().get(tag.getName()), faculty.getReqs().get(tag.getName()))){
                score += tag.getWeight();
            }
            else{
                score -= tag.getWeight();
            }
        }
        return score;
    }


    public TimeSlot[] getTimeSlots() {
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