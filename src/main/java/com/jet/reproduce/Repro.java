package com.jet.reproduce;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Repro {


        public static void main(String[] args) {
            String z = "1234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345123451234512345";
            String[] x = new String[]{z,z,z,z,z,z,z,z,z,z,z,z,z,z,z,z,z,z,z,z,z,z};
            //StreamSupport.stream(Spliterators.spliteratorUnknownSize(x[0], Spliterator.ORDERED), false).collect(Collectors.joining(";"));
            System.out.println(x);
            String htmlStr = new Repro().getBlockOfHtml();
            final String str = """
                    dfsfsfd {
                    init { -> sdfs}
                    init { -> sdfs}
                    init { -> sdfs}
                    init { -> sdfs}
                    dsfsfsf""";
            System.out.println(htmlStr);
            System.out.println(str);
        }

    public String getBlockOfHtml() {
        return """
            <html>

                <body>
                    <span>example text</span>
                    <span>example text</span>
                    <span>example text</span>
                    <span>example text</span>
                    <span>example text</span>
                    <span>example text</span>
                </body>
            </html>""";
    }



}


