package com.java8;

import java.util.*;

public class test2 {

    public static void main(String[] args) {
        String s1 = "TRUEFORTRUTH";
        //System.out.println("length: " + lengthOfLongestSubstring(s1));
        System.out.println("length: " + lengthOfLongestSubstring(s1));
        System.out.println("String: " + longestSubstring(s1));
    }


    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        int left = 0;
        Set<Character> charSet = new HashSet<>();

        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);
            while (charSet.contains(c)) {
                charSet.remove(s.charAt(left));
                left++;
            }
            charSet.add(c);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }


    public static String longestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        int left = 0;
        int start = 0;
        Set<Character> charSet = new HashSet<>();

        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);
            while (charSet.contains(c)) {
                charSet.remove(s.charAt(left));
                left++;
            }
            charSet.add(c);
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                start = left;
            }
        }
        return s.substring(start, start + maxLength);
    }


        /*

    private static int lengthOfLongestSubstring(String s) {

        System.out.println("s1: " + s);

        int n = s.length(), ans = 0, left = 0;


        Map<Character, Integer> map = new HashMap<>();
        for (int right = 0; right < n; right++) {
            if (map.containsKey(s.charAt(right))) {

                left = Math.max(left, map.get(s.charAt(right)) + 1);

            }
            map.put(s.charAt(right), right);
            ans = Math.max(ans, right - left + 1);

        }
        return ans;
    }
    */


}