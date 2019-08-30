package com.jet.breakpoints;


public class User {  //<BP> default constructor

    String name;
    int age;

        public String getName() {
            return name;
        }




        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
        return age;
    }


        /**
         * return an age
         * @param age
         */
    public void setAge(int age) {
        this.age = age;
    }


}
