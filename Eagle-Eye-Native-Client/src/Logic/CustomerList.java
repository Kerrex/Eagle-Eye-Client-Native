package Logic;

import Database.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by tomek on 16.12.15.
 */
public class CustomerList {
    private static CustomerList ourInstance = new CustomerList();
    private List<Customer> customerList;

    private CustomerList() {
        customerList = new ArrayList<Customer>();
    }

    public static CustomerList getInstance() {
        return ourInstance;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void load() throws SQLException {
        DBConnector db = DBConnector.getInstance();
        ResultSet rs = db.getCustomers();
        rs.next();
        while (!rs.isAfterLast()) {
            long id = rs.getLong(1);
            String name = rs.getString(2);
            customerList.add(new Customer(id, name));
            rs.next();
        }
        sortCustomerList();

    }

    private void sortCustomerList() {
        Collections.sort(customerList, (Customer p1, Customer p2) -> p1.getName().compareTo(p2.getName()));
    }

}
