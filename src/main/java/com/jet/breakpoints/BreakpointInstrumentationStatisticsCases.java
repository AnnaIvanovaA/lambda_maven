package com.jet.breakpoints;

import java.util.Arrays;
import java.util.Locale;

/**
 * Manual debuggee for checking the detail reported when breakpoint instrumentation is skipped or fails.
 *
 * <p>The detail is produced by the IDE, not by the debuggee. Run this class under the debugger with
 * {@code debugger.breakpoint.instrumentation} enabled, configure the breakpoint described by the selected
 * case, and inspect the debugger instrumentation event/log. Use {@code --list} to print the complete catalog.
 */
public final class BreakpointInstrumentationStatisticsCases {
  private BreakpointInstrumentationStatisticsCases() {}

  enum Kind {
    SOURCE_REPRODUCIBLE,
    DEBUGGER_CONFIGURATION,
    IDE_INTERNAL_ONLY
  }

  enum Detail {
    NO_ADDITIONAL_DETAIL,
    BREAKPOINT_CONTAINS_MODIFIERS,
    BREAKPOINT_BEFORE_LAMBDA,
    BREAKPOINT_AT_RETURN,
    BREAKPOINT_AT_LAMBDA,
    BREAKPOINT_INSIDE_KOTLIN_INLINE_FUNCTION,
    BREAKPOINT_CHANGES_LOCAL_VARIABLES,
    NO_FALLBACK,
    NO_CONDITION_TEXT,
    NO_SOURCE_POSITION,
    NO_CONTEXT_ELEMENT,
    NO_CONTEXT_FROM_SUPPORTER,
    JAVA_COMPILING_EVALUATOR_UNAVAILABLE,
    KOTLIN_CODE_FRAGMENT_NOT_CREATED,
    INCORRECT_USER_CODE,
    INTERNAL_COMPILER_ERROR,
    AGENT_FAILED_TO_FINISH_INSTRUMENTATION,
    INSTRUMENTED_USER_CLASS_BECOME_NOT_VALID,
    INCORRECT_INSTRUMENTATION_IN_RUNTIME,
    CANNOT_INSTALL_USER_CODE_HIT_BREAKPOINT,
    NO_FOUND_SINGLE_CHILD_IN_FRAGMENT_PSI,
    RESULT_VALUE_IS_MISSING_IN_FRAGMENT_PSI,
    RESULT_VAR_NAME_IS_WRONG_IN_FRAGMENT_PSI,
    RESULT_INIT_IS_NOT_METHOD_CALL,
    WRONG_INVOCATION_METHOD_NAME,
    MAIN_FRAGMENT_CLASS_OBJECT_MISSING,
    CLASS_BINARY_CODE_MISSING,
    FRAGMENT_CLASS_CTOR_ARGS_MISSING,
    UNEXPECTED_FRAGMENT_CALL_SHAPE,
    NON_REFERENCE_ARGUMENT,
    MISSING_ARGUMENT_REFERENCE_NAME,
    LOADING_NO_COMPILED_DATA,
    LOADING_NO_FRAGMENT_ENTRY_CLASS,
    LOADING_NO_FRAGMENT_ENTRY_METHOD
  }

  record Case(Kind kind, String setup) {}

  public static void main(String[] args) {
    validateCatalog();
    if (args.length == 0 || "--list".equals(args[0])) {
      printCatalog();
      return;
    }

    Detail detail = Detail.valueOf(args[0].toUpperCase(Locale.ROOT));
    Case testCase = describe(detail);
    System.out.printf("%s [%s]%n%s%n", detail, testCase.kind(), testCase.setup());
    if (testCase.kind() == Kind.IDE_INTERNAL_ONLY) {
      System.out.println("This detail cannot be forced reliably by ordinary debuggee source.");
      return;
    }
    runSourceScenario(detail);
  }

  private static void validateCatalog() {
    for (Detail detail : Detail.values()) {
      Case testCase = describe(detail);
      if (testCase.setup().isBlank()) {
        throw new AssertionError("Missing setup for " + detail);
      }
    }
  }

  private static void printCatalog() {
    Arrays.stream(Detail.values()).forEach(detail -> {
      Case testCase = describe(detail);
      System.out.printf("%-48s %-24s %s%n", detail, testCase.kind(), testCase.setup());
    });
  }

