package GUI;

import Logic.BoughtProductsListCreator;
import Logic.Customer;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.util.ArrayList;

/**
 * Created by tomek on 16.12.15.
 */
public class CustomerTreeSelectionListener implements TreeSelectionListener {
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        new Thread(() -> {

            System.out.println("Selection changed");
            TreePath selectedPath = e.getPath();
            //System.out.println(((Customer)((DefaultMutableTreeNode)e.getPath().getLastPathComponent()).getUserObject()).getId());
            ArrayList<Customer> selectedCustomers = new ArrayList<>();

            selectedCustomers.add((Customer) ((DefaultMutableTreeNode) selectedPath.getLastPathComponent()).getUserObject());
            BoughtProductsListCreator listCreator = BoughtProductsListCreator.getInstance();
            listCreator.generateTable(selectedCustomers);

        }).start();
    }
    //TODO Zaznaczenie wybiera tylko jednego klienta, do wybrania kilku potrzeba Buttona
}
