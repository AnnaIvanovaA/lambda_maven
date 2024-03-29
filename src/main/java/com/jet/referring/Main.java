package com.jet.referring;

public class Main {

    public static void main(String[] args) {
        //check node2 - node1 reference; node 3 - nodea-node1 references
        Node node1 = new Node("Node 1");
        Node node2 = new Node("Node 2");
        Node node3 = new Node("Node 3");

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node2);

        //show referring objects for name, hair fields
        Man person1 = new Man("brown", 182, 78000, "Peter");
        Man person2 = person1;
        Man person3 = person2;
        Man person4 = new Man("white", 190, 19000, "Bob");
        Man person5 = person4;

        person5 = person1;
        System.out.println("Person 1: " + person1.getName() + ", High: " + person1.getHeight());
        System.out.println("Person 2: " + person2.getName() + ", High: " + person2.getHeight());
        System.out.println("Person 3: " + person3.getName() + ", High: " + person3.getHeight());
        System.out.println("Person 4: " + person4.getName() + ", High: " + person4.getHeight());
        System.out.println("Person 5: " + person5.getName() + ", High: " + person5.getHeight());

    }
}
