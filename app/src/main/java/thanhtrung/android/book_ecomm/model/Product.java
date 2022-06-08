package thanhtrung.android.book_ecomm.model;

import java.util.Date;

public class Product {
    String id;
    String name;
    String price;
    String img;
    int imageUrl;
    int stock;
    String author;
    String publisher;
    String size;
    Date releaseDate;
    String translator;
    String cover;
    int numPage;
    int numSold;

    public Product(){ }

    public Product(String id, String name, String price, String img, int imageUrl, int quantity, String publisher, String size, Date releaseDate, String translator, String cover, int numPage, int numSold) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;
        this.imageUrl = imageUrl;
        this.stock = quantity;
        this.publisher = publisher;
        this.size = size;
        this.releaseDate = releaseDate;
        this.translator = translator;
        this.cover = cover;
        this.numPage = numPage;
        this.numSold = numSold;
    }

    public Product(String id, String name, String price, String img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;
    }

    public Product(String id, String name, String price, int imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQuantity() {
        return stock;
    }

    public void setQuantity(int quantity) {
        this.stock = quantity;
    }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getSize() { return size; }

    public void setSize(String size) { this.size = size; }

    public Date getReleaseDate() { return releaseDate; }

    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }

    public String getTranslator() { return translator; }

    public void setTranslator(String translator) { this.translator = translator; }

    public String getCover() { return cover; }

    public void setCover(String cover) { this.cover = cover; }

    public int getNumPage() { return numPage; }

    public void setNumPage(int numPage) { this.numPage = numPage; }

    public int getNumSold() { return numSold; }

    public void setNumSold(int numSold) { this.numSold = numSold; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }
}
