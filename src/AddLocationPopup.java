import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AddLocationPopup extends JFrame {
    private JTextField locationNameField;
    private JTextField locationDescField;
    private JButton cancelButton;
    private JButton OKButton;
    private JPanel mainPanel;

    private String locationName;
    private String locationDescription;

    public AddLocationPopup() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(300, 200);
        setVisible(true);
        locationNameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(locationNameField.getText().equals("Location Name............")){
                    locationNameField.setText("");
                }
            }
        });
        locationNameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(locationNameField.getText().equals("")){
                    locationNameField.setText("Location Name............");
                }
            }
        });
        locationDescField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(locationDescField.getText().equals("Location Description.......")){
                    locationDescField.setText("");
                }
            }
        });
        locationDescField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(locationDescField.getText().equals("")){
                    locationDescField.setText("Location Description.......");
                }
            }
        });
        OKButton.requestFocusInWindow();
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!locationNameField.getText().equals("Location Name............") && !locationDescField.getText().equals("Location Description.......")){
                    locationName = locationNameField.getText();
                    locationDescription = locationDescField.getText();
                    dispose();
                }
                else{
                    WarningPopup invalidInput = new WarningPopup("Please fill out all fields!");
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public AddLocationPopup(String name, String desc) {
        setContentPane(mainPanel);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(300, 200);
        setVisible(true);
        locationNameField.setText(name);
        locationDescField.setText(desc);
        locationNameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(locationNameField.getText().equals("Location Name............")){
                    locationNameField.setText("");
                }
            }
        });
        locationNameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(locationNameField.getText().equals("")){
                    locationNameField.setText("Location Name............");
                }
            }
        });
        locationDescField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(locationDescField.getText().equals("Location Description.......")){
                    locationDescField.setText("");
                }
            }
        });
        locationDescField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(locationDescField.getText().equals("")){
                    locationDescField.setText("Location Description.......");
                }
            }
        });
        OKButton.requestFocusInWindow();
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!locationNameField.getText().equals("Location Name............") && !locationDescField.getText().equals("Location Description.......")){
                    locationName = locationNameField.getText();
                    locationDescription = locationDescField.getText();
                    dispose();
                }
                else{
                    WarningPopup invalidInput = new WarningPopup("Please fill out all fields!");
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public String getLocationName() {
        return locationName;
    }

    public String getLocationDescription() {
        return locationDescription;
    }
}
