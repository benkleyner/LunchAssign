import java.util.ArrayList;
import java.util.HashMap;

public class PLTMeetingSchedule {
    private HashMap<TimeSlot, String> meetingSchedule;

    public PLTMeetingSchedule(ArrayList<Faculty> teachers, ArrayList<TimeSlot> timeSlots){
        meetingSchedule = createMeetingSchedule(teachers, timeSlots);
    }

    private HashMap<TimeSlot, String> createMeetingSchedule(ArrayList<Faculty> teachers, ArrayList<TimeSlot> timeSlots) {
        return null;
    }
}
