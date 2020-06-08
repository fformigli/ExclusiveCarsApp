package models;

import java.math.BigInteger;

public class Client {
    public BigInteger id;
    public String firstname, lastname;
    public String phone;

    public Client(BigInteger id, String firstname, String lastname, String phone) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
    }
}
