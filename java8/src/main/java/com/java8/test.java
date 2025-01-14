package com.java8;

import java.net.CacheRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.*;

public class test {


    public static void main(String[] args) {

        List<Integer> list1 = Arrays.asList(1, 2, 3, 45, 6);
        List<Integer> list2 = Arrays.asList(2, 3, 45, 5, 6, 7);

        Set<Integer> aSet = new HashSet<>(list1);
        Set<Integer> bSet = new HashSet<>(list2);

        List<Integer> output = aSet.stream().filter(bSet::contains).collect(Collectors.toList());
        System.out.println("output: " + output);


        int[] intval = {1, 6, 7, 8, 9, 7};
        int minimumProfit = Integer.MAX_VALUE;
        int maximumProfit = 0;
        for (int i : intval) {
            minimumProfit = Math.min(minimumProfit, i);
            maximumProfit = Math.max(maximumProfit, i - minimumProfit);
        }

        System.out.println("maximumProfit: " + maximumProfit);


        String str = "this is test program";
        Character result = str.chars().mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().filter(entry -> entry.getValue() == 1L).map(entry -> entry.getKey()).findFirst().get();

        System.out.println("result: " + result);

        Character result1 = str.chars()
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1L)
                .map(entry -> entry.getKey())
                .findFirst()
                .get();

        System.out.println("result1: " + result1);

        Map<String, Long> freqOfStr = Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        freqOfStr.forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });


        Long MaxFrq = str.chars()
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet()
                .stream()
                .mapToLong(Map.Entry::getValue)
                .max()
                .orElse(0);

        System.out.println("MaxFrq: " + MaxFrq);

        Character MaxFreqChar = str.replace(" ", "").chars()
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .get();

        System.out.println("MaxFreqChar: " + MaxFreqChar);


        Map<Integer, Long> FreqInt = Arrays.stream(intval).boxed().collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
        System.out.println("FreqInt: " + FreqInt);
        FreqInt.forEach((k, v) -> {
            System.out.println(k + "  : " + v);
        });


        Long maxFreqInt = Arrays.stream(intval)
                .boxed()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()))
                .entrySet()
                .stream()
                .mapToLong(Map.Entry::getValue).max().orElse(0);

        System.out.println("int arrays are: " + Arrays.toString(intval));
        System.out.println("maxFreqInt: " + maxFreqInt);


        Integer MaxFreqInt = Arrays.stream(intval).boxed().collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).get();
        System.out.println("MaxFreqInt: " + MaxFreqInt);

        Map<Integer, Long> firstRepeatedIntValue = Arrays.stream(intval).boxed().collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
        for(int val : intval){
            if(firstRepeatedIntValue.get(val)>1L){
                System.out.println("first repeated value is :" +val);
                break;
            }
        }

        for(int val: intval){
            if(firstRepeatedIntValue.get(val)==1L){
                System.out.println("first non repeated value is: " +val);
                break;
            }
        }




    }

}

