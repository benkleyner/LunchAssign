import javax.swing.table.AbstractTableModel;

public class CustomTableModel extends AbstractTableModel {
    private String[] columnNames;
    private Object[][] data;

    public CustomTableModel(Object[][] data, String[] columnNames){
        this.data = data;
        this.columnNames = columnNames;
    }

    public CustomTableModel(String[] columnNames){
        this.data = new Object[0][0];
        this.columnNames = columnNames;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    public Class getColumnClass(int c) {
        if(getValueAt(0, c) != null){
            return getValueAt(0, c).getClass();
        }
        else{
            return Object.class;
        }

    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (data[row][col].getClass() != Boolean.class) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
    
}
