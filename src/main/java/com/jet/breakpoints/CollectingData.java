package com.jet.breakpoints;

import com.jet.coverage.TestReflection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectingData {
    public static void main(String[] args) throws IOException {

        List<String> result = Files.readAllLines(Paths.get("src/main/java/com/jet/text.txt"));

        int[][] a = new int[1000][1000];
        System.out.println(Arrays.toString(a));

        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= Math.pow(2, 10); i++) {
            list.add("Hey - I'm busy looking at: " + i);

        }

    }


}
