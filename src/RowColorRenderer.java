import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class RowColorRenderer extends DefaultTableCellRenderer {
    Color backgroundColor, foregroundColor;
    public RowColorRenderer(Color backgroundColor, Color foregroundColor) {
        super();
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
    }
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(row == 0){
            cell.setBackground(backgroundColor);
            cell.setForeground(foregroundColor);
        }
        return cell;
    }
}