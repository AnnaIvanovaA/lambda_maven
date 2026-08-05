package com.jet.breakpoints;

/**
 * Manual verification for IDEA-389740.
 *
 * <ol>
 *   <li>Add a Java exception breakpoint for {@link VerificationException}.</li>
 *   <li>Start this class under the debugger.</li>
 *   <li>While the project is indexing, open or close the Breakpoints dialog several times.</li>
 * </ol>
 *
 * The IDE must remain responsive while it resolves the exception breakpoint's source position.
 * Resume each breakpoint hit to repeat the check.
 */
public final class ExceptionBreakpointReadActionFreeze {
    private ExceptionBreakpointReadActionFreeze() {
    }

    public static void main(String[] args) throws InterruptedException {
        for (int attempt = 1; attempt <= 20; attempt++) {
            try {
                throw new VerificationException("Attempt " + attempt);
            }
            catch (VerificationException ignored) {
                Thread.sleep(250);
            }
        }
    }

    public static final class VerificationException extends RuntimeException {
        public VerificationException(String message) {
            super(message);
        }
    }
}
