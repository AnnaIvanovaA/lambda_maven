package com.jet.streams;

import kotlin.jvm.JvmClassMappingKt;

import java.util.List;
import java.util.stream.Collectors;

public class CheckNumberStreams {
    public static void main(String[] args) {

        double v = NumberStream.dPoint[0];
        List<Integer> numbers = List.of(1, 2, 3, 4, 5,6,5);
        JvmClassMappingKt.getJavaClass(v);

        String invertedOddNumbers = numbers
                .stream()
                .filter(it -> it % 2 != 0).map(it -> -it)
                .map(Object::toString)
                .collect(Collectors.joining("; "));
    }
}
