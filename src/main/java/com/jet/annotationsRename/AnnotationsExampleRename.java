package com.jet.annotationsRename;

public class AnnotationsExampleRename {



//    String str;
//    String myString = (@NotNull String) str;

    @Deprecated
    public void deprecatedMethod(){

    }

    @SuppressWarnings("unchecked")
    public void method(){

    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
