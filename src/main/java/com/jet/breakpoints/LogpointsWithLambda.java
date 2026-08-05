package com.jet.breakpoints;

import java.util.Comparator;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LogpointsWithLambda {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> numbers = IntStream.rangeClosed(1, 12)
                .boxed()
                .toList();

        Thread.sleep(2000);
        List<DemoUser> users = List.of(
                new DemoUser("Ada", true),
                new DemoUser("Grace Hopper", true),
                new DemoUser("Katherine Johnson", false),
                new DemoUser("Margaret Hamilton", true)
        );

        String invertedOddNumbers = numbers
                .stream()
                .filter(n -> n % 2 != 0) // LOGPOINT HERE: "numbers before odd filter = " + numbers
                .map(n -> -n)
                .map(String::valueOf)
                .collect(Collectors.joining("; "));

        boolean hasLongName = users
                .stream()
                .map(DemoUser::name)
                .anyMatch(name -> name.length() > 12); // LOGPOINT HERE: "user names before long-name check = " + users.stream().map(DemoUser::name).toList()

        List<Integer> doubledOdds = numbers
                .stream()
                .flatMap(n -> n % 2 == 0 ? Stream.empty() : Stream.of(n * 2)) // LOGPOINT HERE "numbers before flatMap odds = " + numbers
                .toList();

        DemoUser futureUser = new DemoUser("  Barbara Liskov  ", true);
        String futureLabel = CompletableFuture.completedFuture(futureUser)
                .thenApply(DemoUser::name)
// LOGPOINT HERE: should be before thenApply(...), not inside the lambda.
// Log expression: "raw future user name = '" + futureUser.name() + "'"
                .thenApply(name -> name.trim().toUpperCase())
                .thenApply(name -> "User: " + name)
                .join();

        Optional<DemoUser> maybeActiveUser = Optional.of(new DemoUser("Donald Knuth", true));
        Optional<String> activeUserLabel = maybeActiveUser
// LOGPOINT HERE: should be before filter(...), not inside the lambda.
// Log expression: "optional user before active filter = " + maybeActiveUser
                .filter(DemoUser::active)
                .map(DemoUser::name)
                .map(name -> "[" + name + "]");

        List<String> sortedNames = users
                .stream()
                .map(DemoUser::name)
// LOGPOINT HERE: should be before sorted(...), not inside the comparator lambda.
// Log expression: "name lengths = " + users.stream().map(user -> user.name() + "=" + user.name().replace(" ", "").length()).toList()
                .sorted(Comparator.comparingInt(name -> name.replace(" ", "").length()))
                .toList();

        List<String> nullableNames = Arrays.asList("alpha", null, "beta", "gamma");
        String nonNullNames = nullableNames
                .stream()
// LOGPOINT HERE: should be before filter(...), not inside the lambda.
// Log expression: "values before null filter = " + nullableNames
                .filter(Objects::nonNull)
                .map(value -> value + "!")
                .collect(Collectors.joining(", "));

        System.out.println("invertedOddNumbers = " + invertedOddNumbers);
        System.out.println("hasLongName = " + hasLongName);
        System.out.println("doubledOdds = " + doubledOdds);
        System.out.println("futureLabel = " + futureLabel);
        System.out.println("activeUserLabel = " + activeUserLabel.orElse("none"));
        System.out.println("sortedNames = " + sortedNames);
        System.out.println("nonNullNames = " + nonNullNames);
    }

    private record DemoUser(String name, boolean active) {
    }
}
