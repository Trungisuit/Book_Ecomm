package thanhtrung.android.book_ecomm.model;

import java.util.List;

public class Category {
    int CategoryID;
    String CategoryName;
    List<Product> ListProduct;


    public Category() {
    }

    public Category(String name, List<Product> products) {
        this.CategoryName = name;
        this.ListProduct = products;
    }

    public Category(int categoryID, String categoryName, List<Product> listProduct) {
        CategoryID = categoryID;
        CategoryName = categoryName;
        ListProduct = listProduct;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public List<Product> getListProduct() {
        return ListProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        ListProduct = listProduct;
    }
}
