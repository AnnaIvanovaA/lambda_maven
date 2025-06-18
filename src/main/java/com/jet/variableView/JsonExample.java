//package com.jet.variableView;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//public class JsonExample {
//    public static void main(String[] args) {
//        // JSON String
//        String jsonString = "{ \"name\": \"Alice\", \"age\": 25, \"city\": \"Wonderland\" }";
//        System.out.println("JSON String:");
//String str;
//System.out.println(jsonString);
//        parseJson(jsonString);
//        System.out.println();
//
//        String jArray = createJsonWith50Records();
//        System.out.println();
//    }
//
//    // Method to generate a JSON string with 50 records
//    private static String createJsonWith50Records() {
//        JSONArray jsonArray = new JSONArray();
//        for (int i = 1; i <= 50; i++) {
//            JSONObject person = new JSONObject();
//            person.put("name", "Person" + i);
//            person.put("age", 20 + (i % 30)); // Age between 20 and 49
//            person.put("city", "City" + ((i % 10) + 1)); // City1 to City10
//            jsonArray.put(person);
//        }
//        return jsonArray.toString();
//    }
//
//    // Parse JSON String
//    private static void parseJson(String jsonString) {
//        System.out.println("\nParsed JSON:");
//        JSONObject jsonObject = new JSONObject(jsonString);
//        System.out.println("Name: " + jsonObject.getString("name"));
//        System.out.println("Age: " + jsonObject.getInt("age"));
//        System.out.println("City: " + jsonObject.getString("city"));
//
//
//    }
//}
