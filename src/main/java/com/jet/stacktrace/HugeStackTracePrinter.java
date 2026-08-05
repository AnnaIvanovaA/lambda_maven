package com.jet.stacktrace;

import java.io.PrintStream;

public class HugeStackTracePrinter {
    private static final int DEFAULT_FRAMES = 10_000;
    private static final int DEFAULT_CAUSES = 5;
    private static final int DEFAULT_SUPPRESSED = 10;

    public static void main(String[] args) {
        int frames = readIntArg(args, 0, DEFAULT_FRAMES);
        int causes = readIntArg(args, 1, DEFAULT_CAUSES);
        int suppressed = readIntArg(args, 2, DEFAULT_SUPPRESSED);
        PrintStream output = args.length > 3 && "out".equalsIgnoreCase(args[3])
                ? System.out
                : System.err;

        Throwable hugeTrace = createHugeStackTrace(frames, causes, suppressed);
        output.printf(
                "Printing huge stack trace: %,d root frames, %,d causes, %,d suppressed exceptions%n",
                frames,
                causes,
                suppressed
        );
        hugeTrace.printStackTrace(output);
        output.flush();
    }

    private static Throwable createHugeStackTrace(int frames, int causes, int suppressed) {
        RuntimeException root = new RuntimeException("Root exception with a synthetic huge stack trace");
        root.setStackTrace(createFrames("RootCall", frames));

        Throwable cause = null;
        for (int i = causes; i >= 1; i--) {
            RuntimeException nextCause = new RuntimeException("Nested cause #" + i, cause);
            nextCause.setStackTrace(createFrames("Cause" + i, Math.max(1, frames / 2)));
            cause = nextCause;
        }
        if (cause != null) {
            root.initCause(cause);
        }

        for (int i = 1; i <= suppressed; i++) {
            RuntimeException suppressedException = new RuntimeException("Suppressed exception #" + i);
            suppressedException.setStackTrace(createFrames("Suppressed" + i, Math.max(1, frames / 4)));
            root.addSuppressed(suppressedException);
        }

        return root;
    }

    private static StackTraceElement[] createFrames(String prefix, int count) {
        StackTraceElement[] frames = new StackTraceElement[count];
        for (int i = 0; i < count; i++) {
            frames[i] = new StackTraceElement(
                    "com.jet.stacktrace.synthetic." + prefix + "Class" + (i % 100),
                    "methodWithLongNameForConsoleWrapping" + i,
                    prefix + "File" + (i % 50) + ".java",
                    i + 1
            );
        }
        return frames;
    }

    private static int readIntArg(String[] args, int index, int defaultValue) {
        if (args.length <= index) {
            return defaultValue;
        }

        try {
            return Math.max(0, Integer.parseInt(args[index]));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
