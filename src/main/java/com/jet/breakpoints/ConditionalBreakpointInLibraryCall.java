package com.jet.breakpoints;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Manual debugger playground for conditional breakpoints in user code that is executed from JDK library calls.
 * <p>
 * Suggested checks:
 * 1. tc1_listForEach: set a breakpoint on the marked line, condition: name.contains("target")
 * 2. tc2_mapComputeIfAbsent: set a breakpoint on the marked line, condition: missingKey.equals("target-key")
 * 3. tc3_streamFilter: set a breakpoint on the marked line, condition: order.id().startsWith("VIP")
 */
public class ConditionalBreakpointInLibraryCall {

    public static void main(String[] args) {
        tc1_listForEach();
        tc2_mapComputeIfAbsent();
        tc3_streamFilter();
    }

    private static void tc1_listForEach() {
        List<String> names = List.of("alpha", "beta", "target-beta", "gamma");
        List<String> visited = new ArrayList<>();
        names.forEach(name -> {
            visited.add(name); // ← SET BREAKPOINT HERE | condition: name.contains("target")
            System.out.println("Visited " + name);
        });System.out.println("TC-1 done, visited=" + visited);
    }

    private static void tc2_mapComputeIfAbsent() {
        Map<String, Integer> cache = new LinkedHashMap<>();
        List<String> keys = List.of("alpha", "beta", "beta", "target-key");

        for (String key : keys) {
            int value = cache.computeIfAbsent(key, missingKey -> {
                int computed = missingKey.length() * 10; // ← SET BREAKPOINT HERE | condition: missingKey.equals("target-key")
                System.out.println("Computed " + missingKey + " -> " + computed);
                return computed;
            });
            System.out.println(key + " => " + value);
        }
        System.out.println("TC-2 done, cache=" + cache);
    }

    private static void tc3_streamFilter() {
        List<Order> orders = List.of(
                new Order("A-100", 2, false),
                new Order("B-200", 5, true),
                new Order("C-300", 7, false),
                new Order("VIP-900", 12, true)
        );
        List<Order> flagged = orders.stream()
                .filter(order -> {
                    boolean shouldKeep = order.expedited() && order.quantity() >= 10; // ← SET BREAKPOINT HERE | condition: order.id().startsWith("VIP")
                    return shouldKeep;
                })
                .toList();

        System.out.println("TC-3 done, flagged=" + flagged);
    }

    private record Order(String id, int quantity, boolean expedited) {
    }
}
