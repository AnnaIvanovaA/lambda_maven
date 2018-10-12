package com.jet.intentions;

public class User {

    int age;
    String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(int age, String str) {
        this.age = age;
        this.name = str;
    }

    public User(String str) {
        this.name = str;
    }

    public User(int age) {
        this.age = age;
    }

    public void boo(){
        PrivateCustomer privateCustomer = new PrivateCustomer(100, "Tom", "address");
        privateCustomer.fooboo();

        PrivateCustomer privateCustomer2 = new PrivateCustomer(99, "Jane", "address");
        privateCustomer2.fooboo();

        PrivateCustomer privateCustomer3 = new PrivateCustomer(98, "Bill", "address");
        privateCustomer3.fooboo();
    }


}
