package ru.academits.ikonnikov.arrayListHome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
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
        } catch (FileNotFoundException e) {
            System.out.println("The file is not exist!");
            e.printStackTrace();
        }

        ArrayList<Integer> numbers1 = new ArrayList<>(Arrays.asList(13, 4, 18, 9, 10, 9, 14, 19, 2, 4, 16, 1, 7, 27, 13, 6, 11));

        System.out.println(numbers1);

        for (int i = 0; i < numbers1.size(); i++) {
            if (numbers1.get(i) % 2 == 0) {
                numbers1.remove(i);
                i--;
            }
        }
        System.out.println(numbers1);

        ArrayList<Integer> numbers2 = new ArrayList<>(Arrays.asList(7, 2, 19, 12, 4, 5, null, 12, 17, 6, 14, 12, 19, 17, 7, 3, 4, 14));
        ArrayList<Integer> numbersNoRepeats = new ArrayList<>();
        System.out.println(numbers2);

        if (!numbers2.isEmpty()) {
            for (Integer integer : numbers2) {
                if (!numbersNoRepeats.contains(integer)) {
                    numbersNoRepeats.add(integer);
                }
            }
        }
        System.out.println(numbersNoRepeats);
    }
}
