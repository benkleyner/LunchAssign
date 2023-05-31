import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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

    public static ArrayList<Tag> loadTags() throws SQLException{
        ArrayList<Tag> tags = new ArrayList<Tag>();

        Statement stmt = Main.getConn().createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM Tags");
        while(result.next()){
            Tag tag = new Tag(result.getString("TagName"), result.getInt("TagWeight"), result.getInt("TagID"));
            tags.add(tag);
        }
        return tags;
    }

    public static void loadTeachersFromCSV(File file) throws SQLException, FileNotFoundException {
        Scanner s = new Scanner(file);
        Statement stmt = Main.getConn().createStatement();
        while(s.hasNextLine()){
            String[] vals = s.nextLine().split(",");
            if(!vals[0].equals("Name")){
                String name = vals[0];
                int planningPeriod = Integer.parseInt(vals[1]);
                String PLT = vals[2];
                stmt.executeUpdate("INSERT INTO Teachers(TeacherName, PlanningPeriod, PLT) VALUES (\"" + name + "\", " + planningPeriod + ", \"" + PLT + "\");");
            }
        }
    }
}
