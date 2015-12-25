package GUI;

import GUI.InsertCustomer.InsertCustomerDialog;
import Logic.BoughtProductsListCreator;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by tomek on 15.12.15.
 */
public class Window {
    private static JFrame frame;
    private JButton loadProductsButton;
    private JTable productTable;
    private JPanel mainPanel;
    private CustomerTree customerTree;
    private JXDatePicker fromDatePicker;
    private JXDatePicker toDatePicker;
    private JButton showInsertClientDialogButton;
    private JTabbedPane tabbedPane1;

    public static void main(String[] args) {
        frame = new JFrame("Eagle Eye");
        frame.setContentPane(new Window().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private void createUIComponents() throws IOException, SQLException {
        customerTree = new CustomerTree(new CustomerTreeModel());
        fromDatePicker = new JXDatePicker();
        toDatePicker = new JXDatePicker();
        productTable = new JTable(new ProductTableModel());
        loadProductsButton = new JButton();
        BoughtProductsListCreator.getInstance().setDatePickers(fromDatePicker, toDatePicker);
        BoughtProductsListCreator.getInstance().addPropertyChangeListener((ProductTableModel) productTable.getModel());
        loadProductsButton.addActionListener(customerTree);
        showInsertClientDialogButton = new JButton();

        showInsertClientDialogButton.addActionListener(e -> new InsertCustomerDialog(frame, customerTree));
    }
}

