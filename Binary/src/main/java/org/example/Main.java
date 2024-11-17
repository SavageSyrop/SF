package org.example;

import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Integer[] numbersArray = {1,2,3,4,5,6,7,8,9};
        byte[] bytes = {1,6,9,23,75};
        char[] chars = {'c', 'h', 'a', 'r'};
        double[] doubles = {6d, 9d, 12d, 16d, 62d};
        float[] floats = {2f, 11f, 42f, 96f, 261f};
        long[] longs = {7L, 18L, 27L, 55L, 75L};
        short[] shorts = {4, 8, 16, 82, 99};
        List<Integer> numbersList = java.util.Arrays.asList(numbersArray);

        System.out.println(Arrays.binarySearch(bytes, bytes[3])); // contains
        System.out.println(Arrays.binarySearch(bytes, 0, 2, bytes[3])); // not found
        System.out.println(Arrays.binarySearch(chars, 'c')); // contains
        System.out.println(Arrays.binarySearch(chars, 0, 2, chars[3])); // not found
        System.out.println(Arrays.binarySearch(doubles, 62d)); // contains
        System.out.println(Arrays.binarySearch(doubles, 0, 2, 62d)); // not found
        System.out.println(Arrays.binarySearch(floats, 42f)); // contains
        System.out.println(Arrays.binarySearch(floats, 0, 2, 42f)); // not found
        System.out.println(Arrays.binarySearch(java.util.Arrays.stream(numbersArray).mapToInt(i->i).toArray(), 6)); // contains
        System.out.println(Arrays.binarySearch(java.util.Arrays.stream(numbersArray).mapToInt(i->i).toArray(), 0, 2, 6)); // not found
        System.out.println(Arrays.binarySearch(longs, 55L)); // contains
        System.out.println(Arrays.binarySearch(longs, 0, 2, 55L)); // not found
        System.out.println(Arrays.binarySearch(shorts, shorts[3])); // contains
        System.out.println(Arrays.binarySearch(shorts, 0, 2, shorts[3])); // not found
        System.out.println(Arrays.binarySearch(numbersArray, 7, Comparator.comparingInt(o -> o))); // contains
        System.out.println(Arrays.binarySearch(numbersArray, 0, 5, 7, Comparator.comparingInt(o -> o))); // not found
        System.out.println(Collections.binarySearch(numbersList, 3)); // contains
        System.out.println(Collections.binarySearch(numbersList, 4, Comparator.comparingInt(o -> o))); // contains
    }
}