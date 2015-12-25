package Database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by tomek on 16.12.15.
 */
public class ProductListSynchronizer {
    Map<String, String> productMap;
    DBConnector db;
    FileWriter fw;

    public ProductListSynchronizer() throws IOException {
        productMap = new TreeMap<String, String>();
        db = DBConnector.getInstance();
        fw = new FileWriter(new File("ProductList.csv"), false);
    }

    public void synchronize() throws SQLException, IOException {
        ResultSet rs = db.getProducts();
        rs.next();
        while (!rs.isAfterLast()) {
            String EAN = rs.getString(1),
                    productName = rs.getString(2);
            fw.write(EAN + ";" + productName + System.getProperty("line.separator"));
            rs.next();
        }
        fw.close();
    }

}
