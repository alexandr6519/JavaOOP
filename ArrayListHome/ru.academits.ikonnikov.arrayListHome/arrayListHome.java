package ru.academits.ikonnikov.arrayListHome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class arrayListHome {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new FileInputStream("./res/input.txt"));
            ArrayList<String> list = new ArrayList<>();

            do {
                list.add(scanner.next());
            } while (scanner.hasNext());

            System.out.println(list);
        } catch ( FileNotFoundException e ) {
            e.getMessage();
        }

        ArrayList<Integer> numbers1 = new ArrayList<>(Arrays.asList(13, 4, 18, 9, 22, 10, 9, 14, 19, 2, 4, 16, 1, 7, 27, 13, 6, 11));

        System.out.println(numbers1);
        int end = numbers1.size();

        for (int i = 0; i < end; i++) {
            if (numbers1.get(i) % 2 == 0) {
                numbers1.remove(i);
                i--;
                end--;
            }
        }
        System.out.println(numbers1);

        ArrayList<Integer> numbers2 = new ArrayList<>(Arrays.asList(7, 2, 19, 12, 4, 5, 12, 17, 6, 14, 12, 19, 17, 7, 3, 4, 14));
        System.out.println(numbers2);

        ArrayList<Integer> numbersNoRepeats = new ArrayList<>();
        numbersNoRepeats.add(numbers2.get(0));

        for (int i = 1; i < numbers2.size(); i++) {
            boolean needAdd = true;

            for (Integer number : numbersNoRepeats) {
                needAdd = needAdd && (!numbers2.get(i).equals(number));
            }

            if (needAdd) {
                numbersNoRepeats.add(numbers2.get(i));
            }
        }
        System.out.println(numbersNoRepeats);

    }
}
