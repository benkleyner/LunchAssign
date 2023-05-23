import javax.sound.sampled.FloatControl;
import java.util.*;

public class Schedule {
    private TimeSlot[] timeSlots;

    private String[][] pairings;

    public Schedule(TimeSlot[] timeSlots, ArrayList<Faculty> teachers, ArrayList<Location> locations){
        this.timeSlots = timeSlots;
        this.pairings = createMatrix(teachers, locations);
    }

    private String[][] createMatrix(ArrayList<Faculty> teachers, ArrayList<Location> locations){
        String[][] matrix = new String[locations.size()][timeSlots.length];
        HashMap<String, Integer> slotAssignments = getPLTAssignment(teachers);
        for(int i = 0; i < timeSlots.length; i++){
            ArrayList<Faculty> includedTeachers = removeExcluded(teachers, slotAssignments);
            for(int j = 0; j < locations.size(); j++){
                String teacherSelectedName = includedTeachers.get(0).getName();
                int teacherSelectedScore = matchScore(includedTeachers.get(0), locations.get(j));
            }
        }
        return matrix;
    }
    /*implement next*/
    private int matchScore(Faculty faculty, Location location) {
        return 0;
    }

    /* implement next */
    private ArrayList<Faculty> removeExcluded(ArrayList<Faculty> teachers, HashMap<String, Integer> slotAssignments) {
        return null;
    }

    public TimeSlot[] getTimeSlots() {
        return timeSlots;
    }

    public String[][] getPairings() {
        return pairings;
    }

    private HashMap<String, Integer> getPLTAssignment(ArrayList<Faculty> teachers){
        HashMap<String, Integer> PLTScores = new HashMap<>();

        for(Faculty teacher : teachers){
            if(!PLTScores.containsKey(teacher.getPLT())){
                PLTScores.put(teacher.getPLT(), 1);
            }
            else{
                PLTScores.replace(teacher.getPLT(), PLTScores.get(teacher.getPLT()) + getScore(teacher));
            }
        }

        return normalizeByMedian(PLTScores);


    }

    private HashMap<String, Integer> normalizeByMedian(HashMap<String, Integer> pltScores) {
        ArrayList<Integer> vals = new ArrayList<Integer>(pltScores.values());
        LinkedHashMap<String, Integer> sortedScores = new LinkedHashMap<String, Integer>();
        Collections.sort(vals);
        int middleIndex = vals.size() / 2;
        for(int num : vals){
            for(Map.Entry<String, Integer> entry : pltScores.entrySet()){
                if(entry.getValue().equals(num)){
                    sortedScores.put(entry.getKey(), num);
                }
            }
        }
        ArrayList<String> keys = new ArrayList<>(sortedScores.keySet());
        for(int i = 0; i < vals.size(); i++){
            sortedScores.replace(keys.get(i), (i < middleIndex ? 1 : 2));
        }

        return sortedScores;
    }

    private int getScore(Faculty teacher) {
        int result = 0;
        switch (teacher.getPlanningPeriod()){
            case 0:
                result = 0;
            case 1:
                result = 0;
            case 2:
                result = 1;
            case 3:
                result = 5;
            case 4:
                result = 0;
        }
        return result;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "timeSlots=" + Arrays.toString(timeSlots) +
                ", pairings=" + Arrays.toString(pairings) +
                '}';
    }

}