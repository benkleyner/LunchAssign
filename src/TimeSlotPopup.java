import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TimeSlotPopup extends JDialog {
    private JPanel popupPanel;
    private JSpinner startHours;
    private JButton OKButton;
    private JSpinner endHours;
    private JSpinner startMinutes;
    private JSpinner endMinutes;
    private JComboBox startAMPM;
    private JComboBox endAMPM;
    private JComboBox daySelect;
    private JButton cancelButton;

    private int startHoursValue;
    private int endHoursValue;
    private String startMinutesValue;
    private String endMinutesValue;
    private String startAMPMValue;
    private String endAMPMValue;

    private String day;

    public TimeSlotPopup(){
        setContentPane(popupPanel);
        setSize(300, 200);
        initComponents();
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setVisible(true);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startHoursValue = (int) startHours.getValue();
                endHoursValue = (int) endHours.getValue();
                startMinutesValue = (String) startMinutes.getValue();
                endMinutesValue = (String) endMinutes.getValue();
                startAMPMValue = (String) startAMPM.getSelectedItem();
                endAMPMValue = (String) endAMPM.getSelectedItem();
                day = (String) daySelect.getSelectedItem();
                dispose();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public TimeSlotPopup(int sh, String sm, String sa, int eh, String em, String ea, String d){
        setContentPane(popupPanel);
        setSize(300, 200);
        initComponents();
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        startHours.setValue(sh);
        startMinutes.setValue(sm);
        startAMPM.setSelectedItem(sa);
        endHours.setValue(eh);
        endMinutes.setValue(em);
        endAMPM.setSelectedItem(ea);
        daySelect.setSelectedItem(d);
        setVisible(true);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startHoursValue = (int) startHours.getValue();
                endHoursValue = (int) endHours.getValue();
                startMinutesValue = (String) startMinutes.getValue();
                endMinutesValue = (String) endMinutes.getValue();
                startAMPMValue = (String) startAMPM.getSelectedItem();
                endAMPMValue = (String) endAMPM.getSelectedItem();
                day = (String) daySelect.getSelectedItem();
                dispose();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void initComponents(){
        ArrayList<Integer> hourValues = new ArrayList<>();
        for(int i = 1; i <= 12; i++){
            hourValues.add(i);
        }

        ArrayList<String> minuteValues = new ArrayList<>();
        for(int i = 0; i < 60; i++){
            if(i < 10){
                minuteValues.add("0" + i);
            }
            else{
                minuteValues.add(String.valueOf(i));
            }
        }

        CyclingSpinnerListModel startHoursModel = new CyclingSpinnerListModel(hourValues.toArray(new Integer[0]));
        CyclingSpinnerListModel endHoursModel = new CyclingSpinnerListModel(hourValues.toArray(new Integer[0]));
        CyclingSpinnerListModel startMinutesModel = new CyclingSpinnerListModel(minuteValues.toArray(new String[0]));
        CyclingSpinnerListModel endMinutesModel = new CyclingSpinnerListModel(minuteValues.toArray(new String[0]));



        DefaultComboBoxModel startAMPMModel = new DefaultComboBoxModel<>(new String[]{"AM", "PM"});
        DefaultComboBoxModel endAMPMModel = new DefaultComboBoxModel<>(new String[]{"AM", "PM"});

        startHours.setModel(startHoursModel);
        endHours.setModel(endHoursModel);
        startMinutes.setModel(startMinutesModel);
        endMinutes.setModel(endMinutesModel);

        startMinutesModel.setLinkedModel(startHoursModel);
        endMinutesModel.setLinkedModel(endHoursModel);
        startAMPM.setModel(startAMPMModel);
        endAMPM.setModel(endAMPMModel);

    }

    public int getStartHoursValue() {
        return startHoursValue;
    }

    public int getEndHoursValue() {
        return endHoursValue;
    }

    public String getStartMinutesValue() {
        return startMinutesValue;
    }

    public String getEndMinutesValue() {
        return endMinutesValue;
    }

    public String getStartAMPMValue() {
        return startAMPMValue;
    }

    public String getEndAMPMValue() {
        return endAMPMValue;
    }

    public String getDay() {
        return day;
    }
}
