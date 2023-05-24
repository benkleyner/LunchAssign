public class SchedulableFaculty extends Faculty{

    private int countAssignments;

    private boolean eligible;
    private String lastDaySelected;

    public SchedulableFaculty(Faculty teacher){
        super(teacher.getName(), teacher.getPLT(), teacher.getPlanningPeriod(), teacher.isAdmin(), teacher.getReqs(), teacher.getID());
        this.countAssignments = 0;
        this.eligible = true;
        this.lastDaySelected = "";
    }

    public int getCountAssignments() {
        return countAssignments;
    }

    public boolean isEligible() {
        return eligible;
    }

    public String getLastDaySelected() {
        return lastDaySelected;
    }

    public void setCountAssignments(int countAssignments) {
        this.countAssignments = countAssignments;
    }

    public void setEligible(boolean eligible) {
        this.eligible = eligible;
    }

    public void increaseAssignmentCount(){
        this.countAssignments++;
    }

    public void setLastDaySelected(String lastDaySelected) {
        this.lastDaySelected = lastDaySelected;
    }

    @Override
    public String toString() {
        return super.toString() + "SchedulableFaculty{" +
                "countAssignments=" + countAssignments +
                ", eligible=" + eligible +
                '}';
    }
}
