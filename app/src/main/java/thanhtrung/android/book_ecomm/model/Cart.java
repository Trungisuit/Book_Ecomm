package thanhtrung.android.book_ecomm.model;

public class Cart {
    String ProductID;
    String ProductName;
    String ImageSrc;
    Integer Img;
    String AuthorName;
    String ProductPrice;
    String ProductQuantity;
    String ProductStock;
    String ProductSold;
    String TotalPrice;

    public Cart() {
    }

    public Cart(String idProduct, String nameProduct, String imageSrc, String nameAuthor, String priceProduct, String quantityProduct, String totalPrice) {
        this.ProductID = idProduct;
        this.ProductName = nameProduct;
        ImageSrc = imageSrc;
        AuthorName = nameAuthor;
        ProductPrice = priceProduct;
        ProductQuantity = quantityProduct;
        TotalPrice = totalPrice;
    }

    public Cart(Integer img, String nameProduct, String nameAuthor, String priceProduct, String quantityProduct, String totalPrice) {
        this.ProductID = ProductID;
        ProductName = nameProduct;
        this.Img = img;
        AuthorName = nameAuthor;
        ProductPrice = priceProduct;
        ProductQuantity = quantityProduct;
        TotalPrice = totalPrice;
    }

    public String getProductID() { return ProductID; }

    public void setProductID(String productID) { ProductID = productID; }

    public String getProductName() { return ProductName; }

    public void setProductName(String productName) { ProductName = productName; }

    public String getAuthorName() { return AuthorName; }

    public void setAuthorName(String authorName) { AuthorName = authorName; }

    public String getProductPrice() { return ProductPrice; }

    public void setProductPrice(String productPrice) { ProductPrice = productPrice; }

    public String getProductQuantity() { return ProductQuantity; }

    public void setProductQuantity(String productQuantity) { ProductQuantity = productQuantity; }

    public String getProductStock() { return ProductStock; }

    public void setProductStock(String productStock) { ProductStock = productStock; }

    public String getProductSold() { return ProductSold; }

    public void setProductSold(String productStock) { ProductSold = productStock; }

    public String getTotalPrice() { return TotalPrice; }

    public void setTotalPrice(String totalPrice) { TotalPrice = totalPrice; }

    public String getImageSrc() { return ImageSrc; }

    public void setImageSrc(String imageSrc) { ImageSrc = imageSrc; }

    public Integer getImg() { return Img; }

    public void setImg(Integer img) { this.Img = img; }
}
