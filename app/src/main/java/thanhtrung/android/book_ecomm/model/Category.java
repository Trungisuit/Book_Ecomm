package thanhtrung.android.book_ecomm.model;

import java.util.List;

public class Category {
    int id;
    String name;
    List<Product> products;

    public Category() {
    }

    public Category(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
