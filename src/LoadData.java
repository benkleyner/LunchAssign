import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class LoadData {
    public static ArrayList<Faculty> loadTeacherNames() throws SQLException{
        ArrayList<Faculty> teachers = new ArrayList<Faculty>();
        Statement stmt = Main.getConn().createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM Teachers");
        ResultSetMetaData rsmd = result.getMetaData();
        while(result.next()){
            String name = result.getString("TeacherName");
            int planningPeriod = result.getInt("PlanningPeriod");
            boolean isAdmin = result.getInt("IsAdmin") == 1;
            String PLT = result.getString("PLT");
            int ID = result.getInt("TeacherID");
            HashMap<String, Boolean> teacherReqs = new HashMap<String, Boolean>();
            if(rsmd.getColumnCount() > 5){
                for(int i = 6; i <= rsmd.getColumnCount(); i++){
                    teacherReqs.put(rsmd.getColumnName(i), result.getInt(i) == 1);
                }
            }
            Faculty teacher = new Faculty(name, PLT, planningPeriod, isAdmin, teacherReqs, ID);
            teachers.add(teacher);
        }
        return teachers;
    }

    public static ArrayList<Location> loadLocations() throws SQLException{
        ArrayList<Location> locations = new ArrayList<>();
        Statement stmt = Main.getConn().createStatement();
        ResultSet result = stmt.executeQuery("SELECT  * FROM Locations");
        ResultSetMetaData rsmd = result.getMetaData();
        while(result.next()){
            String name = result.getString("LocationName");
            String desc = result.getString("LocationDesc");
            int ID = result.getInt("LocationID");
            HashMap<String, Boolean> locationsReqs = new HashMap<String, Boolean>();
            if(rsmd.getColumnCount() > 2){
                for(int i = 3; i <= rsmd.getColumnCount(); i++){
                    locationsReqs.put(rsmd.getColumnName(i), result.getInt(i) == 1);
                }
            }
            Location location = new Location(name, desc, locationsReqs, ID);
            locations.add(location);
        }
        return locations;
    }

    public static ArrayList<TimeSlot> loadTimeSlots() throws SQLException{
        Statement stmt = Main.getConn().createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM TimeSlots");
        ArrayList<TimeSlot> slots = new ArrayList<TimeSlot>();
        while(result.next()){
            String timePeriod = result.getString("TimePeriod");
            int ID = result.getInt("TimeSlotID");
            int indexOfHyphen = timePeriod.indexOf("-");
            TimeSlot timeSlot = new TimeSlot(timePeriod.substring(0, indexOfHyphen), timePeriod.substring(indexOfHyphen + 1), result.getString("Day"), ID);
            slots.add(timeSlot);
        }
        return slots;
    }

    public static HashMap<String, Integer> loadTags() throws SQLException{
        HashMap<String, Integer> tags = new HashMap<String, Integer>();

        Statement stmt = Main.getConn().createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM Tags");
        while(result.next()){
            tags.put(result.getString("TagName"), result.getInt("TagWeight"));
        }
        return tags;
    }
}
