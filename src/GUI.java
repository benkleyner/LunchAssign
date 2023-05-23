import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class GUI extends JFrame{
    private JTabbedPane tabs;
    private JPanel mainPanel;
    private JScrollPane schedule;
    private JPanel teachers;
    private JPanel locationsPanel;
    private JTable teacherTable;
    private JButton teacherSaveButton;
    private JTable locationTable;
    private JButton locationSaveButton;
    private JButton addLocationButton;
    private JButton addTeacherButton;
    private JScrollPane teacherTablePane;
    private JScrollPane locationTablePane;
    private JTabbedPane editTabs;
    private JPanel timeSlotsPanel;
    private JButton addTimeSlotButton;
    private JTable timeSlotTable;
    private JScrollPane timeSlotTablePane;
    private JButton removeTimeSlotButton;
    private JTable table1;
    private JButton generateScheduleButton;
    private JTable tagList;
    private JButton addTagButton;
    private JButton removeTagButton;
    private JPanel tagPanel;
    private JButton removeLocationButton;
    private JButton editLocationButton;
    private JButton removeTeacherButton;
    private JButton editTeacherButton;
    private JButton editTimeSlotButton;
    private JButton editTagButton;


    private ArrayList<Faculty> teacherNames = LoadData.loadTeacherNames();
    private ArrayList<Location> locations = LoadData.loadLocations();
    private ArrayList<TimeSlot> timeSlots = LoadData.loadTimeSlots();

    private ArrayList<Tag> tags = LoadData.loadTags();



    public GUI() throws IOException, SQLException {
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(640, 480);
        initTables();
        setVisible(true);
        Statement stmt = Main.getConn().createStatement();
        addTimeSlotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimeSlotPopup popup = new TimeSlotPopup();
                popup.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        super.windowClosed(e);
                        String startTime = popup.getStartHoursValue() + ":" + popup.getStartMinutesValue() + popup.getStartAMPMValue();
                        String endTime = popup.getEndHoursValue() + ":" + popup.getEndMinutesValue() + popup.getEndAMPMValue();
                        try {
                            stmt.execute("INSERT INTO TimeSlots(Day, TimePeriod) VALUES(\"" + popup.getDay() + "\", \"" + startTime + "-" + endTime + "\");");
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                        try {
                            updateSQL();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        updateTimeSlotTable();
                    }
                });

            }
        });

        removeTimeSlotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(timeSlotTable.getSelectedRow() != -1){
                    try {
                        stmt.executeUpdate("DELETE FROM TimeSlots WHERE TimeSlotID=" + timeSlots.get(timeSlotTable.getSelectedRow()).getID() + ";");
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        updateSQL();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    updateTimeSlotTable();
                }
                else{
                    WarningPopup noRowSelected = new WarningPopup("No row selected!");
                }
            }
        });

        editTimeSlotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(timeSlotTable.getSelectedRow() != -1){
                    String day = (String) timeSlotTable.getValueAt(timeSlotTable.getSelectedRow(), 0);
                    String startTime = (String) timeSlotTable.getValueAt(timeSlotTable.getSelectedRow(), 1);
                    String endTime = (String) timeSlotTable.getValueAt(timeSlotTable.getSelectedRow(), 2);

                    String sh = startTime.substring(0, 2);
                    String eh = endTime.substring(0, 2);
                    String sm = startTime.substring(3, 5);
                    String em = endTime.substring(3, 5);
                    String sa = startTime.substring(5);
                    String ea = endTime.substring(5);
                    if(startTime.length() == 6){
                        sh = sh.substring(0, 1);
                        sm = startTime.substring(2, 4);
                        sa = startTime.substring(4);
                    }
                    if(endTime.length() == 6){
                        eh = eh.substring(0, 1);
                        em = endTime.substring(2, 4);
                        ea = endTime.substring(4);
                    }
                    TimeSlotPopup timeSlotPopup = new TimeSlotPopup(Integer.parseInt(sh), sm, sa, Integer.parseInt(eh), em, ea, day);
                    timeSlotPopup.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            super.windowClosed(e);
                            try {
                                Statement stmt = Main.getConn().createStatement();
                                stmt.executeUpdate("UPDATE TimeSlots SET TimePeriod=\"" + timeSlotPopup.getStartHoursValue() + ":" + timeSlotPopup.getStartMinutesValue() + timeSlotPopup.getStartAMPMValue() + "-" + timeSlotPopup.getEndHoursValue() + ":" + timeSlotPopup.getEndMinutesValue() + timeSlotPopup.getEndAMPMValue() + "\", Day=\"" + timeSlotPopup.getDay() + "\"" + " WHERE TimeSlotID=" + timeSlots.get(timeSlotTable.getSelectedRow()).getID() + ";");
                                updateSQL();
                                updateTimeSlotTable();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    });
                }
                else{
                    WarningPopup warningPopup = new WarningPopup("No row selected!");
                }
            }
        });


        addTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTeacherPopup addTeacherPopup = new AddTeacherPopup();
                addTeacherPopup.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        super.windowClosed(e);
                        try {
                            if(addTeacherPopup.getTeacherName() != null){
                                Statement stmt = Main.getConn().createStatement();
                                stmt.executeUpdate("INSERT INTO Teachers(TeacherName, PlanningPeriod, PLT) VALUES (\"" + addTeacherPopup.getTeacherName() + "\"," + addTeacherPopup.getPlanningPeriod() + ", \"" + addTeacherPopup.getPLT() + "\");");
                                updateSQL();
                                initTables();
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });

            }
        });
        removeTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(teacherTable.getSelectedRow() != -1){
                    try{
                        Statement stmt = Main.getConn().createStatement();
                        stmt.executeUpdate("DELETE FROM Teachers WHERE TeacherID=" + teacherNames.get(teacherTable.getSelectedRow()).getID() + ";");
                        updateSQL();
                        initTables();
                    }
                    catch(SQLException ex){
                        ex.printStackTrace();
                    }
                }
                else{
                    WarningPopup warningPopup = new WarningPopup("No row selected!");
                }
            }
        });
        editTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(teacherTable.getSelectedRow() != -1){
                    AddTeacherPopup addTeacherPopup = new AddTeacherPopup((String) teacherTable.getValueAt(teacherTable.getSelectedRow(), 0), (Integer) teacherTable.getValueAt(teacherTable.getSelectedRow(), 1), (String) teacherTable.getValueAt(teacherTable.getSelectedRow(), 3));
                    addTeacherPopup.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            super.windowClosed(e);
                            if(addTeacherPopup.getTeacherName() != null){
                                try {
                                    Statement stmt = Main.getConn().createStatement();
                                    stmt.executeUpdate("UPDATE Teachers SET TeacherName=\"" + addTeacherPopup.getTeacherName() + "\", PlanningPeriod=" + addTeacherPopup.getPlanningPeriod() + ", PLT=\"" + addTeacherPopup.getPLT() + "\" WHERE TeacherID=" + teacherNames.get(teacherTable.getSelectedRow()).getID() + ";");
                                    updateSQL();
                                    initTables();
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        }
                    });
                }
                else{
                    WarningPopup warningPopup = new WarningPopup("No row selected!");
                }
            }
        });
        addTagButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTagPopup addTagPopup = new AddTagPopup();
                addTagPopup.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        super.windowClosed(e);
                        if(addTagPopup.getTagName() != null){
                            //add column to SQL Tables
                            try {
                                Statement stmt = Main.getConn().createStatement();
                                stmt.executeUpdate("ALTER TABLE Teachers ADD " + addTagPopup.getTagName() + " INTEGER DEFAULT 0;");
                                stmt.executeUpdate("ALTER TABLE Locations ADD " + addTagPopup.getTagName() + " INTEGER DEFAULT 0;");
                                stmt.executeUpdate("INSERT INTO Tags(TagName, TagWeight) VALUES (\"" + addTagPopup.getTagName() + "\", " + addTagPopup.getWeight() + ");");
                                DefaultTableModel tagListModel = (DefaultTableModel) tagList.getModel();
                                tagListModel.addRow(new Object[]{addTagPopup.getTagName(), addTagPopup.getWeight()});
                                tagList.setModel(tagListModel);
                                updateSQL();
                                initTables();
                            } catch (SQLException ex) {
                                WarningPopup duplicateColumn = new WarningPopup("This tag already exists!");
                            }
                        }
                    }
                });
            }
        });
        removeTagButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tagList.getSelectedRow() != -1){
                    try {
                        Statement stmt = Main.getConn().createStatement();
                        stmt.executeUpdate("DELETE FROM Tags WHERE TagID=" + tags.get(tagList.getSelectedRow()).getID() + ";");
                        stmt.executeUpdate("ALTER TABLE Teachers DROP COLUMN " + tagList.getValueAt(tagList.getSelectedRow(), 0) + ";");
                        stmt.executeUpdate("ALTER TABLE Locations DROP COLUMN " + tagList.getValueAt(tagList.getSelectedRow(), 0) + ";");
                        updateSQL();
                        initTables();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        editTagButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tagList.getSelectedRow() != -1){
                    AddTagPopup addTagPopup = new AddTagPopup((String) tagList.getValueAt(tagList.getSelectedRow(), 0), (Integer) tagList.getValueAt(tagList.getSelectedRow(), 1));
                    addTagPopup.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            super.windowClosed(e);
                            try {
                                Statement stmt = Main.getConn().createStatement();
                                stmt.executeUpdate("ALTER TABLE Teachers RENAME COLUMN " + tagList.getValueAt(tagList.getSelectedRow(), 0) + " to " + addTagPopup.getTagName() + ";");
                                stmt.executeUpdate("ALTER TABLE Locations RENAME COLUMN " + tagList.getValueAt(tagList.getSelectedRow(), 0) + " to " + addTagPopup.getTagName() + ";");
                                stmt.executeUpdate("UPDATE Tags SET TagName=\"" + addTagPopup.getTagName() + "\" , TagWeight=" + addTagPopup.getWeight() + " WHERE TagID=" + tags.get(tagList.getSelectedRow()).getID() + ";");
                                updateSQL();
                                initTables();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    });
                }
                else{
                    WarningPopup warningPopup = new WarningPopup("No row selected!");
                }
            }
        });
        addLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddLocationPopup addLocationPopup = new AddLocationPopup();
                addLocationPopup.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        super.windowClosed(e);
                        try {
                            if(addLocationPopup.getLocationName() != null && addLocationPopup.getLocationDescription() != null){
                                Statement stmt = Main.getConn().createStatement();
                                stmt.executeUpdate("INSERT INTO Locations(LocationName, LocationDesc) VALUES (\"" + addLocationPopup.getLocationName() + "\", \"" + addLocationPopup.getLocationDescription() + "\");");
                                updateSQL();
                                initTables();
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
            }
        });
        removeLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(locationTable.getSelectedRow() != -1){
                    try{
                        Statement stmt = Main.getConn().createStatement();
                        stmt.executeUpdate("DELETE FROM Locations WHERE LocationID=" + locations.get(locationTable.getSelectedRow()).getID() + ";");
                        updateSQL();
                        initTables();
                    }
                    catch(SQLException ex){
                        ex.printStackTrace();
                    }
                }
                else{
                    WarningPopup warningPopup = new WarningPopup("No row selected!");
                }
            }
        });
        editLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(locationTable.getSelectedRow() != -1){
                    AddLocationPopup addLocationPopup = new AddLocationPopup((String) locationTable.getValueAt(locationTable.getSelectedRow(), 0), (String) locationTable.getValueAt(locationTable.getSelectedRow(), 1));
                    addLocationPopup.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            super.windowClosed(e);
                            if(addLocationPopup.getLocationDescription() != null && addLocationPopup.getLocationName() != null){
                                try {
                                    Statement stmt = Main.getConn().createStatement();
                                    stmt.executeUpdate("UPDATE Locations SET LocationName=\"" + addLocationPopup.getLocationName() + "\", LocationDesc=\"" + addLocationPopup.getLocationDescription() + "\" WHERE LocationID=" + locations.get(locationTable.getSelectedRow()).getID() + ";");
                                    updateSQL();
                                    initTables();
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        }
                    });
                }
                else{
                    WarningPopup warningPopup = new WarningPopup("No row selected!");
                }
            }
        });
        teacherSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gatherTeacherUpdate();
                try {
                    pushTeacherUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                WarningPopup saved = new WarningPopup("Changes saved!");
            }
        });
        locationSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gatherLocationUpdate();
                try {
                    pushLocationUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                WarningPopup saved = new WarningPopup("Changes saved!");
            }
        });
        generateScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void initTables() throws SQLException{
        Statement stmt  = Main.getConn().createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM Teachers");
        ResultSetMetaData rsmd = result.getMetaData();
        String[] teacherColumnNames = new String[rsmd.getColumnCount() - 1];
        for(int i = 2; i <= rsmd.getColumnCount(); i++){
            teacherColumnNames[i - 2] = rsmd.getColumnName(i);
        }
        teacherColumnNames[0] = "Teacher";
        teacherColumnNames[1] = "Planning Period";
        teacherColumnNames[2] = "Administrator";
        Object[][] teacherData = new Object[teacherNames.size()][teacherColumnNames.length];
        for(Faculty teacher : teacherNames){
            teacherData[teacherNames.indexOf(teacher)][0] = teacher.getName();
            teacherData[teacherNames.indexOf(teacher)][1] = teacher.getPlanningPeriod();
            teacherData[teacherNames.indexOf(teacher)][2] = teacher.isAdmin();
            teacherData[teacherNames.indexOf(teacher)][3] = teacher.getPLT();
            for(int i = 4; i < teacherData[0].length; i++){
                teacherData[teacherNames.indexOf(teacher)][i] = teacher.getReqs().get(teacherColumnNames[i]);
            }
        }
        teacherTable.setModel(new CustomTableModel(teacherData, teacherColumnNames));

        ResultSet result1 = stmt.executeQuery("SELECT * FROM Locations");
        ResultSetMetaData rsmd1 = result1.getMetaData();
        String[] locationColumnNames = new String[rsmd1.getColumnCount() - 1];
        for(int i = 2; i <= rsmd1.getColumnCount(); i++){
            locationColumnNames[i - 2] = rsmd1.getColumnName(i);
        }
        locationColumnNames[0] = "Location";
        locationColumnNames[1] = "Description";
        Object[][] locationData = new Object[locations.size()][locationColumnNames.length];
        for(Location location : locations){
            locationData[locations.indexOf(location)][0] = location.getName();
            locationData[locations.indexOf(location)][1] = location.getDescription();
            for(int i = 2; i < locationData[0].length; i++){
                locationData[locations.indexOf(location)][i] = location.getReqs().get(locationColumnNames[i]);
            }
        }
        locationTable.setModel(new CustomTableModel(locationData, locationColumnNames));

        updateTimeSlotTable();

        Object[][] tagData = new Object[tags.size()][2];
        int i = 0;
        for(Tag tag : tags){
            tagData[i][0] = tag.getName();
            tagData[i][1] = tag.getWeight();
            i++;
        }
        tagList.setModel(new DefaultTableModel(tagData, new Object[]{"Tag", "Weight"}));
    }

    private void updateTimeSlotTable() {
        DefaultTableModel timeSlotTableModel = new DefaultTableModel();
        timeSlotTableModel.addColumn("Day");
        timeSlotTableModel.addColumn("Start Time");
        timeSlotTableModel.addColumn("End Time");
        for(TimeSlot timeSlot : timeSlots){
            timeSlotTableModel.addRow(new String[]{timeSlot.getDay(), timeSlot.getStartTime(), timeSlot.getEndTime()});
        }
        timeSlotTable.setModel(timeSlotTableModel);
    }

    private void updateSQL() throws SQLException{
        tags = LoadData.loadTags();
        teacherNames = LoadData.loadTeacherNames();
        locations = LoadData.loadLocations();
        timeSlots = LoadData.loadTimeSlots();
    }

    private void gatherTeacherUpdate(){
        for(int i = 0; i < teacherNames.size(); i++){
            teacherNames.get(i).setAdmin((Boolean) teacherTable.getValueAt(i, 2));
            HashMap<String, Boolean> newReqs = new HashMap<String, Boolean>();
            for(int j = 4; j < teacherTable.getColumnCount(); j++){
                newReqs.put(teacherTable.getColumnName(j), (Boolean) teacherTable.getValueAt(i, j));
            }
            teacherNames.get(i).setReqs(newReqs);
        }
    }

    private void pushTeacherUpdate() throws SQLException{
        Statement stmt = Main.getConn().createStatement();
        for(Faculty teacher : teacherNames){
            String SQL = "UPDATE Teachers SET IsAdmin=" + (teacher.isAdmin() ? 1 : 0);
            for(Map.Entry<String, Boolean> entry : teacher.getReqs().entrySet()){
                SQL += ", " + entry.getKey() + "=" + (entry.getValue() ? 1 : 0) + " ";
            }
            SQL += "WHERE TeacherID=" + teacher.getID() + ";";
            stmt.executeUpdate(SQL);
        }
    }

    private void gatherLocationUpdate(){
        for(int i = 0; i < locations.size(); i++){
            HashMap<String, Boolean> newReqs = new HashMap<String, Boolean>();
            for(int j = 2; j < locationTable.getColumnCount(); j++){
                newReqs.put(locationTable.getColumnName(j), (Boolean) locationTable.getValueAt(i, j));
            }
            locations.get(i).setReqs(newReqs);
        }
    }

    private void pushLocationUpdate() throws SQLException{
        Statement stmt = Main.getConn().createStatement();
        for(Location location : locations){
            String SQL = "UPDATE Locations SET ";
            int i = 0;
            for(Map.Entry<String, Boolean> entry : location.getReqs().entrySet()){
                SQL += entry.getKey() + "=" + (entry.getValue() ? 1 : 0);
                if(i < location.getReqs().size() - 1){
                    SQL += ", ";
                }
                i++;
            }
            SQL += " WHERE LocationID=" + location.getID() + ";";
            stmt.executeUpdate(SQL);
        }
    }

}
