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

            if (!scanner.hasNext()) {
                System.out.println("This file is empty!");
            } else {
                ArrayList<String> list = new ArrayList<>();

                while (scanner.hasNext()) {
                    list.add(scanner.next());
                }
                System.out.println(list);
            }
        } catch ( FileNotFoundException e ) {
            e.getMessage();
        }

        ArrayList<Integer> numbers1 = new ArrayList<>(Arrays.asList(13, 4, 18, 9, null, 10, 9, 14, 19, 2, 4, 16, 1, 7, 27, 13, 6, 11));

        System.out.println(numbers1);

        for (int i = 0; i < numbers1.size(); i++) {
            if (numbers1.get(i) == null) {
                continue;
            }

            if (numbers1.get(i) % 2 == 0) {
                numbers1.remove(i);
                i--;
            }
        }
        System.out.println(numbers1);

        ArrayList<Integer> numbers2 = new ArrayList<>(Arrays.asList(null, 7, 2, 19, 12, null, 4, 5, 12, 17, 6, 14, 12, 19, 17, 7, 3, 4, 14));

        if (numbers2.isEmpty()) {
            System.out.println("This ArrayList is empty!");
        } else {
            System.out.println(numbers2);
            ArrayList<Integer> numbersNoRepeats = new ArrayList<>();
            boolean existNull = false;

            if (numbers2.get(0) == null) {
                numbersNoRepeats.add(null);
                existNull = true;
            } else {
                numbersNoRepeats.add(numbers2.get(0));
            }

            for (int i = 1; i < numbers2.size(); i++) {
                if (numbers2.get(i) == null) {
                    if (existNull) {
                        continue;
                    } else {
                        numbersNoRepeats.add(null);
                        existNull = true;
                        continue;
                    }
                }
                if (numbersNoRepeats.indexOf(numbers2.get(i)) == -1) {
                    numbersNoRepeats.add(numbers2.get(i));
                }
            }
            System.out.println(numbersNoRepeats);
        }
    }
}
