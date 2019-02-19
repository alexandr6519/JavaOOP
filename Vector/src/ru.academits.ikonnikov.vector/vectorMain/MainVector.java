package ru.academits.ikonnikov.vector.vectorMain;

import ru.academits.ikonnikov.vector.classes.Vector;

import static ru.academits.ikonnikov.vector.classes.Vector.add;
import static ru.academits.ikonnikov.vector.classes.Vector.multiplyScalar;
import static ru.academits.ikonnikov.vector.classes.Vector.subtract;

public class MainVector {
    public static void main(String[] args) throws RuntimeException {
        double[] array1 = {11.0, 2.5, 5.1};
        Vector v1 = new Vector(array1);
        System.out.println("Vector v1 : " + v1.toString());
        System.out.printf("   The length of vector v1 = %.2f %n", v1.getLength());

        double[] array2 = {-1.0, -4.2, 2.2, 5.6};
        Vector v2 = new Vector(array2);
        System.out.println("Vector v2 : " + v2.toString());
        System.out.printf("   The length of vector v2 = %.2f %n", v2.getLength());

        Vector v3 = new Vector(5, array2);
        System.out.println("Vector v3 : " + v3.toString());
        System.out.println("     Size of vector v3 = " + v3.getSize());

        System.out.println("The result of turn of vector v3 : " + v3.turn().toString());

        double[] array3 = {1.0, 2.5};
        Vector v4 = new Vector(array3);
        System.out.println("Vector v4 : " + v4.toString());

        v4.setComponent(0, -2.5);
        v4.setComponent(1, 1.3);
        System.out.println(" After changing of values vector v4 : " + v4.toString());

        Vector v5 = new Vector(v3);
        System.out.printf("Vector v5 is copy of v3 : %s %n ", v5.toString());

        System.out.printf("The result of addition of vectors v1 and v2 by nonstatic method is: %s %n", v1.add(v2).toString());

        System.out.printf("The result of addition of vectors v1 and v2 by static method is: %s %n", add(v1, v2).toString());

        System.out.printf("The result of subtraction of vector v2 from v1 by nonstatic method is: %s %n", v1.subtract(v2).toString());

        System.out.printf("The result of subtraction of vector v2 from v1 by static method is: %s %n", subtract(v1, v2).toString());

        System.out.printf("The result of multiplication of vector v2 by scalar (3,30) is: %s %n", v2.multiplyByScalar(3.3).toString());
        System.out.printf("The result of multiplication of vector v2 by scalar (-0,50) is: %s %n", v2.multiplyByScalar(-0.5).toString());

        System.out.printf("The result of scalar multiplication of vectors v2 and v3 = %.2f %n", multiplyScalar(v2, v3));

        System.out.printf("The result of comparing vectors v2 and v4 is: %s %n", v4.equals(v2));
        System.out.printf("The result of comparing vectors v3 and v5 is: %s %n", v3.equals(v5));

        System.out.printf("  The first component of vector v4 = %.2f %n  The second component of vector v4 = %.2f %n", v4.getComponent(0), v4.getComponent(1));

        System.out.printf("The hashCode of vector v2 = %d %n", v2.hashCode());
        System.out.printf("The hashCode of vector v4 = %d %n", v4.hashCode());
    }
}

