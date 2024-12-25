package com.jet.variableView;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlExample {

    public static void main(String[] args) {
        String xmlString = "<person><name>Alice</name><age>25</age><city>Wonderland</city></person>";
        System.out.println("\nXML String:");
        System.out.println(xmlString);
        parseXml(xmlString);
    }
    // Parse XML String
    private static void parseXml(String xmlString) {
        try {
            System.out.println("\nParsed XML:");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document document = builder.parse(new StreamSource(new StringReader(xmlString)));
//            NodeList name = document.getElementsByTagName("name");
//            NodeList age = document.getElementsByTagName("age");
//            NodeList city = document.getElementsByTagName("city");
//
//            System.out.println("Name: " + name.item(0).getTextContent());
//            System.out.println("Age: " + age.item(0).getTextContent());
//            System.out.println("City: " + city.item(0).getTextContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
