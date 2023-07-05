//package com.jet.dfa;
//
//import org.apache.http.entity.ContentType;
//
//import java.nio.charset.Charset;
//
//public class HttpCharsetCheck {
//    static void getResponseCharset(ContentType normalizedContent) {
//        ContentType contentType = normalizedContent;
//        if (contentType.getCharset() == null) { // breakpoint
//            System.out.println();
//        }
//    }
//
//    public static void main(String[] args) {
//        getResponseCharset(ContentType.create("text/plain", (Charset) null));
//    }
//}
