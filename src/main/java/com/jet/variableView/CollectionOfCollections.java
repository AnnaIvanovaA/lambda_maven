package com.jet.variableView;

import java.util.*;

public class CollectionOfCollections {

    public static void main(String[] args) {
        //listOf(arrayOf(1, 2, 3));

        // Creating nested lists
        List<List<Integer>> nestedList = new ArrayList<>();
        nestedList.add(Arrays.asList(1, 2, 3));
        nestedList.add(Arrays.asList(4, 5, 6));
        nestedList.add(Arrays.asList(7, 8, 9));

        // Printing nested lists
        for (List<Integer> innerList : nestedList) {
            System.out.println("Inner List: " + innerList);
        }
        // Creating different collections
        Integer[] array = {1, 2, 3};                     // Array
        List<Integer> list = Arrays.asList(4, 5, 6);     // List
        Set<Integer> set = new HashSet<>(Arrays.asList(7, 8, 9)); // Set

        // Creating a List of Collections
        List<Collection<Integer>> collectionOfCollections = new ArrayList<>();
        collectionOfCollections.add(Arrays.asList(array)); // Wrap array as a List
        collectionOfCollections.add(list);                // Add List
        collectionOfCollections.add(set);                 // Add Set

        // Printing the collection of collections
        for (Collection<Integer> collection : collectionOfCollections) {
            System.out.println("Collection: " + collection);
        }

    }
}
