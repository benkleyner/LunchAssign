import java.util.HashMap;

public class Faculty{
    private String name;
    private String PLT;
    private int planningPeriod;
    private boolean isAdmin;
    private HashMap<String, Boolean> reqs;

    private int ID;
    /** Creates an instance of Faculty with default values */
    public Faculty(){
        this.name = "";
        this.PLT = "";
        this.planningPeriod = 1;
        this.isAdmin = false;
        this.reqs = new HashMap<String, Boolean>();
    }

    /**
     * test constructor, may not be used in final version
     * @param name - teacher name
     */

    /**
     * Creates an instance of Faculty with params passed to constructor
     * @param name - String - name of faculty member
     * @param PLT - String - name of faculty members PLT
     * @param planningPeriod - int - (1-4), teachers planning period
     * @param isAdmin - boolean - is the faculty member an admin
     * @param reqs - HashMap of Strings to Booleans - Strings are tags, booleans are true if faculty member has the tag
     */
    public Faculty(String name, String PLT, int planningPeriod, boolean isAdmin, HashMap<String, Boolean> reqs, int ID){
        this.name = name;
        this.PLT = PLT;
        this.planningPeriod = planningPeriod;
        this.isAdmin = isAdmin;
        this.reqs = reqs;
        this.ID = ID;
    }

    /**
     * @return String name of faculty member
     */
    public String getName() {
        return name;
    }

    /**
     * @return String name of faculty member's PLT
     */
    public String getPLT() {
        return PLT;
    }

    /**
     * @return int number of faculty member's planning period
     * (1-4) if they have it (0) if they don't
     */
    public int getPlanningPeriod() {
        return planningPeriod;
    }

    /**
     * @return is the Faculty Member an admin
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * @return map of Strings to Booleans (tags, does the faculty member have the tag)
     */
    public HashMap<String, Boolean> getReqs() {
        return reqs;
    }

    public int getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setPlanningPeriod(int planningPeriod) {
        this.planningPeriod = planningPeriod;
    }

    public void setPLT(String PLT) {
        this.PLT = PLT;
    }

    public void setReqs(HashMap<String, Boolean> reqs) {
        this.reqs = reqs;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    /** summary of Faculty member*/
    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                ", PLT='" + PLT + '\'' +
                ", planningPeriod=" + planningPeriod +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
