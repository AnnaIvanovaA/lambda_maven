package com.jet.evaluate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class EvaluateCodeFragmentDialog {

    private final PricingEngine pricingEngine = new PricingEngine();
    private final CustomerDirectory customerDirectory = new CustomerDirectory();
    private final String environment = "staging";

    public static void main(String[] args) {
        EvaluateCodeFragmentDialog dialogCheck = new EvaluateCodeFragmentDialog();
        InvoiceSummary summary = dialogCheck.prepareInvoice(
                new CheckoutRequest(
                        "ORDER-2026-0007",
                        "CUST-42",
                        List.of(
                                new LineItem("SKU-BOOK", 2, new BigDecimal("12.50")),
                                new LineItem("SKU-MUG", 1, new BigDecimal("8.90")),
                                new LineItem("SKU-STICKER", 3, new BigDecimal("1.99"))
                        ),
                        List.of("WELCOME10", "SPRING"),
                        Map.of("channel", "web", "region", "nl")
                )
        );

        System.out.println(summary.invoiceId());
        System.out.println(summary.payableCents());
    }

    public InvoiceSummary prepareInvoice(CheckoutRequest request) {
        CustomerProfile customer = customerDirectory.findById(request.customerId())
                .orElseThrow(() -> new IllegalArgumentException("Unknown customer " + request.customerId()));

        List<InvoiceLine> invoiceLines = request.items().stream()
                .map(item -> new InvoiceLine(item.sku(), item.quantity(), toCents(item.unitPrice()), item.quantity() * toCents(item.unitPrice())))
                .sorted(Comparator.comparing(InvoiceLine::sku))
                .toList();

        PriceBreakdown breakdown = pricingEngine.calculate(customer, invoiceLines, request.coupons());
        Map<String, String> debugTags = new LinkedHashMap<>(request.metadata());
        debugTags.put("tier", customer.tier().name());
        debugTags.put("environment", environment);

        String privateNote = buildPrivateNote(customer, breakdown, debugTags);
        LocalDate followUpDate = customer.registeredAt().plusDays(customer.tier() == Tier.GOLD ? 7 : 14);
        int loyaltyPreview = invoiceLines.stream().mapToInt(InvoiceLine::quantity).sum() * customer.tier().multiplier();

        /*
         Breakpoint: main Evaluate Code Fragment entry point.
         Useful expressions:
         - customer.fullName()
         - invoiceLines.stream().mapToInt(InvoiceLine::totalCents).sum()
         - breakdown.payableCents() - breakdown.discountCents()
         - debugTags.get("channel")
         - buildPrivateNote(customer, breakdown, debugTags)
         - formatCents(loyaltyPreview * 125)
         - "string".toLowerCase(Locale.ROOT);
        */
        return new InvoiceSummary(
                "INV-" + UUID.nameUUIDFromBytes(request.orderId().getBytes()).toString().substring(0, 8),
                request.orderId(),
                customer.fullName(),
                breakdown.subtotalCents(),
                breakdown.discountCents(),
                breakdown.payableCents(),
                followUpDate,
                loyaltyPreview,
                privateNote,
                debugTags,
                invoiceLines
        );
    }

    private String buildPrivateNote(CustomerProfile customer, PriceBreakdown breakdown, Map<String, String> debugTags) {
        String couponDigest = debugTags.entrySet().stream()
                .map(entry -> entry.getKey() + '=' + entry.getValue())
                .sorted()
                .reduce((left, right) -> left + ", " + right)
                .orElse("no-tags");
        return customer.email() + " | " + formatCents(breakdown.payableCents()) + " | " + couponDigest;
    }

    private int toCents(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(100))
                .setScale(0, RoundingMode.HALF_UP)
                .intValueExact();
    }

    private String formatCents(int cents) {
        return BigDecimal.valueOf(cents, 2)
                .setScale(2, RoundingMode.HALF_UP)
                .toPlainString();
    }

    record CheckoutRequest(
            String orderId,
            String customerId,
            List<LineItem> items,
            List<String> coupons,
            Map<String, String> metadata
    ) {
    }

    record LineItem(String sku, int quantity, BigDecimal unitPrice) {
    }

    record InvoiceLine(String sku, int quantity, int unitPriceCents, int totalCents) {
    }

    record InvoiceSummary(
            String invoiceId,
            String orderId,
            String customerName,
            int subtotalCents,
            int discountCents,
            int payableCents,
            LocalDate followUpDate,
            int loyaltyPreview,
            String privateNote,
            Map<String, String> debugTags,
            List<InvoiceLine> invoiceLines
    ) {
    }

    record PriceBreakdown(int subtotalCents, int discountCents, int payableCents) {
    }

    enum Tier {
        REGULAR(1),
        GOLD(3);

        private final int multiplier;

        Tier(int multiplier) {
            this.multiplier = multiplier;
        }

        public int multiplier() {
            return multiplier;
        }
    }

    static class PricingEngine {
        PriceBreakdown calculate(CustomerProfile customer, List<InvoiceLine> invoiceLines, List<String> coupons) {
            int subtotal = invoiceLines.stream().mapToInt(InvoiceLine::totalCents).sum();
            int couponDiscount = coupons.stream()
                    .map(String::trim)
                    .map(String::toUpperCase)
                    .mapToInt(code -> switch (code) {
                        case "WELCOME10" -> 1000;
                        case "SPRING" -> 250;
                        default -> 0;
                    })
                    .sum();
            int tierDiscount = customer.tier() == Tier.GOLD ? (int) Math.round(subtotal * 0.15) : 0;
            int totalDiscount = Math.min(subtotal, couponDiscount + tierDiscount);
            return new PriceBreakdown(subtotal, totalDiscount, subtotal - totalDiscount);
        }
    }

    static class CustomerDirectory {
        private final List<CustomerProfile> customers = new ArrayList<>(List.of(
                new CustomerProfile("CUST-42", "Nora", "Stone", "nora.stone@example.com", Tier.GOLD, LocalDate.of(2023, 11, 14)),
                new CustomerProfile("CUST-77", "Ethan", "Cole", "ethan.cole@example.com", Tier.REGULAR, LocalDate.of(2024, 5, 2))
        ));

        Optional<CustomerProfile> findById(String customerId) {
            return customers.stream()
                    .filter(customer -> customer.id().equals(customerId))
                    .findFirst();
        }
    }

    record CustomerProfile(
            String id,
            String firstName,
            String lastName,
            String email,
            Tier tier,
            LocalDate registeredAt
    ) {
        String fullName() {
            return firstName + " " + lastName;
        }
    }
}
