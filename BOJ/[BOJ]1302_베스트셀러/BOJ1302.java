package algo;

import java.util.*;
import java.io.*;

public class BOJ1302 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        HashMap<String, Integer> books = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String book = br.readLine();
            if(books.containsKey(book)) books.put(book, books.get(book)+1);
            else books.put(book, 1);
        }

        List<Map.Entry<String, Integer>> entities = new ArrayList<>(books.entrySet());
        Collections.sort(entities, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue().equals(o2.getValue())) return o1.getKey().compareTo(o2.getKey());
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        System.out.println(entities.get(0).getKey());
    }
}
