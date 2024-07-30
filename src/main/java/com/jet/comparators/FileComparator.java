package com.jet.comparators;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileComparator {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java WordComparator <file1> <file2> <outputFile3>");
            return;
        }

        String file1Path = args[0];
        String file2Path = args[1];
        String fileOutputPath = args[2];

        try {
            Set<String> file1Words = loadWordsFromFile(file1Path);
            Set<String> file2Words = loadWordsFromFile(file2Path);

            printDifference(file1Words, file2Words, fileOutputPath);
        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
        }
    }

    private static Set<String> loadWordsFromFile(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        Set<String> words = new HashSet<>();

        for (String line : lines) {
            String[] splitWords = line.split("\\s+");
            for (String word : splitWords) {
                if (!word.trim().isEmpty()) {
                    words.add(word.trim());
                }
            }
        }

        return words;
    }

    private static void printDifference(Set<String> file1Words, Set<String> file2Words, String fileOutputPath) throws IOException {
        file2Words.removeAll(file1Words);

        if (file2Words.isEmpty()) {
            System.out.println("All words in file 2 are also in file 1.");
        } else {
            System.out.println("Words present in file 2 but not in file 1:");
            for (String word : file2Words) {
                System.out.println(word);
            }
        }

        Files.write(Paths.get(fileOutputPath), file2Words, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        System.out.println("The differences have been saved to " + fileOutputPath);
    }
}