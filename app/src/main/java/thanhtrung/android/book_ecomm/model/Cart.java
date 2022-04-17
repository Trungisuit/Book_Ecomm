package thanhtrung.android.book_ecomm.model;

public class Cart {
    int ImageSrc;
    String NameProduct;
    String NameAuthor;
    String PriceProduct;
    String QuantityProduct;
    String TotalPrice;

    public Cart(int imageSrc, String nameProduct, String nameAuthor, String priceProduct, String quantityProduct, String totalPrice) {
        NameProduct = nameProduct;
        NameAuthor = nameAuthor;
        PriceProduct = priceProduct;
        QuantityProduct = quantityProduct;
        TotalPrice = totalPrice;
        ImageSrc = imageSrc;
    }

    public String getNameProduct() {
        return NameProduct;
    }

    public void setNameProduct(String nameProduct) {
        NameProduct = nameProduct;
    }

    public String getNameAuthor() {
        return NameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        NameAuthor = nameAuthor;
    }

    public String getPriceProduct() {
        return PriceProduct;
    }

    public void setPriceProduct(String priceProduct) {
        PriceProduct = priceProduct;
    }

    public String getQuantityProduct() {
        return QuantityProduct;
    }

    public void setQuantityProduct(String quantityProduct) {
        QuantityProduct = quantityProduct;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    public int getImageSrc() {
        return ImageSrc;
    }

    public void setImageSrc(int imageSrc) {
        ImageSrc = imageSrc;
    }
}
