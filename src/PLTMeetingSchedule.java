import java.util.*;

public class PLTMeetingSchedule {
    private HashMap<TimeSlot, String> meetingSchedule;

    public PLTMeetingSchedule(ArrayList<Faculty> teachers, ArrayList<TimeSlot> timeSlots){
        meetingSchedule = createMeetingSchedule(teachers, timeSlots);
    }

    private LinkedHashMap<TimeSlot, String> createMeetingSchedule(ArrayList<Faculty> teachers, ArrayList<TimeSlot> timeSlots) {
        LinkedHashMap<TimeSlot, String> meetingSchedule = new LinkedHashMap<>();
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

        result = normalizeAroundMiddle(result);
        return result;
    }

    private LinkedHashMap<String, Integer> normalizeAroundMiddle(LinkedHashMap<String, Integer> result) {
        int middleIndex = result.size() / 2;
        List<Map.Entry<String, Integer>> entryList = new LinkedList<Map.Entry<String, Integer>>(result.entrySet());

        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> stringIntegerEntry, Map.Entry<String, Integer> t1) {
                return(stringIntegerEntry.getValue().compareTo(t1.getValue()));
            }
        });

        System.out.println(entryList);

        LinkedHashMap<String, Integer> toReturn = new LinkedHashMap<>();
        for(Map.Entry<String, Integer> stringIntegerEntry: entryList){
            toReturn.put(stringIntegerEntry.getKey(), stringIntegerEntry.getValue());
        }
        List<String> keySet = new ArrayList<>();
        for(Map.Entry<String, Integer> stringIntegerEntry: entryList){
            keySet.add(stringIntegerEntry.getKey());
        }
        for(int i = 0; i < middleIndex; i++){
            toReturn.put(keySet.get(i), 1);
        }
        for(int i = keySet.size() - 1; i >= middleIndex; i--){
            toReturn.put(keySet.get(i), 2);
        }

        return toReturn;

    }

    private Integer pltScore(Faculty f) {
        int s = 0;
        int pp = f.getPlanningPeriod();
        if(pp == 1){
            s = 1;
        } else if (pp == 2) {
            s = 2;
        } else if(pp == 3){
            s = 3;
        } else{
            s = 1;
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
