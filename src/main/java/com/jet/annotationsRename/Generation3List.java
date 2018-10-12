package com.jet.annotationsRename;

@ClassPreamble(
    author = "John",
    date = "3/11/2011",
    reviewers = {"Alice", "Bob", "Cindy"}

)

public class Generation3List extends Generation2List {

    public Generation3List(String str, int i) {
        super(str, i);
    }

}
