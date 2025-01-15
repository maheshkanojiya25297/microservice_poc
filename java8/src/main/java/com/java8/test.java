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

        System.out.println("[2] Given a String, find the first non-repeated character in it using Stream functions? String str= maheshS ");
        String input1 = "maheshS";
        Character output1 = input1.chars()
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1L)
                .map(entry -> entry.getKey())
                .findFirst()
                .get();
        System.out.println("non-repeated Character: " + output1);

        System.out.println("[4] Given a String, find the first repeated character in it using Stream functions? String str= maheshS ");
        String input2 = "maheshS";
        Character output2 = input2.chars()
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1L)
                .map(entry -> entry.getKey())
                .findFirst()
                .get();

        System.out.println("repeated Character: " + output2);

        System.out.println("[6] find the frequency of character of given String = maheshS");
        String input3 = "maheshS";
        Map<String, Long> output3 = Arrays.stream(input3.replace(" ", "").split(""))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        output3.forEach((data, count) -> {
            System.out.println(data + " : " + count);
        });

        System.out.println("[8] Find the element who having Maximum frequency of given String strValues = maheshS");
        String input4 = "mahesh";
        Character output4 = input4.chars()
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .get();

        System.out.println("Maximum frequency of character in String is : " + output4);

        System.out.println("[10] Find the element of Maximum frequency of given String strValues= maheshS");
        String input5 = "maheshS";
        Long output5 = input5.chars()
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet()
                .stream().mapToLong(Map.Entry::getValue)
                .max().orElse(0);

        System.out.println("Maximum frequency  is : " + output5);

        System.out.println("[11] Find the repeated character from  the  given String strr= maheshS");
        String input6 = "maheshS";
        List<Character> output6 = input6.chars()
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1L)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());

        output6.forEach(e -> System.out.println(e));

        System.out.println("[12] Find the nonrepeated character from  the  given String strr= MaheshMA");
        String input7 = "ADGHFJHG";
        List<Character> output7 = input7.chars()
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1L).map(entry -> entry.getKey()).collect(Collectors.toList());
        output7.forEach(l -> System.out.println(l));

        System.out.println("[15] Find out the second last character from the string str= mahesh");
        String input8 = "mahesh";
        Character output8 = (char) input8
                .chars()
                .skip(input8.length() - 2)
                .findFirst()
                .orElse('\0');
        System.out.println("second last character: " + output8);

        System.out.println("[17] Fid out second last digit from str = abcd123nhcj345ab6m7");
        String input9 = "abcd123nhcj345ab6m7";
        Character output9 = (char) input9
                .chars()
                .filter(Character::isDigit)
                .mapToObj(Character::getNumericValue)
                .mapToInt(Integer::intValue)
                .skip(Math.max(0, input9.chars().filter(Character::isDigit).count() - 2))
                .findFirst()
                .orElse('0');
        System.out.println("second last digit is : " + output9);

        System.out.println("[18] write a function functional interface to get second last digit from str = abcd123nhcj345ab6m7");
        String input10 = "abcd123nhcj345ab6m7";
        Function<String, Integer> extract = data -> {
            StringBuilder sb = new StringBuilder();
            for (char c : data.toCharArray()) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                }
            }
            return Character.getNumericValue(sb.charAt(sb.length() - 2));
        };
        int output10 = extract.apply(input10);
        System.out.println("Second last digit is: " + output10);


    }
}

