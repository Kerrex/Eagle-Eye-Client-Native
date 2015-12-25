package Logic;

/**
 * Created by tomek on 15.12.15.
 */
public class Product {
    //private long id;
    private String EAN;
    private String name;
    private int quantity;
    private Object[] array;

    public Product(String EAN, String name) {
        //this.id = id;
        this.EAN = EAN;
        this.name = name;
        array = new Object[]{EAN, name, quantity};
    }

    public Product(String EAN, String name, int quantity) {
        this.EAN = EAN;
        this.name = name;
        this.quantity = quantity;
        array = new Object[]{EAN, name, quantity};
    }

    public String getEAN() {
        return EAN;
    }

    public void setEAN(String EAN) {
        this.EAN = EAN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Object[] toArray() {
        return array;
    }
}

