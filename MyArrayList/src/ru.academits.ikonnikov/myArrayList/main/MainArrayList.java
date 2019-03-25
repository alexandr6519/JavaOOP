package ru.academits.ikonnikov.myArrayList.main;

import ru.academits.ikonnikov.myArrayList.classes.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MainArrayList {
    public static void main(String[] args) {
        MyArrayList<String> myListStringsEmpty = new MyArrayList<>(0);
        System.out.println("The myListStringsEmpty is: " + myListStringsEmpty.toString());
        System.out.println("The length of myListStringsEmpty.toArray()is: " + myListStringsEmpty.toArray().length);
        myListStringsEmpty.clear();
        System.out.println("The myListStringsEmpty after clearing is: " + myListStringsEmpty.toString());
        myListStringsEmpty.ensureCapacity(10);
        System.out.println("The myListStringsEmpty is: " + myListStringsEmpty.toString());
        System.out.println();

        Integer[] arrayIntegers = {0, 1, 2, null, 4, 5, 6};
        MyArrayList<Integer> myListIntegers = new MyArrayList<>(arrayIntegers);

        Iterator iteratorInt = myListIntegers.iterator();
        System.out.print("The items of myListIntegers:   ");

        while (iteratorInt.hasNext()) {
            System.out.print(iteratorInt.next() + "   ");
        }
        System.out.println();

        System.out.println("The result of adding data '7' to myListIntegers is : " + myListIntegers.add(7));
        System.out.println("The myListIntegers after adding data '7' is : " + myListIntegers.toString());

        System.out.println("The pastData of setting data '7' to myListIntegers by index '1' is: " + myListIntegers.set(1, 7));
        System.out.println("The myListIntegers after setting data '7' by index '1' is : " + myListIntegers.toString());
        System.out.println("The item of myListIntegers by index '1' is: " + myListIntegers.get(1));

        System.out.println("The result of method 'indexOf(1)' of myListIntegers is: " + myListIntegers.indexOf(1));
        System.out.println("The result of method 'indexOf(null)' of myListIntegers is: " + myListIntegers.indexOf(null));
        System.out.println("The result of method 'indexOf(7)' of myListIntegers is: " + myListIntegers.indexOf(7));
        System.out.println("The result of method 'lastIndexOf(7)' of myListIntegers is: " + myListIntegers.lastIndexOf(7));
        System.out.println("The result of method 'lastIndexOf(1)' of myListIntegers is: " + myListIntegers.lastIndexOf(1));
        System.out.println("The result of method 'lastIndexOf(0)' of myListIntegers is: " + myListIntegers.lastIndexOf(0));

        Integer[] arrayForIntegers = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        Integer[] arrayListToArrayInt = myListIntegers.toArray(arrayForIntegers);

        System.out.print("The array of method 'myListIntegers.toArray(arrayForIntegers)' is : (");

        for (int i = 0; i < arrayListToArrayInt.length - 1; i++) {
            System.out.print(arrayListToArrayInt[i] + ", ");
        }
        System.out.println(arrayListToArrayInt[arrayListToArrayInt.length - 1] + ")");
        System.out.println();

        String[] arrayStrings = {"a", "b", "null", "d", "e"};
        MyArrayList<String> myListStrings = new MyArrayList<>(arrayStrings);
        System.out.println("The myListStrings  is : " + myListStrings.toString());
        System.out.println("The pastData of setting data 'k' to myListStrings  by index '1' is: " + myListStrings.set(1, "k"));
        System.out.println("The item of myListStrings by index '2' is: " + myListStrings.get(2));

        myListStrings.add(2, "f");
        //System.out.println("The result of adding data 'f' to myListStrings  is : " + myListStrings("f"));
        System.out.println("The myListStrings after adding data 'f' by index '2' is : " + myListStrings.toString());

        List<String> collection1 = new ArrayList<>(Arrays.asList("s", "q", "p"));
        System.out.println("The result of adding collection {'s','q','p'} to myListStrings is : " + myListStrings.addAll(collection1));
        System.out.println("The myListStrings  after adding collection {'s','q','p'} is : " + myListStrings.toString());

        List<String> collection2 = new ArrayList<>(Arrays.asList("4", "8"));

        System.out.println("The result of adding collection {'4','8'} to myListStrings by index '3' is : " + myListStrings.addAll(3, collection2));
        System.out.println("The myListStrings after adding collection {'4','8'} is : " + myListStrings.toString());

        System.out.println("The removed data from myListStrings  by index '1' is: " + myListStrings.remove(1));
        System.out.println("The result of removing of object 'f' from myListStrings   is: " + myListStrings.remove("f"));
        System.out.println("The myListStrings after removing of object 'f' and data by index '1' is : " + myListStrings.toString());

        System.out.println("The result of removing collection {'4','8'} from myListStrings  is : " + myListStrings.removeAll(collection2));
        System.out.println("The myListStrings  after removing collection {'4','8'} is : " + myListStrings.toString());

        Object[] arrayListToArrayStr = myListStrings.toArray();
        System.out.print("The array by method 'myListStrings.toArray()' is : (");

        for (int i = 0; i < arrayListToArrayStr.length - 1; i++) {
                System.out.print(arrayListToArrayStr[i] + ", ");
        }
        System.out.println(arrayListToArrayStr[arrayListToArrayStr.length - 1] + ")");

        System.out.println("The result of adding data 'q' to myListStrings  is :  " + myListStrings.add("q"));
        System.out.println("The myListStrings  is : " + myListStrings.toString());

        if (myListStrings.containsAll(collection1)) {
            System.out.println("The myListStrings has collection {'s','q','p'}.");
        } else {
            System.out.println("The myListStrings has not collection {'s','q','p'}.");
        }

        if (myListStrings.containsAll(collection2)) {
            System.out.println("The myListStrings has collection {'4','8'}." );
        } else {
            System.out.println("The myListStrings has not collection {'4','8'}." );
        }
        System.out.println("The result of using method 'retainAll' to myListStrings by collection {'s','q','p'} is : " + myListStrings.retainAll(collection1));
        System.out.println("The myListStrings after using method 'retainAll' by collection {'s','q','p'} is : " + myListStrings.toString());
        System.out.println();

        MyArrayList<String> myListStringsCopy = new MyArrayList<>(arrayStrings);
        System.out.println("The myListStringCopy is : " + myListStringsCopy.toString());
        myListStringsCopy.clear();
        System.out.println("The myListStringsCopy result after clearing is : " + myListStringsCopy.toString());

        if (myListStringsCopy.isEmpty()) {
            System.out.println("Now myListStringsCopy is empty.");
        } else {
            System.out.println("Now myListStringsCopy is not empty.");
        }
        System.out.println();

        List<Short> collection3 = new ArrayList<>(Arrays.asList((short)14, (short)16, (short)18));
        List<Short> collection4 = new ArrayList<>(Arrays.asList((short)11, (short)16, (short)20));
        MyArrayList<Short> myListShorts = new MyArrayList<>(10);
        System.out.println("The size of myListShorts = " + myListShorts.size());
        myListShorts.add(0, (short) 11);
        myListShorts.add(0, (short) 22);
        myListShorts.add(1, (short) 33);
        myListShorts.addAll(3, collection3);
        System.out.println("The myListShorts after adding items and collection is : " + myListShorts.toString());
        System.out.println("The item of myListShorts by index '5' is: " + myListShorts.get(5));

        if (myListShorts.contains((short) 12)) {
            System.out.println("The myListShorts has item '12'.");
        } else {
            System.out.println("The myListShorts has not item '12'.");
        }

        if (myListShorts.contains((short) 11)) {
            System.out.println("The myListShorts has item '11'.");
        } else {
            System.out.println("The myListShorts has not item '11'.");
        }

        if (myListShorts.retainAll(collection4)) {
            System.out.println("The myListShorts after using method 'retainAll' by collection {'11','16','20'} is : " + myListShorts.toString());
        }
    }
}
