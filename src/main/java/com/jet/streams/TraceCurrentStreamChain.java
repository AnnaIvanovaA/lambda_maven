package com.jet.streams;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TraceCurrentStreamChain {

    public static void main(String[] args) {
        TraceCurrentStreamChain examples = new TraceCurrentStreamChain();

        List<Order> orders = sampleOrders();
        List<InventorySnapshot> inventory = sampleInventory();
        List<SupportTicket> tickets = sampleTickets();
        List<CustomerVisit> visits = sampleVisits();

        System.out.println(examples.buildRevenueReport(orders));
        System.out.println(examples.findRestockCandidates(inventory));
        System.out.println(examples.collectEscalationCandidates(tickets));
        System.out.println(examples.findPriorityVisitors(visits));
    }

    public List<CategoryRevenue> buildRevenueReport(List<Order> orders) {
        LocalDate periodStart = LocalDate.of(2026, 3, 1);

        //BP here
        return orders.stream()
                .filter(order -> order.status() == OrderStatus.PAID || order.status() == OrderStatus.SHIPPED)
                .filter(order -> !order.createdAt().isBefore(periodStart))
                .flatMap(order -> order.items().stream())
                .collect(Collectors.groupingBy(
                        OrderItem::category,
                        Collectors.mapping(OrderItem::lineTotal, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))
                ))
                .entrySet()
                .stream()
                .map(entry -> new CategoryRevenue(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(CategoryRevenue::revenue).reversed())
                .toList(); // Breakpoint: trace a multi-stage stream with filter -> flatMap -> groupingBy -> stream -> map -> sorted
    }

    public List<RestockCandidate> findRestockCandidates(List<InventorySnapshot> inventory) {
        return inventory.stream()
                .filter(snapshot -> snapshot.available() <= snapshot.reorderPoint())
                .map(snapshot -> new RestockCandidate(
                        snapshot.sku(),
                        snapshot.available(),
                        snapshot.reorderPoint(),
                        snapshot.reorderPoint() + 15 - snapshot.available(),
                        snapshot.suppliers().stream()
                                .sorted()
                                .findFirst()
                                .orElse("unassigned")
                ))
                .sorted(Comparator.comparing(RestockCandidate::missingUnits).reversed())
                .toList(); // Breakpoint: trace filter -> map -> sorted
    }

    public Map<String, List<String>> collectEscalationCandidates(List<SupportTicket> tickets) {
        return tickets.stream()
                .filter(ticket -> ticket.status() != TicketStatus.CLOSED)
                .filter(ticket -> ticket.priority() >= 3)
                .flatMap(ticket -> ticket.labels().stream()
                        .map(label -> new TicketLabelPair(ticket, label)))
                .filter(pair -> pair.label().startsWith("dbg-") || pair.label().startsWith("prod-"))
                .collect(Collectors.groupingBy(
                        TicketLabelPair::label,
                        Collectors.mapping(pair -> pair.ticket().id(), Collectors.toList())
                )); // Breakpoint: trace nested flatMap and grouping collector
    }

    public List<String> findPriorityVisitors(List<CustomerVisit> visits) {
        return visits.stream()
                .filter(visit -> visit.purchaseCount() >= 3)
                .filter(visit -> visit.minutesOnSite() >= 15)
                .map(visit -> visit.customerName() + " (" + visit.purchaseCount() + ')')
                .sorted()
                .toList(); // Breakpoint: simple stream
    }

    private static List<Order> sampleOrders() {
        return List.of(
                new Order(
                        "ORD-101",
                        OrderStatus.PAID,
                        LocalDate.of(2026, 3, 2),
                        List.of(
                                new OrderItem("BK-101", "Books", new BigDecimal("39.90"), 1),
                                new OrderItem("EL-220", "Electronics", new BigDecimal("19.99"), 2)
                        )
                ),
                new Order(
                        "ORD-102",
                        OrderStatus.SHIPPED,
                        LocalDate.of(2026, 3, 4),
                        List.of(
                                new OrderItem("HM-330", "Home", new BigDecimal("12.50"), 4),
                                new OrderItem("BK-500", "Books", new BigDecimal("22.00"), 1)
                        )
                ),
                new Order(
                        "ORD-103",
                        OrderStatus.NEW,
                        LocalDate.of(2026, 3, 6),
                        List.of(
                                new OrderItem("EL-100", "Electronics", new BigDecimal("299.00"), 1)
                        )
                )
        );
    }

    private static List<InventorySnapshot> sampleInventory() {
        return List.of(
                new InventorySnapshot("BK-101", 4, 10, Set.of("book-hub", "central-warehouse")),
                new InventorySnapshot("EL-220", 18, 8, Set.of("tech-import")),
                new InventorySnapshot("HM-330", 3, 12, Set.of("home-supplier", "backup-home")),
                new InventorySnapshot("BK-500", 7, 7, Set.of("book-hub"))
        );
    }

    private static List<SupportTicket> sampleTickets() {
        return List.of(
                new SupportTicket("SUP-201", 4, TicketStatus.OPEN, List.of("dbg-streams", "prod-eu")),
                new SupportTicket("SUP-202", 2, TicketStatus.OPEN, List.of("ui", "minor")),
                new SupportTicket("SUP-203", 5, TicketStatus.IN_PROGRESS, List.of("dbg-memory", "customer")),
                new SupportTicket("SUP-204", 4, TicketStatus.CLOSED, List.of("prod-us", "resolved"))
        );
    }

    private static List<CustomerVisit> sampleVisits() {
        return List.of(
                new CustomerVisit("Nora Stone", 18, 4),
                new CustomerVisit("Ethan Cole", 7, 1),
                new CustomerVisit("Mia Foster", 31, 5),
                new CustomerVisit("Noah Reed", 12, 2),
                new CustomerVisit("Emma Price", 26, 3),
                new CustomerVisit("Liam Brooks", 14, 2),
                new CustomerVisit("Olivia Lane", 22, 4),
                new CustomerVisit("Lucas Hayes", 16, 3),
                new CustomerVisit("Ava Gray", 9, 1),
                new CustomerVisit("Elijah Ward", 44, 6),
                new CustomerVisit("Sophia Bell", 28, 3),
                new CustomerVisit("James Perry", 11, 2),
                new CustomerVisit("Isabella Ross", 35, 5),
                new CustomerVisit("Benjamin Cox", 19, 3),
                new CustomerVisit("Charlotte Kelly", 13, 2),
                new CustomerVisit("Henry Flores", 25, 4),
                new CustomerVisit("Amelia Cook", 17, 3),
                new CustomerVisit("Alexander Long", 8, 1),
                new CustomerVisit("Harper Diaz", 21, 3),
                new CustomerVisit("Michael Bennett", 29, 5)
        );
    }

    record Order(String id, OrderStatus status, LocalDate createdAt, List<OrderItem> items) {
    }

    record OrderItem(String sku, String category, BigDecimal unitPrice, int quantity) {
        BigDecimal lineTotal() {
            return unitPrice.multiply(BigDecimal.valueOf(quantity));
        }
    }

    record CategoryRevenue(String category, BigDecimal revenue) {
    }

    enum OrderStatus {
        NEW,
        PAID,
        SHIPPED
    }

    record InventorySnapshot(String sku, int available, int reorderPoint, Set<String> suppliers) {
    }

    record RestockCandidate(String sku, int available, int reorderPoint, int missingUnits, String preferredSupplier) {
    }

    record SupportTicket(String id, int priority, TicketStatus status, List<String> labels) {
    }

    record CustomerVisit(String customerName, int minutesOnSite, int purchaseCount) {
    }

    enum TicketStatus {
        OPEN,
        IN_PROGRESS,
        CLOSED
    }

    record TicketLabelPair(SupportTicket ticket, String label) {
    }
}
