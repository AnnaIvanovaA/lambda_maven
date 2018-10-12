package com.jet.SmartStepInto;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class ForceStepInto {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Key", "Value");
        map.put("Key1", "Value1");
        map.put("Key2", "Value2");
        map.put("Key3", "Value3");
        map.put("Key4", "Value4");

        Iterator<String> itr = map.keySet().iterator();    // Debug this line

        while (itr.hasNext()) {
            System.out.println(itr.next());
        }


        Map<String, String> reversedMap = new TreeMap<String, String>(map);

        for (Map.Entry entry : reversedMap.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }

        Iterator<Map.Entry<String, String>> entries = reversedMap.entrySet().iterator();

        while (entries.hasNext())
        {
            String text = entries.next().getKey();
            System.out.println(text);
        }
    }
}
