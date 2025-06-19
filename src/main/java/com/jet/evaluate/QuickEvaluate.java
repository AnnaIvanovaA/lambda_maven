package com.jet.evaluate;

public class QuickEvaluate {

    public static void main(String[] args) {
        Supplier supplier = new Supplier("ABC123");
        String code = supplier.getSupplierCode(); // Set breakpoint here
        System.out.println("Supplier code: " + code);
    }

    static class Supplier {
        private final String supplierCode;

        public Supplier(String supplierCode) {
            this.supplierCode = supplierCode;
        }

        public String getSupplierCode() {
            return supplierCode + "qqqq";
        }
    }
}
