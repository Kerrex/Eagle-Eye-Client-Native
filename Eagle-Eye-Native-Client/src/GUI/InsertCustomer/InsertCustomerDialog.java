package GUI.InsertCustomer;

import Database.DBConnector;
import GUI.CustomerTreeModel;

import javax.swing.*;

public class InsertCustomerDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;

    public InsertCustomerDialog(JFrame frame, JTree tree) {
        super(frame);
        setLocationRelativeTo(frame);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.addActionListener(e -> {
            insertCustomer(textField1.getText(), textField2.getText());
            ((CustomerTreeModel) tree.getModel()).updateModel();
            dispose();
        });
        buttonCancel.addActionListener(e -> dispose());
        setTitle("Dodaj nowego klienta");
        pack();
        setVisible(true);
    }

    private void insertCustomer(String custName, String REGON) {
        DBConnector.getInstance().insertCustomer(custName, REGON);
    }

}
