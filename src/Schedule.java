import java.util.*;

public class Schedule {

    private String[][] pairings;

    private PLTMeetingSchedule meetingSchedule;

    public Schedule(ArrayList<TimeSlot> timeSlots, ArrayList<Faculty> teachers, ArrayList<Location> locations, ArrayList<Tag> tags){
        this.meetingSchedule = new PLTMeetingSchedule(teachers, timeSlots);
        this.pairings = createMatrix(teachers, locations, tags);
    }

    private String[][] createMatrix(ArrayList<Faculty> teachers, ArrayList<Location> locations, ArrayList<Tag> tags){
        String[][] matrix = new String[locations.size()][meetingSchedule.getMeetingSchedule().size()];
        ArrayList<SchedulableFaculty> schedulableFaculty = createSchedulableFaculty(teachers);
        for(int i = 0; i < meetingSchedule.getMeetingSchedule().size(); i++){
            setEligibility(schedulableFaculty, i);
            for(int j = 0; j < locations.size(); j++){
                int maxMatchScore = 0;
                boolean firstPass = true;
                int selectedIndex = 0;
                String teacherSelectedName = "";
                for(int x = 0; x < schedulableFaculty.size(); x++){
                    if(schedulableFaculty.get(x).isEligible()){
                        if(firstPass){
                            maxMatchScore = matchScore(schedulableFaculty.get(x), locations.get(j), tags);
                            teacherSelectedName = schedulableFaculty.get(x).getName();
                            selectedIndex = x;
                            firstPass = false;
                        }
                        else if (matchScore(schedulableFaculty.get(x), locations.get(j), tags) > maxMatchScore) {
                            maxMatchScore = matchScore(schedulableFaculty.get(x), locations.get(j), tags);
                            teacherSelectedName = schedulableFaculty.get(x).getName();
                            selectedIndex = x;
                        }
                    }
                }
                matrix[j][i] = teacherSelectedName;
                schedulableFaculty.get(selectedIndex).increaseAssignmentCount();
                schedulableFaculty.get(selectedIndex).setEligible(false);
            }
        }
        return matrix;
    }

    private void setEligibility(ArrayList<SchedulableFaculty> schedulableFaculty, int index) {
        ArrayList<TimeSlot> slots = new ArrayList<>(meetingSchedule.getMeetingSchedule().keySet());
        String currSelectedPLT = meetingSchedule.getMeetingSchedule().get(slots.get(index));
        for(SchedulableFaculty s : schedulableFaculty){
            if(s.getPLT().equals(currSelectedPLT) || s.getCountAssignments() >= 2){
                s.setEligible(false);
            }
            else{
                s.setEligible(true);
            }
        }
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



    public String[][] getPairings() {
        return pairings;
    }

    public PLTMeetingSchedule getMeetingSchedule() {
        return meetingSchedule;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "pairings=" + Arrays.toString(pairings) +
                ", meetingSchedule=" + meetingSchedule +
                '}';
    }
}