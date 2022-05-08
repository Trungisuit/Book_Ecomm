package thanhtrung.android.book_ecomm.model;

public class Book {
 private  int resourceid;
 private String name;

    public Book(int resourceid, String name) {
        this.resourceid = resourceid;
        this.name = name;
    }

    public int getResourceid() {
        return resourceid;
    }

    public void setResourceid(int resourceid) {
        this.resourceid = resourceid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
