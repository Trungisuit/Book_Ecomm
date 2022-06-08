package thanhtrung.android.book_ecomm.model;

public class User {
    String ID, Name, Email, Phone, Password, Address;

    public User() {
    }

    public User(String id, String name, String eMail, String phoneNum, String address) {
        this.ID = id;
        this.Name = name;
        this.Email = eMail;
        this.Phone = phoneNum;
        this.Address = address;


    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}

