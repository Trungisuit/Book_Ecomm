package thanhtrung.android.book_ecomm.model;

public class User {
    String fullName, eMail, phoneNum, password;

    public User(String fullName, String eMail, String phoneNum, String password) {
        this.fullName = fullName;
        this.eMail = eMail;
        this.phoneNum = phoneNum;
        this.password = password;
    }

    public User(){}

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
