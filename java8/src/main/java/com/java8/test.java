package com.java8;

import java.net.CacheRequest;
import java.sql.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.*;
import java.util.stream.IntStream;

public class test {


    public static void main(String[] args) {

        System.out.println("[1] find the first non-repeated character in it using Stream functions with int valaues = {1,2,2,4,1,5,5}");

        int[] valaues = {1, 2, 2, 4, 1, 5, 5};

        Map<Integer, Long> freqOfVal = Arrays.stream(valaues)
                .boxed()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));

        freqOfVal.forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });

        for (int val : valaues) {
            if (freqOfVal.get(val) == 1L) {
                System.out.println("first non repeated character found: " + val);
                break;
            }
        }

        System.out.println("[2] Given a String, find the first non-repeated character in it using Stream functions? String str= maheshS ");

        String str = "maheshS";

        Character result = str.chars()
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1L)
                .map(entry -> entry.getKey())
                .findFirst()
                .get();
        System.out.println("first non repeated character from string found: " + result);

        System.out.println("[3] Given a String, find the first repeated character in it using Stream functions? String str= maheshS ");

        Character output = str.chars()
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1L)
                .map(entry -> entry.getKey())
                .findFirst()
                .get();
        System.out.println("first  repeated character from string found: " + output);

        System.out.println("[4] find the first repeated character in given string t using Stream functions with intvalaues = {1,2,2,4,1,5,5}");

        int[] intvalaues = {1, 2, 2, 4, 1, 5, 5};
        Map<Integer, Long> freqOfValues = Arrays.stream(intvalaues)
                .boxed()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));

        for (int val : intvalaues) {
            if (freqOfValues.get(val) > 1L) {
                System.out.println("first repeated character in given string found: " + val);
                break;
            }
        }

        System.out.println("[5] find the frequency of character of given String = maheshS");

        String strvalues = "maheshS";

        Map<String, Long> outputNew = Arrays.stream(strvalues.split(""))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        outputNew.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });

        System.out.println("[6] Find the frequency of given array element with intvalaues = {1,2,2,2,2,4,1,5,5}");

        int[] intvalues1 = {1, 2, 2, 2, 2, 4, 1, 5, 5};

        Map<Integer, Long> freqOfeach = Arrays.stream(intvalues1)
                .boxed()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));

        freqOfeach.forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });

        System.out.println("[7] Find the element who having Maximum frequency of given array element with intvalaues = {1,2,2,2,2,4,1,5,5}");

        int[] intvalaues2 = {1, 2, 2, 2, 2, 4, 1, 5, 5};

        Integer maxFreqHaving = Arrays.stream(intvalaues2)
                .boxed()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .get();
        System.out.println("Maximum frequency of given array elemen found: " + maxFreqHaving);

        System.out.println("[8] Find the element who having Maximum frequency of given String strValues = maheshS");

        String strVaString = "maheshS";

        Character maxFreqHavingChar = strVaString.chars()
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .get();
        System.out.println("Maximum frequency of given String found: " + maxFreqHavingChar);

        System.out.println("[9] Find the element of Maximum frequency of given array element with intvalaues = {1,2,2,2,2,4,1,5,5}");

        int[] intvalaues3 = {1, 2, 2, 2, 2, 4, 1, 5, 5};
        Long maxFreqHavingInt = Arrays.stream(intvalaues3)
                .boxed()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()))
                .entrySet()
                .stream()
                .mapToLong(Map.Entry::getValue)
                .max()
                .orElse(0);
        System.out.println("Maximum frequency of given array elemen found: " + maxFreqHavingInt);

        System.out.println("[10] Find the element of Maximum frequency of given String strValues= maheshS");

        String strValues3 = "maheshS";
        Long maxFreqHavingCharacter = strValues3.chars()
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet()
                .stream()
                .mapToLong(Map.Entry::getValue)
                .max()
                .orElse(0);
        System.out.println("Maximum frequency of given array String found: " + maxFreqHavingCharacter);

        System.out.println("[6] Find the repeated character from  the  given String strr= maheshS");
        String strr = "maheshS";

        List<Character> repeatedListOfCharacter = strr.chars()
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1L)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
        System.out.println("repeated Character list are : " + repeatedListOfCharacter);

        System.out.println("[7] Find the nonrepeated character from  the  given String strr= MaheshMA");

        String strr1 = "MaheshMA";

        List<Character> nonrepeatedListOfCharacter = strr1.chars()
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1L)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
        System.out.println("242025 non repeated Character list are : " + nonrepeatedListOfCharacter);

        System.out.println("[8] Fid out second last character from str = abcd123nhcj345ab6m7");

        String str2 = "abcd123nhcj345ab6m7";
        Character secondLastCharFromString = (char) str2.chars()
                .skip(str2.length() - 2)
                .findFirst()
                .orElse('0');
        System.out.println("second last character from string  are : " + secondLastCharFromString);

        System.out.println("[9] Fid out second last character from array intvalaues = {1,2,2,2,2,4,1,5,5}");
        int[] intarr = {1, 2, 2, 2, 2, 4, 1, 5, 5};
        Integer secondLastFromintArr = IntStream.of(intarr)
                .skip(intarr.length - 2)
                .findFirst()
                .orElse(0);
        System.out.println("second last int from array  are : " + secondLastFromintArr);

        System.out.println("[9] Fid out second last digit from str = abcd123nhcj345ab6m7");

        String digitNewInput = "abcd123nhcj345ab6m7";
        Integer secondLastDigit = digitNewInput.chars()
                .filter(Character::isDigit)
                .mapToObj(Character::getNumericValue)
                .mapToInt(Integer::intValue)
                .skip(Math.max(0, digitNewInput.chars().filter(Character::isDigit).count() - 2))
                .findFirst()
                .orElse(0);
        System.out.println("second last digit from String  are : " + secondLastDigit);

        System.out.println("[10] write a function functional interface to get second last digit from str = abcd123nhcj345ab6m7");
        String str2ndDigit = "abcd123nhcj345ab6m7";
        Function<String, Integer> extract = s -> {
            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                }
            }
            return Character.getNumericValue(sb.charAt(sb.length() - 2));
        };
        int out = extract.apply(str2ndDigit);
        System.out.println("second last digit from String  are : " + out);

        System.out.println("[11] list out the repeated character from  given array element with intvalaues = {1,2,2,2,2,4,1,5,5}");
        int[] arrIntVal = {1, 2, 2, 2, 2, 4, 1, 5, 5};
        Map<Integer, Long> freqOfintvaluesArray = Arrays.stream(arrIntVal)
                .boxed()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));

        List<Integer> outputNew1 = new ArrayList<>();
        for (int val : arrIntVal) {
            if (freqOfintvaluesArray.get(val) > 1L) {
                outputNew1.add(val);
            }
        }
        System.out.println("outputNew1 : " + outputNew1.stream().distinct().collect(Collectors.toList()));

        System.out.println("[12] list out the nonrepeated character from given array element with intvalaues = {1,2,2,2,2,4,1,5,5}");
        int[] arrIntVal2 = {1, 2, 2, 2, 2, 4, 1, 5, 5};

        Map<Integer, Long> frq = Arrays.stream(arrIntVal2)
                .boxed()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));

        List<Integer> outputNew2 = new ArrayList<>();
        for (int val : arrIntVal2) {
            if (frq.get(val) == 1L) {
                outputNew2.add(val);
            }
        }
        System.out.println("non repeaed list are : " + outputNew2.stream().distinct().collect(Collectors.toList()));


        //write code to find anagram and groupe them
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
        //Excepted Output: [[bat], [nat, tan], [ate, eat, tea]]
        List<List<String>> groupedAnagrams = groupAnagrams(input);
        System.out.println(groupedAnagrams);

        //Find max repeated vowels from given string and this max vowels words print.
        String[] words = {"apple", "banana", "orange", "kiwi", "aardvark", "elephant"};
        List<String> maxVowelWords = findMaxRepeatedVowels(words);
        System.out.println("max repeated vowels found: " + maxVowelWords);

        List<String> minVowelWords = findMinRepeatedVowels(words);
        System.out.println("min repeated vowels found: " + minVowelWords);

        //write code to find first non repeated vowel from the string
        String strVowel = "Hello World";
        char firstNonRepeatedVowel = findFirstNonRepeatedVowel(strVowel);
        System.out.println("first non repeated vowels found: " + firstNonRepeatedVowel);

        //find the common elements from both of the list
        List<Integer> list1 = Arrays.asList(1, 2, 24, 5, 56, 7);
        List<Integer> list2 = Arrays.asList(1, 4, 5, 2, 1, 56);
        Set<Integer> set1 = new HashSet<>(list1);
        Set<Integer> set2 = new HashSet<>(list2);
        Set<Integer> resultCommonElements = set1.stream().filter(set2::contains).collect(Collectors.toSet());
        System.out.println("common elements between both of the list found: " + resultCommonElements);

    }

    private static char findFirstNonRepeatedVowel(String strVowel) {

        String string = strVowel.toLowerCase().replaceAll("[^a-z]]", "");

        LinkedHashMap<Character, Integer> hm = new LinkedHashMap<>();
        for (char c : string.toCharArray()) {
            if ("aeiou".indexOf(c) != -1) {
                hm.put(c, hm.getOrDefault(c, 0) + 1);
            }
        }

        for (Map.Entry<Character, Integer> entry : hm.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return '\0';

    }

    private static List<String> findMinRepeatedVowels(String[] words) {
        Map<String, Integer> vowelcounts = new HashMap<>();
        for (String word : words) {
            String vowels = word.replaceAll("[^aeiouAEIOU]", "");
            Set<Character> uni = new HashSet<>();
            for (char c : vowels.toCharArray()) {
                uni.add(c);
            }
            vowelcounts.put(word, uni.size());
        }
        int min = Collections.min(vowelcounts.values());

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : vowelcounts.entrySet()) {
            if (entry.getValue() == min) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public static List<String> findMaxRepeatedVowels(String[] words) {

        Map<String, Integer> vowelcounts = new HashMap<>();
        for (String word : words) {
            String vowels = word.replaceAll("[^aeiouAEIOU]", "");
            Set<Character> uniqueVowel = new HashSet<>();
            for (char c : vowels.toCharArray()) {
                uniqueVowel.add(c);
            }
            vowelcounts.put(word, uniqueVowel.size());
        }
        int maxVowelCount = Collections.max(vowelcounts.values());
        //System.out.println("max count of vowel is : " + maxVowelCount);
        List<String> maxVowelWords = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : vowelcounts.entrySet()) {
            System.out.println("entry key : " + entry.getKey());
            System.out.println("entry value : " + entry.getValue());
            if (entry.getValue() == maxVowelCount) {
                maxVowelWords.add(entry.getKey());
            }
        }
        return maxVowelWords;
    }

    public static List<List<String>> groupAnagrams(String[] strr) {
        List<List<String>> output = Arrays.stream(strr)
                .collect(Collectors.groupingBy(str -> {
                    char[] chararry = str.toCharArray();
                    Arrays.sort(chararry);
                    return new String(chararry);
                }))
                .values()
                .stream()
                .toList();
        return output;
          }
}