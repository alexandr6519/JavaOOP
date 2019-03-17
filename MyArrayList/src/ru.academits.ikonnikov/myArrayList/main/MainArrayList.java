package ru.acadeits.ikonnikov.myArrayList.main;

import ru.academits.ikonnikov.myArrayList.classes.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
//import java.util.Objects;

public class MainArrayList {
    public static void main(String[] args) {
        String[] arrayStrings = {"a", "b", "null", "d", "e"};
        MyArrayList<String> myListStrings = new MyArrayList<>(arrayStrings);
        System.out.println("The myListStrings  is : " + myListStrings.toString());

        Integer[] arrayIntegers = {0, 1, 2, null, 4, 5, 6};
        MyArrayList<Integer> myListIntegers = new MyArrayList<>(arrayIntegers);

        Iterator iterator = myListIntegers.iterator();
        System.out.print("The items of myListIntegers:   ");

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "   ");
        }
        System.out.println();

        System.out.println("The result of adding data '7' in myListIntegers is : " + myListIntegers.add(7));
        System.out.println("The myListIntegers after adding data '7' is : " + myListIntegers.toString());

        System.out.println("The pastData of setting data '7' in myListIntegers by index '1' is: " + myListIntegers.set(1, 7));
        System.out.println("The myListIntegers after setting data '7' by index '1' is : " + myListIntegers.toString());
        System.out.println("The result of getting by index '2' in myListIntegers is: " + myListIntegers.get(2));

        System.out.println("The pastData of setting data 'k' in myListStrings  by index '1' is: " + myListStrings.set(1, "k"));
        System.out.println("The result of getting by index '2' in myListStrings is: " + myListStrings.get(2));

        System.out.println("The result of adding data 'f' in myListStrings  is : " + myListStrings.add("f"));
        System.out.println("The myListStrings  after adding data 'f' is : " + myListStrings.toString());

        List<String> collection1 = new ArrayList<>(Arrays.asList("s", "q"));
        System.out.println("The result of adding collection {'s','q'} in myListStrings is : " + myListStrings.addAll(collection1));
        System.out.println("The myListStrings  after adding collection {'s','q'} is : " + myListStrings.toString());

        List<String> collection2 = new ArrayList<>(Arrays.asList("4", "8"));
        System.out.println("The result of adding collection {'4','8'} by index '3' in myListStrings  is : " + myListStrings.addAll(3, collection2));
        System.out.println("The myListStrings after adding collection {'4','8'} is : " + myListStrings.toString());

        System.out.println("The 'indexOf' item  '1' in myListIntegers is: " + myListIntegers.indexOf(1));
        System.out.println("The 'lastIndexOf' item '7' in myListIntegers is: " + myListIntegers.lastIndexOf(7));
        System.out.println("The removed data from myListStrings  by index '1' is: " + myListStrings.remove(1));
        System.out.println("The result of removing from myListStrings  object 'f' is: " + myListStrings.remove("f"));
        System.out.println("The myList1 after removing from myListStrings  object 'f' is : " + myListStrings.toString());

        MyArrayList<String> myListStringsCopy = new MyArrayList<>(arrayStrings);
        System.out.println("The myListStringCopy is : " + myListStringsCopy.toString());

        System.out.println("The result of removing collection {'s','q'} from myListStrings  is : " + myListStrings.removeAll(collection1));
        System.out.println("The myListStrings  after removing collection {'s','q'} is : " + myListStrings.toString());

        myListStringsCopy.clear();
        System.out.println("The myListStringsCopy result after clearing is : " + myListStringsCopy.toString());
        System.out.println("The result of checking on emptiness of myListStringsCopy  is : " + myListStringsCopy.isEmpty());

        MyArrayList<Short> myListShorts = new MyArrayList<>(5);
        System.out.println("The size of myListShorts = " + myListShorts.size());
        myListShorts.add(0, (short) 11);
        myListShorts.add(1, (short) 22);
        myListShorts.add((short) 33);
        System.out.println("The myListShorts is : " + myListShorts.toString());
        System.out.println("The result of getting by index '5' in myListShorts is: " + myListShorts.get(5));

        System.out.println("The result of checking of presence of item '11' in myListShorts by method 'contains' is : " + myListShorts.contains((short) 11));

        Object[] arrayListToArrayObject = myListIntegers.toArray();
        System.out.print("The result of method 'myListIntegers.toArray()' is : {");



        for (int i = 0; i < arrayListToArrayObject.length - 1; i++) {
                System.out.print(arrayListToArrayObject[i] + ", ");
        }
        System.out.println(arrayListToArrayObject[arrayListToArrayObject.length - 1] + "}");

        myListStrings.add(2, "q");
        System.out.println("The myListStrings  is : " + myListStrings.toString());
        System.out.println("The result of checking of presence of collection {'s','q'} in myListStrings is : " + myListStrings.containsAll(collection1));
        System.out.println("The result of checking of presence of collection {'4','8'} in myListStrings is : " + myListStrings.containsAll(collection2));

        System.out.println("The result of using method 'retainAll' by collection {'s','q'} from myListStrings  is : " + myListStrings.retainAll(collection1));
        System.out.println("The myListStrings using method 'retainAll' by collection {'s','q'} is : " + myListStrings.toString());

        Integer[] arrayForIntegers = new Integer[myListIntegers.size()];
        Integer[] arrayListToArray = myListIntegers.toArray(arrayForIntegers);

        System.out.print("The result of method 'myListIntegers.toArray(arrayForIntegers)' is : {");

        for (int i = 0; i < arrayListToArray.length - 1; i++) {
                System.out.print(arrayListToArray[i] + ", ");
        }
        System.out.println(arrayListToArray[arrayListToArray.length - 1] + "}");
    }
}
