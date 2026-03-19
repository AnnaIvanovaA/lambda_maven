package com.jet.streams;

import java.io.IOException;
import java.util.List;

public class FileEtlStreamTrace {

    public static void main(String[] args) throws IOException {
        Workflow workflow = new Workflow();

        List<String> reader = List.of(
                "aaaa",
                "bbbbb",
                "cccc",
                "dddd",
                "error-row",
                "etl-ready",
                "raw-input"
        );

        List<UserRecord> lines = reader.stream()
                .filter(line -> line.length() > 3)
                .filter(line -> !line.startsWith("a"))
                .map(workflow::process)
                .toList(); // Breakpoint

        System.out.println(lines);
    }

    static class Workflow {
        public UserRecord process(String id) {
            return new UserRecord(id, id.toUpperCase(), id.length());
        }
    }

    record UserRecord(String rawId, String normalizedId, int length) {
    }
}