  private static Case describe(Detail detail) {
    return switch (detail) {
      case BREAKPOINT_CONTAINS_MODIFIERS -> configurable(
        "On the marked statement set condition 'i == 2' and also enable a class filter, log message, or log stack trace.");
      case BREAKPOINT_BEFORE_LAMBDA -> source("Set a conditional breakpoint on the marked statement immediately before the lambda.");
      case BREAKPOINT_AT_RETURN -> source("Set a conditional breakpoint on the marked return statement.");
      case BREAKPOINT_AT_LAMBDA -> source("Set a conditional breakpoint on the marked statement in the lambda body.");
      case BREAKPOINT_INSIDE_KOTLIN_INLINE_FUNCTION -> source(
        "Run InstrumentationStatisticsKotlinKt and set the documented breakpoint inside its inline function.");
      case BREAKPOINT_CHANGES_LOCAL_VARIABLES -> configurable(
        "On the marked statement use a condition that mutates a local, for example '++i > 2'.");
      case NO_CONDITION_TEXT -> configurable("Enable a condition but leave its expression empty, if the UI permits this state.");
      case JAVA_COMPILING_EVALUATOR_UNAVAILABLE -> configurable(
        "Use a Java condition requiring compiled evaluation while the Java compiling evaluator is unavailable.");
      case KOTLIN_CODE_FRAGMENT_NOT_CREATED -> configurable(
        "Use a Kotlin condition at a location where the Kotlin debugger cannot create a code fragment.");
      case INCORRECT_USER_CODE -> configurable("Use deliberately invalid condition text, for example 'missingName +'.");
      case NO_ADDITIONAL_DETAIL -> internal("Generic fallback used when no more specific detail was attached.");
      case NO_FALLBACK -> internal("Instrumentation failed on a path on which the IDE has no evaluator fallback.");
      case NO_SOURCE_POSITION -> internal("Requires a breakpoint request without a source position.");
      case NO_CONTEXT_ELEMENT -> internal("Requires source-position resolution to return no PSI context element.");
      case NO_CONTEXT_FROM_SUPPORTER -> internal("Requires the language instrumentation supporter to reject the PSI context.");
      case INTERNAL_COMPILER_ERROR -> internal("Requires the fragment compiler to fail internally, rather than reject user code.");
      case AGENT_FAILED_TO_FINISH_INSTRUMENTATION -> internal("Requires fault injection or a debugger-agent failure.");
      case INSTRUMENTED_USER_CLASS_BECOME_NOT_VALID -> internal("Requires the target class to become invalid during installation.");
      case INCORRECT_INSTRUMENTATION_IN_RUNTIME -> internal("Requires malformed instrumented bytecode/runtime protocol data.");
      case CANNOT_INSTALL_USER_CODE_HIT_BREAKPOINT -> internal("Requires the debugger agent to reject the instrumented class installation.");
      case NO_FOUND_SINGLE_CHILD_IN_FRAGMENT_PSI -> internal("Requires an unexpected compiler-fragment PSI shape.");
      case RESULT_VALUE_IS_MISSING_IN_FRAGMENT_PSI -> internal("Requires a compiler fragment without its generated result value.");
      case RESULT_VAR_NAME_IS_WRONG_IN_FRAGMENT_PSI -> internal("Requires a compiler fragment with an unexpected generated result variable.");
      case RESULT_INIT_IS_NOT_METHOD_CALL -> internal("Requires a compiler fragment whose result initializer is not a method call.");
      case WRONG_INVOCATION_METHOD_NAME -> internal("Requires a compiler fragment with an unexpected invocation method name.");
      case MAIN_FRAGMENT_CLASS_OBJECT_MISSING -> internal("Requires compiled fragment output without the main fragment class object.");
      case CLASS_BINARY_CODE_MISSING -> internal("Requires a compiled class object without binary bytecode.");
      case FRAGMENT_CLASS_CTOR_ARGS_MISSING -> internal("Requires fragment metadata without constructor arguments.");
      case UNEXPECTED_FRAGMENT_CALL_SHAPE -> internal("Requires an unsupported generated fragment call shape.");
      case NON_REFERENCE_ARGUMENT -> internal("Requires a generated fragment call containing a non-reference argument.");
      case MISSING_ARGUMENT_REFERENCE_NAME -> internal("Requires a generated argument reference without a name.");
      case LOADING_NO_COMPILED_DATA -> internal("Requires the agent loading phase to receive no compiled fragment data.");
      case LOADING_NO_FRAGMENT_ENTRY_CLASS -> internal("Requires compiled data without the fragment entry class.");
      case LOADING_NO_FRAGMENT_ENTRY_METHOD -> internal("Requires the fragment entry class without its entry method.");
    };
  }

  private static Case source(String setup) {
    return new Case(Kind.SOURCE_REPRODUCIBLE, setup);
  }

  private static Case configurable(String setup) {
    return new Case(Kind.DEBUGGER_CONFIGURATION, setup);
  }

  private static Case internal(String setup) {
    return new Case(Kind.IDE_INTERNAL_ONLY, setup);
  }

  private static void runSourceScenario(Detail detail) {
    switch (detail) {
      case BREAKPOINT_CONTAINS_MODIFIERS, BREAKPOINT_CHANGES_LOCAL_VARIABLES, NO_CONDITION_TEXT,
           JAVA_COMPILING_EVALUATOR_UNAVAILABLE, INCORRECT_USER_CODE -> ordinaryStatement();
      case BREAKPOINT_BEFORE_LAMBDA -> beforeLambda();
      case BREAKPOINT_AT_RETURN -> System.out.println("result=" + atReturn(4));
      case BREAKPOINT_AT_LAMBDA -> atLambda();
      case BREAKPOINT_INSIDE_KOTLIN_INLINE_FUNCTION, KOTLIN_CODE_FRAGMENT_NOT_CREATED ->
        System.out.println("Use the Kotlin main class described above.");
      default -> System.out.println("No source-only scenario exists for " + detail);
    }
  }

  private static void ordinaryStatement() {
    for (int i = 0; i < 5; i++) {
      System.out.println("ordinary i=" + i); // BREAKPOINT: use the condition from the selected case.
    }
  }

  private static void beforeLambda() {
    int offset = 10; // BREAKPOINT BEFORE LAMBDA: condition "offset > 0".
    Runnable runnable = () -> System.out.println("lambda result=" + (offset + 1));
    runnable.run();
  }

  private static int atReturn(int value) {
    return value * 2; // BREAKPOINT AT RETURN: condition "value > 0".
  }

  private static void atLambda() {
    int offset = 10;
    Arrays.asList(1, 2, 3).forEach(value -> {
      System.out.println(value + offset); // BREAKPOINT AT LAMBDA: condition "value == 2".
    });
  }
}
