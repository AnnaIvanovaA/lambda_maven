package com.jet.intentions;

public class PrivateCustomer extends User {

    String address;

    public PrivateCustomer(int age, String str, String address) {
        super(age, str);
        this.address = address;
    }


    public void fooboo(){
        System.out.println(this.name);
    }

}
