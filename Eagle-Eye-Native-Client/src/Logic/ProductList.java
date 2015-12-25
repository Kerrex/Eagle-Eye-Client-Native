package Logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by tomek on 16.12.15.
 */
public class ProductList {
    private static ProductList ourInstance = new ProductList();
    private Map<String, String> productMap;

    private ProductList() {
        productMap = new HashMap();
        System.out.println("ProductList created");
        try {
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized static ProductList getInstance() {
        return ourInstance;
    }

    public synchronized String get(String EAN) {
        return productMap.get(EAN);
    }

    private synchronized void load() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("ProductList.csv"));
        String temp;
        while ((temp = br.readLine()) != null) {
            String[] separated = temp.split(";");
            productMap.put(separated[0], separated[1]);
        }
        br.close();
    }
}
