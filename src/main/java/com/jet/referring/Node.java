package com.jet.referring;

import lombok.Setter;

public class Node {
    private String data;
    @Setter
    private Node next;

    public Node(String data) {
        this.data = data;
    }

}
