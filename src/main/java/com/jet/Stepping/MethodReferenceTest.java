package com.jet.Stepping;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class MethodReferenceTest {
    public static void main(String[] args) {

        List<String> messages = Arrays.asList("hello", "baeldung", "readers!");
        messages.forEach(word -> StringUtils.capitalize(word));
        messages.forEach(StringUtils::capitalize);

    }
}
