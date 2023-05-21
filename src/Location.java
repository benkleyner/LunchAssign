import java.util.ArrayList;
import java.util.HashMap;

public class Location {
    private String name;
    private HashMap<String, Boolean> reqs;


    private String description;

    private int ID;

    /**
     * default constructor
     */
    public Location(){
        this.name = "";
        this.description = "";
        this.reqs = new HashMap<String, Boolean>();
    }

    /**
     * Constructor with params
     * @param name - a String - the name of the location
     * @param reqs - a HashMap - a map of Strings to booleans (tags, does location require the tag)
     */
    public Location(String name, String description, HashMap<String, Boolean> reqs, int id){
        this.name = name;
        this.description = description;
        this.reqs = reqs;
        this.ID = id;
    }

    /**
     * @return String name of location
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return HashMap of String to Booleans (tags, does the location have the tag)
     */
    public HashMap<String, Boolean> getReqs() {
        return this.reqs;
    }

    public String getDescription() {
        return description;
    }

    public int getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReqs(HashMap<String, Boolean> reqs) {
        this.reqs = reqs;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", reqs=" + reqs +
                ", description='" + description + '\'' +
                '}';
    }
}
