import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AddTeacherPopup extends JFrame {
    private JTextField teacherNameSelect;
    private JComboBox planningPeriodSelect;
    private JComboBox PLTSelect;
    private JPanel popupPanel;
    private JButton OKButton;
    private JButton cancelButton;

    private String teacherName;
    private int planningPeriod;
    private String PLT;

    public AddTeacherPopup(){
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setContentPane(popupPanel);
        setSize(200, 300);

        teacherNameSelect.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(teacherNameSelect.getText().equals("Teacher Name.........")){
                    teacherNameSelect.setText("");
                }
            }
        });
        teacherNameSelect.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(teacherNameSelect.getText().equals("")){
                    teacherNameSelect.setText("Teacher Name.........");
                }
            }
        });
        setVisible(true);

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(teacherNameSelect.getText().equals("Teacher Name.........") || teacherNameSelect.getText().equals("")){
                    WarningPopup warningPopup = new WarningPopup("Please complete all fields!");
                }
                else{
                    teacherName = teacherNameSelect.getText();
                    if(planningPeriodSelect.getSelectedItem().equals("None")){
                        planningPeriod = 0;
                    }
                    else {
                        planningPeriod = Integer.parseInt((String) planningPeriodSelect.getSelectedItem());
                    }
                    PLT = (String) PLTSelect.getSelectedItem();
                    dispose();
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        OKButton.requestFocusInWindow();
    }

    public AddTeacherPopup(String name, int pp, String plt){
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setContentPane(popupPanel);
        setSize(200, 300);


        teacherNameSelect.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(teacherNameSelect.getText().equals("Teacher Name.........")){
                    teacherNameSelect.setText("");
                }
            }
        });
        teacherNameSelect.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(teacherNameSelect.getText().equals("")){
                    teacherNameSelect.setText("Teacher Name.........");
                }
            }
        });

        teacherNameSelect.setText(name);
        planningPeriodSelect.setSelectedItem(String.valueOf(pp));
        PLTSelect.setSelectedItem(plt);
        setVisible(true);

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(teacherNameSelect.getText().equals("Teacher Name.........") || teacherNameSelect.getText().equals("")){
                    WarningPopup warningPopup = new WarningPopup("Please complete all fields!");
                }
                else{
                    teacherName = teacherNameSelect.getText();
                    if(planningPeriodSelect.getSelectedItem().equals("None")){
                        planningPeriod = 0;
                    }
                    else {
                        planningPeriod = Integer.parseInt((String) planningPeriodSelect.getSelectedItem());
                    }
                    PLT = (String) PLTSelect.getSelectedItem();
                    dispose();
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        OKButton.requestFocusInWindow();
    }

    public int getPlanningPeriod() {
        return planningPeriod;
    }

    public String getPLT() {
        return PLT;
    }

    public String getTeacherName() {
        return teacherName;
    }
}
