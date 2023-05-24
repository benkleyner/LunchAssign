public class TimeSlot {
    private String startTime;
    private String endTime;
    private String day;

    private int ID;

    private int numOnDay;

    public TimeSlot(String startTime, String endTime, String day, int id){
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
        this.ID = id;
        this.numOnDay = 0;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getDay() {
        return day;
    }

    public int getID() {
        return ID;
    }

    public int getNumOnDay() {
        return numOnDay;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNumOnDay(int numOnDay) {
        this.numOnDay = numOnDay;
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", day='" + day + '\'' +
                ", ID=" + ID +
                ", numOnDay=" + numOnDay +
                '}';
    }
}
