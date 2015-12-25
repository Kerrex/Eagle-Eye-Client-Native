package Logic;

import Database.DBConnector;
import org.jdesktop.swingx.JXDatePicker;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomek on 17.12.15.
 */
public class BoughtProductsListCreator implements Serializable {
    private static BoughtProductsListCreator instance;
    JXDatePicker dateFrom;
    JXDatePicker dateTo;
    private List<Customer> customersToLoad;
    private ArrayList<Product> finalProductList;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public BoughtProductsListCreator() {
    }

    public static synchronized BoughtProductsListCreator getInstance() {
        if (instance == null) {
            instance = new BoughtProductsListCreator();
        }
        return instance;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public void generateTable(List<Customer> cust) {
        DBConnector db = DBConnector.getInstance();
        StringBuilder ids = new StringBuilder();
        for (Customer c : cust) ids.append(c.getId() + ",");
        ids.deleteCharAt(ids.length() - 1);
        ArrayList<Product> oldProductList = finalProductList;
        System.out.println("Date from:" + dateFrom.getDate() + ", date to: " + dateTo.getDate());
        try {
            finalProductList = resultSetToList(db.getBoughtProducts(
                    dateFrom.getDate(),
                    dateTo.getDate(),
                    ids.toString())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pcs.firePropertyChange("ProductList", oldProductList, finalProductList);

    }

    private ArrayList<Product> resultSetToList(ResultSet rs) throws SQLException {
        ArrayList<Product> temp = new ArrayList<>();
        //rs.next();
        try {
            while (rs.next()) {
                String EAN = rs.getString(1),
                        name = ProductList.getInstance().get(EAN);
                int quantity = rs.getInt(2);
                System.out.println(EAN + " " + name + " " + quantity);
                temp.add(new Product(EAN, name, quantity));
            }
        } finally {
            rs.close();
        }
        System.out.println(temp);
        return temp;
    }

    public void setDatePickers(JXDatePicker from, JXDatePicker to) {
        System.out.println("Date pickers set");
        dateFrom = from;
        dateTo = to;
    }


}
