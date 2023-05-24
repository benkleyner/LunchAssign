import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class RowColorRenderer extends DefaultTableCellRenderer {

    public RowColorRenderer() {
        super();

    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        ScheduleTabelModel model = (ScheduleTabelModel) table.getModel();
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row ,column);
        c.setBackground(model.getRowColor(row));
        return c;
    }
}