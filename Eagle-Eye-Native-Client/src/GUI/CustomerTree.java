package GUI;

import Logic.BoughtProductsListCreator;
import Logic.Customer;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by tomek on 16.12.15.
 */
public class CustomerTree extends JTree implements ActionListener {
    public CustomerTree(CustomerTreeModel customerTreeModel) {
        super(customerTreeModel);
        addTreeSelectionListener(new CustomerTreeSelectionListener());
        //this.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    }

    private void updateProductList() {
        BoughtProductsListCreator creator = BoughtProductsListCreator.getInstance();
        TreePath[] selectedPaths = this.getSelectionPaths();
        ArrayList<Customer> selectedCustomers = new ArrayList<>();
        for (TreePath p : selectedPaths)
            selectedCustomers.add((Customer) ((DefaultMutableTreeNode) p.getLastPathComponent()).getUserObject());
        creator.generateTable(selectedCustomers);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateProductList();
    }
}
