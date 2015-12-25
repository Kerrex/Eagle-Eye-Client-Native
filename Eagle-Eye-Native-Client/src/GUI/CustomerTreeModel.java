package GUI;

import Logic.Customer;
import Logic.CustomerList;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by tomek on 16.12.15.
 */
public class CustomerTreeModel extends DefaultTreeModel {

    public CustomerTreeModel(TreeNode root) {
        super(root);
    }

    public CustomerTreeModel(TreeNode root, boolean asksAllowsChildren) {
        super(root, asksAllowsChildren);
    }

    public CustomerTreeModel() throws IOException, SQLException {
        super(new DefaultMutableTreeNode());
        createTreeModel();
    }

    private void createTreeModel() throws IOException, SQLException {
        CustomerList.getInstance().load();
        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) getRoot();
        for (Customer c : CustomerList.getInstance().getCustomerList()) rootNode.add(new DefaultMutableTreeNode(c));
    }

    public void updateModel() {
        ((DefaultMutableTreeNode) getRoot()).removeAllChildren();
        CustomerList.getInstance().getCustomerList().clear();
        try {
            createTreeModel();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        reload();
    }
}
