package com.java8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet()
                .stream()
                .mapToLong(Map.Entry::getValue)
                .max()
                .orElse(0);
        System.out.println("maxFreqChar : " + maxFreqChar);

        System.out.println("[11] Find the repeated character from  the  given String strr= MaheshMA");
        String strr= "MaheshMA";
        List<Character> listOfRepeatedChars = strr.chars()
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1L)
                .map(entry -> entry.getKey()).collect(Collectors.toList());

        System.out.println("list of repeated characters are as follows: "+listOfRepeatedChars);

        System.out.println("[12] Find the non repeated character from  the  given String strr= MaheshMA");
        List<Character> listOfNonRepeatedChars = strr.chars()
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1L)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());

        System.out.println("list of non repeated characters are as follows: "+listOfNonRepeatedChars);

        System.out.println("[13] Find maximum Profit from int[] prices = {1, 4, 6, 7}");
        int[] prices = {1, 4, 6, 7};
        System.out.println("prices:" + Arrays.toString(prices));
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE; // Initialize minPrice to a high value
        System.out.println("minPrice:" + minPrice);
        for (int price : prices) {
            minPrice = Math.min(minPrice, price); // Update minPrice if a lower price is found
            maxProfit = Math.max(maxProfit, price - minPrice); // Calculate potential profit at each price
        }
        System.out.println("maxProfit:" + maxProfit);

        System.out.println("[14] Find out the common elements: ");
        List<Integer> aList = Arrays.asList(1, 2, 2, 3, 4);
        List<Integer> bList = Arrays.asList(11, 12, 12, 13, 4);

        // Find common elements using streams and sets
        Set<Integer> aSet = new HashSet<>(aList);
        Set<Integer> bSet = new HashSet<>(bList);
        Set<Integer> commonElements = aSet.stream()
                .filter(bSet::contains)
                .collect(Collectors.toSet());

        System.out.println("Common elements: " + commonElements);

        System.out.println("[15] Find out the second last character from the string str= mahesh");
        String strNew = "mahesh";
        Character secondLastChar = (char) strNew.chars()
                .skip(strNew.length() - 2)
                .findFirst()
                .orElse('0');
        System.out.println("Second last character: " + secondLastChar);

        System.out.println("[16] Find out the second last character from the int[]  val = {2,1,4,5,6,6,8}");
        int[] valNew1 = {2, 1, 4, 5, 6, 6, 8};
        Integer secondLastInt = IntStream.of(valNew1)
                .skip(valNew1.length - 2)
                .findFirst()
                .orElse('0');
        System.out.println("Second last value : " + secondLastInt);

        System.out.println("[17] Fid out second last digit from str = abcd123nhcj345ab6m7");
        // Expected output = 6
        String str = "abcd123nhcj345ab6m7";
        int lastSecondDigitfromString = str.chars()
                .filter(Character::isDigit)
                .mapToObj(Character::getNumericValue)
                .mapToInt(Integer::intValue)
                .skip(Math.max(0, str.chars().filter(Character::isDigit).count() - 2))
                .findFirst()
                .orElse(0);
        System.out.println("second last digit  :" + lastSecondDigitfromString);


        System.out.println("[18] write a function functional interface to get second last digit from str = abcd123nhcj345ab6m7");
        // Expected output = 6

        Function<String, Integer> extract = s -> {
            StringBuffer sb = new StringBuffer();
            for (char c : s.toCharArray()) {
                if (Character.isDigit(c))
                    sb.append(c);
            }

            //System.out.println("secondLargest: " + Character.getNumericValue(sb.charAt(sb.length() - 2)));
            return Character.getNumericValue(sb.charAt(sb.length() - 2));
        }   ;

        int secondLargest = extract.apply(str);
        System.out.println("secondLargest: " + secondLargest);


    }
}