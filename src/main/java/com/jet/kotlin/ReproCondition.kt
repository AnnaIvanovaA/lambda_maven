package com.jet.kotlin

fun main(){
    // create a new deque which can have string elements
    var deque = ArrayDeque<String>();

    // add three elements to dequeu
    deque.add("one");
    deque.add("two");
    deque.add("three");
    println("The deque is " + deque);

    // check if the deque contains the element one
    println("\ndeque.contains('one'): " + deque.contains("one"));

    // check if the deque contains the element five
    println("\ndeque.contains('five'): " + deque.contains("five"));
}