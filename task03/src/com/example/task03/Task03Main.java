package com.example.task03;

import java.io.*;
import java.nio.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        Map<String, Set<String>> anagramMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset)))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                String word = line.trim().toLowerCase();
                if (isValidWord(word)) {
                    String sortedWord = sortWord(word);
                    anagramMap.computeIfAbsent(sortedWord, C -> new HashSet<>()).add(word);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return processAnagramMap(anagramMap);
    }
    public static List<Set<String>> processAnagramMap(Map<String, Set<String>> anagramMap) {
        List<Set<String>> result = new ArrayList<>();
        Collection<Set<String>> values = anagramMap.values();
        Iterator<Set<String>> valuesIterator = values.iterator();
        while (valuesIterator.hasNext()) {
            Set<String> set = valuesIterator.next();
            if (set.size() > 1) {
                List<String> sortedList = new ArrayList<>(set);
                Collections.sort(sortedList);
                Set<String> sortedSet = new LinkedHashSet<>(sortedList);
                result.add(sortedSet);
            }
        }
        Collections.sort(result, new Comparator<Set<String>>() {
            @Override
            public int compare(Set<String> set1, Set<String> set2) {
                return set1.iterator().next().compareTo(set2.iterator().next());
            }
        });
        return result;
    }
    private static boolean isValidWord(String word) {
        if (word.length() < 3) {
            return false;
        }
        for (char c : word.toCharArray()) {
            if (!Character.isLetter(c) || !Character.UnicodeBlock.of(c).equals(Character.UnicodeBlock.CYRILLIC)) {
                return false;
            }
        }
        return true;
    }
    private static String sortWord(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}



