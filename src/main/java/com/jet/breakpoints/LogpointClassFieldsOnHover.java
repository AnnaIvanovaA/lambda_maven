package com.jet.breakpoints;

import java.util.List;

/**
 * Manual cases for adding logpoints from hover/actions on Java class fields.
 *
 * Java-specific points covered here:
 * TC-1  field declarations: static, instance, final, and volatile fields
 * TC-2  simple names in an instance method: instanceField, staticCounter
 * TC-3  qualified instance access: this.instanceField, other.instanceField
 * TC-4  qualified static access: LogpointClassFieldsOnHover.staticCounter
 * TC-5  a local variable shadows a field: status vs this.status
 * TC-6  a subclass field hides a superclass field: inheritedStatus vs super.inheritedStatus
 * TC-7  nested classes: static nested class fields vs inner class fields
 */
public class LogpointClassFieldsOnHover extends LogpointBaseStatusHolder {
    // TC-1: hover these declared field names and add a logpoint/watchpoint if the UI offers it.
    private static int staticCounter = 100;
    private static final String STATIC_FINAL_STATUS = "static-final";
    private volatile long volatileTicks = 1L;

    private String instanceField = "instance-alpha";
    private final List<String> finalInstanceField = List.of("red", "green", "blue");

    private String status = "field-status";
    private String inheritedStatus = "child-status";

    public static void main(String[] args) {
        LogpointClassFieldsOnHover first = new LogpointClassFieldsOnHover();
        LogpointClassFieldsOnHover second = new LogpointClassFieldsOnHover();

        first.exerciseSimpleAndQualifiedNames(second);
        first.exerciseShadowing("parameter-status");
        first.exerciseHiddenSuperclassField();
        first.exerciseNestedFieldAccess();
    }

    private void exerciseSimpleAndQualifiedNames(LogpointClassFieldsOnHover other) {
        // TC-2: hover/add logpoint on simple field names: instanceField, finalInstanceField, staticCounter.
        System.out.println(instanceField + " " + finalInstanceField.size() + " " + staticCounter);

        // TC-3: hover/add logpoint on qualified instance fields: this.instanceField and other.instanceField.
        System.out.println(this.instanceField + " / " + other.instanceField);

        // TC-4: hover/add logpoint on qualified static fields and final fields.
        System.out.println(LogpointClassFieldsOnHover.staticCounter + " " + STATIC_FINAL_STATUS);

        volatileTicks++;
        staticCounter++;
    }

    private void exerciseShadowing(String status) {
        String localReport = status.toUpperCase();

        // TC-5a: hover/add logpoint on status here. It is the method parameter, not the field.
        System.out.println("parameter/local status = " + status + ", localReport = " + localReport);

        // TC-5b: hover/add logpoint on this.status. It is the hidden instance field.
        System.out.println("field status = " + this.status);
    }

    private void exerciseHiddenSuperclassField() {
        // TC-6a: hover/add logpoint on inheritedStatus. It resolves to the child field.
        System.out.println("child inheritedStatus = " + inheritedStatus);

        // TC-6b: hover/add logpoint on super.inheritedStatus and the casted access.
        System.out.println("base inheritedStatus = " + super.inheritedStatus);
        System.out.println("base by cast = " + ((LogpointBaseStatusHolder) this).inheritedStatus);
    }

    private void exerciseNestedFieldAccess() {
        StaticNested nested = new StaticNested();
        InnerStatus inner = new InnerStatus();

        // TC-7a: hover/add logpoint on nested.nestedStatus and StaticNested.nestedCounter.
        System.out.println(nested.nestedStatus + " " + StaticNested.nestedCounter);

        // TC-7b: hover/add logpoint on inner.innerStatus and outer status via LogpointClassFieldsOnHover.this.status.
        System.out.println(inner.innerStatus + " " + inner.describeOuterStatus());
    }

    private static class StaticNested {
        private static int nestedCounter = 2;
        private String nestedStatus = "nested-status";
    }

    private class InnerStatus {
        private String innerStatus = "inner-status";

        private String describeOuterStatus() {
            return LogpointClassFieldsOnHover.this.status;
        }
    }

}

class LogpointBaseStatusHolder {
    protected String inheritedStatus = "base-status";
}
