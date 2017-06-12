package qst.com.myapplication;

/**
 * Created by lenovo on 2017/4/25.
 */

public class PeopleInfo {
    int ID;
    String Name;
    String Phone_number;
    String Address;
    String Email;

    public PeopleInfo(String name,String phone_number,String address,String email){
        this.Name=name;
        this.Address=address;
        this.Email=email;
        this.Phone_number=phone_number;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone_number() {
        return Phone_number;
    }

    public void setPhone_number(String phone_number) {
        Phone_number = phone_number;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }



}
