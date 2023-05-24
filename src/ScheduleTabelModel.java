import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ScheduleTabelModel extends DefaultTableModel {

    private ArrayList<Color> colors;
    public ScheduleTabelModel(Object[][] data, String[] columnNames){
        super(data, columnNames);
        this.colors = createEmptyColorList(data.length + 1);
    }

    private ArrayList<Color> createEmptyColorList(int l){
        ArrayList<Color> result = new ArrayList<>();
        for(int i = 0; i < l; i++){
            result.add(Color.WHITE);
        }
        return result;
    }

    public void setRowColor(int row, Color c){
        colors.set(row, c);
        fireTableRowsUpdated(row, row);
    }

    public Color getRowColor(int row){
        return colors.get(row);
    }
}
