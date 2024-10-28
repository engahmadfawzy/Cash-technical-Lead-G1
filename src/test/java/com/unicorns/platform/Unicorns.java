package com.unicorns.platform;


import com.unicorns.pages.*;

public class Unicorns {
    public Login login;
    public Register register;
    public Home home;
    public ProductPage productPage;
    public Checkout checkout;
    public Confirmation confirmation;

    public Unicorns() {
        login = new Login();
        register = new Register();
        home = new Home();
        productPage=new ProductPage();
        checkout=new Checkout();
        confirmation = new Confirmation();
    }
}
