package com.jet.memoryView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemoryTabCheck {
        static class LeakObject {
            byte[] data = new byte[1024 * 10]; // 10KB payload
            String id;

            LeakObject(int i) { this.id = "Object-" + i; }
        }

        private static List<LeakObject> registry = new ArrayList<>();

        public static void main(String[] args) {
            System.out.println("--- Debugger Started ---");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("1: Add 1000 objects | 2: Clear registry | 3: Exit");
                String input = scanner.nextLine();

                if (input.equals("1")) {
                    for (int i = 0; i < 1000; i++) {
                        registry.add(new LeakObject(i));
                    }
                    System.out.println("Total objects in registry: " + registry.size());
                } else if (input.equals("2")) {
                    registry.clear();
                    System.out.println("Registry cleared.");
                } else if (input.equals("3")) {
                    break;
                }
                // Set a BP to the next line
                System.out.println("Action complete. Waiting for next command...");
            }
        }
}
