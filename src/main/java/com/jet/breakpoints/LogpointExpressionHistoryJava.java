package com.jet.breakpoints;

import java.util.List;
import java.util.Locale;

/**
 * Manual cases for IDEA-387231: expression history in the logpoint editor.
 *
 * TC-1: Create a logpoint on JAVA-HISTORY-1 and submit several expressions.
 *       Use Alt+Up/Alt+Down on Windows/Linux or Option+Up/Option+Down on macOS.
 *       Expected: previous and next expressions are restored in order.
 *
 * TC-2: Close and reopen the same logpoint editor in the same IDE session.
 *       Expected: history is still available.
 *
 * TC-3: Create a logpoint on JAVA-HISTORY-2 after filling history on JAVA-HISTORY-1.
 *       Expected: the same history is available, not a per-line history.
 *
 * TC-4: Submit the same expression twice in a row.
 *       Expected: the consecutive duplicate is stored only once.
 */
public class LogpointExpressionHistoryJava {
    private final List<Order> orders = List.of(
            new Order("J-100", "Ada", 42.50, 0.19, List.of("debugger", "editor")),
            new Order("J-101", "Grace", 15.75, 0.10, List.of("history")),
            new Order("J-102", "Katherine", 99.90, 0.05, List.of("logpoint", "navigation", "dedupe"))
    );

    public static void main(String[] args) {
        new LogpointExpressionHistoryJava().runHistoryCases();
    }

    private void runHistoryCases() {
        for (int step = 0; step < orders.size(); step++) {
            Order order = orders.get(step);

            // JAVA-HISTORY-1: set a non-suspending logpoint on the next line.
            // Expression history candidates:
            //   "java id=" + order.id
            //   "java customer=" + order.customer + ", total=" + order.totalWithTax()
            //   LogpointExpressionHistoryJava.describe(order, step, "first-line")
            //   "duplicate java " + order.id
            //   "duplicate java " + order.id
            System.out.println("JAVA-HISTORY-1 " + order.summary());
        }

        for (Order order : orders) {
            String normalizedCustomer = order.customer.toLowerCase(Locale.ROOT);

            // JAVA-HISTORY-2: set another logpoint here after using JAVA-HISTORY-1.
            // Expected: Alt/Option+Up shows expressions entered on JAVA-HISTORY-1.
            // Extra expressions for this line:
            //   "java normalized=" + normalizedCustomer
            //   describe(order, normalizedCustomer.length(), "second-line")
            System.out.println("JAVA-HISTORY-2 " + normalizedCustomer + " " + order.items.size());
        }
    }

    private static String describe(Order order, int index, String source) {
        return source + " #" + index + " " + order.id + " " + order.customer + " " + order.totalWithTax();
    }

    private static class Order {
        private final String id;
        private final String customer;
        private final double subtotal;
        private final double taxRate;
        private final List<String> items;

        private Order(String id, String customer, double subtotal, double taxRate, List<String> items) {
            this.id = id;
            this.customer = customer;
            this.subtotal = subtotal;
            this.taxRate = taxRate;
            this.items = items;
        }

        private double totalWithTax() {
            return subtotal + subtotal * taxRate;
        }

        private String summary() {
            return id + ":" + customer + ":" + items.size();
        }
    }
}
