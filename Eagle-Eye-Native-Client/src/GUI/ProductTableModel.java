package GUI;

import Logic.Product;

import javax.swing.table.AbstractTableModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomek on 17.12.15.
 */
public class ProductTableModel extends AbstractTableModel implements PropertyChangeListener {

    private List<Product> mixedList = new ArrayList<>();

    @Override
    public int getRowCount() {
        return mixedList.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return mixedList.get(rowIndex).toArray()[columnIndex];
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Changed " + evt.getPropertyName());
        mixedList = (List<Product>) evt.getNewValue();
        fireTableDataChanged();
    }
}
