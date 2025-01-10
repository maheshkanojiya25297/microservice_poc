package com.java8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        System.out.println("***********************************************************************************************lets start with some Java 8 features codding Practise !!");

        System.out.println("[1] find the first non-repeated character in it using Stream functions with int valaues = {1,2,2,4,1,5,5}");

        int[] intValues = {1, 2, 2, 2, 2, 4, 1, 5, 5};
        Map<Integer, Long> firstNonRepeatedIntFromArray = Arrays.stream(intValues)
                .boxed()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));

        firstNonRepeatedIntFromArray.forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });
        for (int val : intValues) {
            if (firstNonRepeatedIntFromArray.get(val) == 1L) {
                System.out.println("first non-repeated character found :-" + val);
                break;
            }
        }

        System.out.println("[2] Given a String, find the first non-repeated character in it using Stream functions? String str= mahesh ");
        String strValues = "mahesh";
        Character firstNonRepeatedCharFromString = strValues.chars()
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1L)
                .map(entry -> entry.getKey())
                .findFirst()
                .get();
        System.out.println("first non-repeated character found: " + firstNonRepeatedCharFromString);

        System.out.println("[3] find the first repeated character in given string t using Stream functions with intvalaues = {1,2,2,4,1,5,5}");
        Map<Integer, Long> firstRepeatedIntFromArray = Arrays.stream(intValues)
                .boxed()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));

        firstRepeatedIntFromArray.forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });
        for (int val : intValues) {
            if (firstRepeatedIntFromArray.get(val) > 1L) {
                System.out.println("first repeated character found: " + val);
                break;
            }
        }

        System.out.println("[4] Given a String, find the first repeated character in it using Stream functions? String str= mahesh ");
        Character firstRepeatedCharFromString = strValues.chars()
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1L)
                .map(entry -> entry.getKey())
                .findFirst()
                .get();
        System.out.println("first repeated character found: " + firstRepeatedCharFromString);

        System.out.println("[5] find the frequency of given array element with int [] intvalaues = {1,2,2,4,1,5,5}");
        Map<Integer, Long> frequencyOfeachElementOfIntValus = Arrays.stream(intValues)
                .boxed()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
        System.out.println("frequency of each element are :");
        frequencyOfeachElementOfIntValus.forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });

        System.out.println("[6] find the frequency of character of given String = mahesh");
        Map<String, Long> frequencyOfeachElementOfStrValus = Arrays.stream(strValues.split(""))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        System.out.println("frequency of each element are :");
        frequencyOfeachElementOfStrValus.forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });

        System.out.println("[7] Find the element who having Maximum frequency of given array element with intvalaues = {1,2,2,2,2,4,1,5,5}");
        Integer elehavingMaxFreq = Arrays.stream(intValues)
                .boxed()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .get();

        System.out.println("elehavingMaxFreq : " + elehavingMaxFreq);

        System.out.println("[8] Find the element who having Maximum frequency of given String strValues = Mahesh");
        Character charelehavingMaxFreq = strValues.chars()
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .get();
        System.out.println("charelehavingMaxFreq : " + charelehavingMaxFreq);

        System.out.println("[9] Find the element of Maximum frequency of given array element with intvalaues = {1,2,2,2,2,4,1,5,5}");
        Long maxFreq = Arrays.stream(intValues)
                .boxed()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()))
                .entrySet()
                .stream()
                .mapToLong(Map.Entry::getValue)
                .max()
                .orElse(0);
        System.out.println("maxFreq : " + maxFreq);

        System.out.println("[10] Find the element of Maximum frequency of given String strValues= Mahesh");
        Long maxFreqChar = strValues.chars()
                .mapToObj(c->Character.toLowerCase(Character.valueOf((char)c)))
                .collect(Collectors.groupingBy(c->c, Collectors.counting()))
                .entrySet()
                .stream()
                .mapToLong(Map.Entry::getValue)
                .max()
                .orElse(0);
        System.out.println("maxFreqChar : " + maxFreqChar);

        List<Integer> aList = Arrays.asList(1, 2, 2, 3, 4);
        List<Integer> bList = Arrays.asList(11, 12, 12, 13, 4);

        // Find common elements using streams and sets
        Set<Integer> aSet = new HashSet<>(aList);
        Set<Integer> bSet = new HashSet<>(bList);
        Set<Integer> commonElements = aSet.stream()
                .filter(bSet::contains)
                .collect(Collectors.toSet());

        System.out.println("Common elements: " + commonElements);



    }
}