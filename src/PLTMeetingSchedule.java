import java.util.*;

public class PLTMeetingSchedule {
    private HashMap<TimeSlot, String> meetingSchedule;

    public PLTMeetingSchedule(ArrayList<Faculty> teachers, ArrayList<TimeSlot> timeSlots){
        meetingSchedule = createMeetingSchedule(teachers, timeSlots);
    }

    private HashMap<TimeSlot, String> createMeetingSchedule(ArrayList<Faculty> teachers, ArrayList<TimeSlot> timeSlots) {
        HashMap<TimeSlot, String> meetingSchedule = new HashMap<>();
        setNumOnDay(timeSlots);
        LinkedHashMap<String, Integer> scoredSlots = computeSlotNoByPlanningPeriod(teachers);
        ArrayList<String> PLTs = new ArrayList<>(scoredSlots.keySet());
        for(TimeSlot t : timeSlots){
            for(int i = PLTs.size() - 1; i >= 0; i--){
                if(t.getNumOnDay() == scoredSlots.get(PLTs.get(i))){
                    meetingSchedule.put(t, PLTs.get(i));
                    PLTs.remove(i);
                    break;
                }
            }
        }
        return meetingSchedule;
    }

    private LinkedHashMap<String, Integer> computeSlotNoByPlanningPeriod(ArrayList<Faculty> teachers) {
        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        for(Faculty f : teachers){
            if(!result.containsKey(f.getPLT())){
                result.put(f.getPLT(), pltScore(f));
            }
            else{
                result.put(f.getPLT(), result.get(f.getPLT()) + pltScore(f));
            }

        }

        normalizeAroundMiddle(result);
        return result;
    }

    private void normalizeAroundMiddle(LinkedHashMap<String, Integer> result) {
        int middleIndex = result.size() / 2;
        List<Map.Entry<String, Integer>> entryList = new LinkedList<Map.Entry<String, Integer>>(result.entrySet());
        List<String> keySet = new ArrayList<>(result.keySet());
        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> stringIntegerEntry, Map.Entry<String, Integer> t1) {
                return(stringIntegerEntry.getValue().compareTo(t1.getValue()));
            }
        });
        for(int i = 0; i < keySet.size(); i++){
            if(i < middleIndex){
                result.put(keySet.get(i), 1);
            }
            else{
                result.put(keySet.get(i), 2);
            }
        }

    }

    private Integer pltScore(Faculty f) {
        int s = 0;
        switch (f.getPlanningPeriod()){
            case 2:
                s = 1;
            case 3:
                s = 5;
            default:
                s = 0;

        }
        return s;
    }

    private void setNumOnDay(ArrayList<TimeSlot> timeSlots) {
        HashMap<String, Integer> dayFreqMap = createFreqMap(timeSlots);
        for(int i = timeSlots.size() - 1; i >= 0; i--){
            timeSlots.get(i).setNumOnDay(dayFreqMap.get(timeSlots.get(i).getDay()));
            dayFreqMap.put(timeSlots.get(i).getDay(), dayFreqMap.get(timeSlots.get(i).getDay()) - 1);
        }
    }

    private HashMap<String, Integer> createFreqMap(ArrayList<TimeSlot> timeSlots) {
        HashMap<String, Integer> freqMap = new HashMap<>();
        for(TimeSlot timeSlot : timeSlots){
            if(!freqMap.containsKey(timeSlot.getDay())){
                freqMap.put(timeSlot.getDay(), 1);
            }
            else{
                freqMap.put(timeSlot.getDay(), freqMap.get(timeSlot.getDay()) + 1);
            }

        }
        return freqMap;
    }

    public HashMap<TimeSlot, String> getMeetingSchedule() {
        return meetingSchedule;
    }

    public void setMeetingSchedule(HashMap<TimeSlot, String> meetingSchedule) {
        this.meetingSchedule = meetingSchedule;
    }
}
