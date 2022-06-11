package thanhtrung.android.book_ecomm.model;

import java.util.Date;

public class Product {
    String ProductID;
    String ProductName;
    String ProductPrice;
    String Img;
    int imageUrl;
    int ProductStock;
    String AuthorName;
    String ProductPublisher;
    String ProductSize;
    String ProductDate;
    String ProductTranslator;
    String ProductCover;
    int ProductNumPage;
    int ProductSold;

    public Product(){ }

    public Product(String productID, String productName, String productPrice, String img, int productStock, String authorName, String productPublisher, String productSize, String productDate, String productTranslator, String productCover, int productNumPage, int productSold) {
        ProductID = productID;
        ProductName = productName;
        ProductPrice = productPrice;
        Img = img;
        ProductStock = productStock;
        AuthorName = authorName;
        ProductPublisher = productPublisher;
        ProductSize = productSize;
        ProductDate = productDate;
        ProductTranslator = productTranslator;
        ProductCover = productCover;
        ProductNumPage = productNumPage;
        ProductSold = productSold;
    }

    public Product(String id, String name, String price, String img) {
        this.ProductID = id;
        this.ProductName = name;
        this.ProductPrice = price;
        this.Img = img;
    }

    public Product(String id, String name, String price, int imageUrl) {
        this.ProductID = id;
        this.ProductName = name;
        this.ProductPrice = price;
        this.imageUrl = imageUrl;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getProductStock() {
        return ProductStock;
    }

    public void setProductStock(int productStock) {
        ProductStock = productStock;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }

    public String getProductPublisher() {
        return ProductPublisher;
    }

    public void setProductPublisher(String productPublisher) {
        ProductPublisher = productPublisher;
    }

    public String getProductSize() {
        return ProductSize;
    }

    public void setProductSize(String productSize) {
        ProductSize = productSize;
    }

    public String getProductDate() {
        return ProductDate;
    }

    public void setProductDate(String productDate) {
        ProductDate = productDate;
    }

    public String getProductTranslator() {
        return ProductTranslator;
    }

    public void setProductTranslator(String productTranslator) {
        ProductTranslator = productTranslator;
    }

    public String getProductCover() {
        return ProductCover;
    }

    public void setProductCover(String productCover) {
        ProductCover = productCover;
    }

    public int getProductNumPage() {
        return ProductNumPage;
    }

    public void setProductNumPage(int productNumPage) {
        ProductNumPage = productNumPage;
    }

    public int getProductSold() {
        return ProductSold;
    }

    public void setProductSold(int productSold) {
        ProductSold = productSold;
    }
}
