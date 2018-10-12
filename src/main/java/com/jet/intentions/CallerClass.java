package com.jet.intentions;

public class CallerClass {

    public static void main(String[] args) {

        //stop in the current object
        User user = new User(25, "John");
        for (int i = 0; i < 3; i++) {
            System.out.println(i + " " + user.getName());
        }

        User user2 = new User(30, "Kate");
        for (int i = 0; i <3; i++) {
            System.out.println(i + " " + user2.getName());
        }

        User user3 = new User(13, "Mike");
        for (int i = 0; i < 3; i++) {
            System.out.println(i + " " + user3.getName());
        }

        PrivateCustomer customer = new PrivateCustomer(55, "MR. Smith", "pivet drive");
        System.out.println(customer.getName());

        PrivateCustomer customer2 = new PrivateCustomer(554, "MR. Smitheeee", "piveteeeeeee drive");

        // stop in the current class -- set BP on toString() in Object class
        User user4 = new User(25, "John");
        User user7 = user4;

        if (user7.equals (user4)){
            System.out.println("equals!");
        }

        user4.toString();
        for (int i = 0; i < 3; i++) {
            System.out.println(user4.toString());
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(customer.toString());
        }

        User user5 = new User(30, "Kate");
        for (int i = 0; i <3; i++) {
            System.out.println(user5.toString());
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(customer2.toString());
        }
        User user6 = new User(13, "Mike");
        for (int i = 0; i < 3; i++) {
            System.out.println(user6.toString());
        }



        // do not stop in the current object (not static method)
        foo();
    }

    public static void foo(){
        User user = new User(100, "Tom");
        user.boo();
    }
}
