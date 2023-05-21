import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AddTagPopup extends JFrame{
    private JPanel mainPanel;
    private JTextField tagNameField;
    private JButton OKButton;
    private JButton cancelButton;
    private JSpinner tagWeightSelect;
    private String tagName;
    private int weight;

    public AddTagPopup(){
        setContentPane(mainPanel);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
        OKButton.requestFocusInWindow();
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tagNameField.getText().equals("Tag Name..............")){
                    WarningPopup noNameSelected = new WarningPopup("Please enter a name!");
                }
                else{
                    tagName = tagNameField.getText();
                    weight = (int) tagWeightSelect.getValue();
                    dispose();
                }

            }
        });
        tagNameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(tagNameField.getText().equals("Tag Name..............")){
                    tagNameField.setText("");
                }
            }
        });
        tagNameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(tagNameField.getText().equals("")){
                    tagNameField.setText("Tag Name..............");
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

    public AddTagPopup(String n, int w){
        setContentPane(mainPanel);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
        tagNameField.setText(n);
        tagWeightSelect.setValue(w);
        OKButton.requestFocusInWindow();
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tagNameField.getText().equals("Tag Name..............")){
                    WarningPopup noNameSelected = new WarningPopup("Please enter a name!");
                }
                else{
                    tagName = tagNameField.getText();
                    weight = (int) tagWeightSelect.getValue();
                    dispose();
                }

            }
        });
        tagNameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(tagNameField.getText().equals("Tag Name..............")){
                    tagNameField.setText("");
                }
            }
        });
        tagNameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(tagNameField.getText().equals("")){
                    tagNameField.setText("Tag Name..............");
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

    public String getTagName() {
        return tagName;
    }

    public int getWeight() {
        return weight;
    }
}
