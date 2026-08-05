package com.jet.breakpoints;

import java.util.List;

/**
 * Reproducer for IDEA-390175.
 *
 * Debug setup:
 * 1. Set a non-suspending logpoint on the marked line in processWithLogpoint().
 * 2. Log expression: "logpoint order: " + order
 * 3. Run this class in Debug.
 *
 * Check:
 * 1. In the debugger console, click a "println order:" output link first.
 *    The Stack Trace panel should open for the ordinary println navigation.
 * 2. Then click a "logpoint order:" output link.
 *    Expected: the editor focuses the logpoint line, and the previously opened
 *    Stack Trace panel is hidden because the logpoint navigation has no stored stack trace.
 */
public class LogpointNavigationHidesStackTrace {

    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("354b0096", List.of(new OrderItem("pen", 2), new OrderItem("paper", 4))),
                new Order("664e2b3c", List.of(new OrderItem("book", 1), new OrderItem("bookmark", 3))),
                new Order("4912e956", List.of(new OrderItem("notebook", 5)))
        );

        for (Order order : orders) {
            printFromApplication(order);
            processWithLogpoint(order);
        }

        System.out.println("done");
    }

    private static void printFromApplication(Order order) {
        printOrder(order);
    }

    private static void printOrder(Order order) {
        System.out.println("println order: " + order);
    }

    private static void processWithLogpoint(Order order) {
        int totalQuantity = order.items().stream() // LOGPOINT: "logpoint order: " + order
                .mapToInt(OrderItem::quantity)
                .sum();

        if (totalQuantity < 1) {
            throw new IllegalStateException("Empty order: " + order.id());
        }
    }

    private record Order(String id, List<OrderItem> items) {
    }

    private record OrderItem(String name, int quantity) {
    }
}
