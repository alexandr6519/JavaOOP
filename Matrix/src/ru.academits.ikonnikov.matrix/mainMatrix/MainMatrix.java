package ru.academits.ikonnikov.matrix.mainMatrix;

import ru.academits.ikonnikov.matrix.classes.Matrix;

public class MainMatrix {
    public static void main(String[] args) throws RuntimeException {
        Matrix m1 = new Matrix(3, 3);

        for (int i = 0; i < m1.getRowsNumber(); i++) {
            double[] array = new double[m1.getColumnsNumber()];
            for (int j = 0; j < m1.getColumnsNumber(); j++) {
                array[j] = (i + 1) * (j + 1);
            }
            m1.setRow(i, new Vector(array));
        }

        System.out.println("Matrix m1 : " + m1.toString());

        double[][] array1 = {{11.0, 2.5, 5.1}, {-1.2, 3.6, 8.4}};
        Matrix m2 = new Matrix(array1);
        System.out.println("Matrix m2 : " + m2.toString());
        System.out.printf("The determinant of matrix m2 = %.2f %n", m2.calculateDeterminant());
        System.out.println("The second row of matrix m2 : " + m2.getRow(1).toString());
        System.out.println("The second column of matrix m2 : " + m2.getColumn(1).toString());

        double[][] array2 = {{-1.0, -4.2, 2.2, 5.6}, {3.2, 5.9, -4.8, 21.0}, {7.1, 5.0, 9.6, -4.5}};
        Matrix m3 = new Matrix(array2);
        System.out.println("Matrix m3 : " + m3.toString());
        System.out.printf("The determinant of matrix m3 = %.2f %n", m3.calculateDeterminant());

        Matrix m4 = new Matrix(m2);
        System.out.println("Matrix m4 is copy of m2 : " + m4.toString());
        System.out.printf("Size of matrix m4 : %d x %d %n", m4.getRowsNumber(), m4.getColumnsNumber());

        System.out.println("The result of transposition of matrix m4 : " + m4.transpose().toString());

        double[][] array3 = {{1.0, 2.5}, {6.6, -0.4}, {-11.3, 18.6}};
        Matrix m5 = new Matrix(array3);
        System.out.println("Matrix m5 : " + m5.toString());

        double[] arrayV1 = {13.2, -4.5, 2.1};
        double[] arrayV2 = {3.7, 5.2, -2.3};
        double[] arrayV3 = {0.3, 3.2, 12.1};
        Vector v1 = new Vector(arrayV1);
        Vector v2 = new Vector(arrayV2);
        Vector v3 = new Vector(arrayV3);
        Vector[] vectors = {v1, v2, v3};
        Matrix m6 = new Matrix(vectors);
        System.out.println("Matrix m6 : " + m6.toString());

        m2.setRow(0, v2);
        m2.setRow(1, v3);
        System.out.println(" After changing of values matrix m2 : " + m2.toString());

        System.out.println("The result of addition of matrices m1 and m6 by nonstatic method is: " + m1.add(m6).toString());

        System.out.println("The result of addition of matrices m5 and m4 by nonstatic method is: " + m5.add(m4).toString());

        System.out.println("The result of addition of matrices m1 and m6 by static method is: " + (ru.academits.ikonnikov.matrix.classes.Matrix.add(m1, m6)).toString());

        System.out.println("The result of subtraction of matrix m6 from matrix m1 by nonstatic method is: " + m1.subtract(m6).toString());

        System.out.println("The result of subtraction of matrix m4 from matrix m5 by nonstatic method is: " + m5.subtract(m4).toString());

        System.out.println("The result of subtraction of matrix m4 from matrix m5 by static method is: " + (ru.academits.ikonnikov.matrix.classes.Matrix.subtract(m5, m4)).toString());

        System.out.println("The result of multiplication of matrix m3 by scalar (-1.3) is: " + m3.multiplyByScalar(-1.3).toString());

        System.out.println("The result of multiplication of matrix m2 by scalar (3.5) is: " + m2.multiplyByScalar(3.5).toString());

        System.out.printf("The result of multiplication of matrix m6 by vector v2  = %s %n", m6.multiplyByVector(v2).toString());

        System.out.printf("The result of multiplication of matrix m2 on matrix m5  = %s %n", (ru.academits.ikonnikov.matrix.classes.Matrix.multiply(m2, m5)).toString());

        System.out.printf("The result of multiplication of matrix m1 on matrix m5  = %s %n", (ru.academits.ikonnikov.matrix.classes.Matrix.multiply(m1, m5)).toString());

        System.out.printf("The result of multiplication of matrix m6 on matrix m3  = %s %n", (ru.academits.ikonnikov.matrix.classes.Matrix.multiply(m6, m3)).toString());

        System.out.println("The result of transposition of matrix m4 : " + m4.transpose().toString());
    }
}
