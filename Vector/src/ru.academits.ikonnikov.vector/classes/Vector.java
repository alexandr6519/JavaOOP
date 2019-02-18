package ru.academits.ikonnikov.vector.classes;

import java.lang.IllegalArgumentException;
import java.lang.IndexOutOfBoundsException;
import java.util.Arrays;

public class Vector {
    public double[] vectorArray;

    public Vector(int vectorSize) {
        if (vectorSize < 1) {
            throw new IllegalArgumentException("The parameter 'vectorSize' must be > 0!");
        }

        this.vectorArray = new double[vectorSize];
    }

    public Vector(Vector vector) {
        int vectorSize = vector.vectorArray.length;
        this.vectorArray = Arrays.copyOf(vector.vectorArray, vectorSize);
    }

    public Vector(double[] array) {
        int arrayLength = array.length;

        if (arrayLength == 0) {
            throw new IllegalArgumentException("The length of array must be > 0!");
        }

        this.vectorArray = Arrays.copyOf(array, arrayLength);
    }

    public Vector(int vectorSize, double[] array) {
        if (vectorSize < 1) {
            throw new IllegalArgumentException("The parameter 'vectorSize' must be > 0!");
        }

        this.vectorArray = Arrays.copyOf(array, vectorSize);;
    }

    public int getSize() {
        return this.vectorArray.length;
    }

    public Vector add(Vector vector) {
        int m = vector.vectorArray.length;
        int n = this.vectorArray.length;

        if (m >= n) {
            this.vectorArray = Arrays.copyOf(this.vectorArray, m);
        }

        for (int i = 0; i < m; i++) {
            this.vectorArray[i] += vector.vectorArray[i];
        }

        return this;
    }

    public Vector subtract(Vector vector) {
        int m = vector.vectorArray.length;
        int n = this.vectorArray.length;

        if (m >= n) {
            this.vectorArray = Arrays.copyOf(this.vectorArray, m);
        }

        for (int i = 0; i < m; i++) {
            this.vectorArray[i] -= vector.vectorArray[i];
        }

        return this;
    }

    public Vector multiplyByScalar(double scalar) {
        for (int i = 0; i < this.vectorArray.length; i++) {
            this.vectorArray[i] *= scalar;
        }
        return this;
    }

    public Vector turn() {
        return this.multiplyByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double item : this.vectorArray) {
            sum += item * item;
        }
        return Math.sqrt(sum);
    }

    public double getComponent(int index) {
        if (index < 0 || index >= this.vectorArray.length) {
            throw new IndexOutOfBoundsException("The index of array isn't correct!");
        }
        return this.vectorArray[index];
    }

    public void setComponent(int index, double arrayComponent) {
        if (index < 0 || index >= this.vectorArray.length) {
            throw new IndexOutOfBoundsException("The index of array isn't correct!");
        }
        this.vectorArray[index] = arrayComponent;
    }

    public static Vector add(Vector vector1, Vector vector2) {
        Vector vector3 = new Vector(vector1);
        return vector3.add(vector2);
    }

    public static Vector subtract(Vector vector1, Vector vector2) {
        Vector vector3 = new Vector(vector1);
        return vector3.subtract(vector2);
    }

    public static double multiplyScalar(Vector vector1, Vector vector2) {
        int minSize = Math.min(vector1.vectorArray.length, vector2.vectorArray.length);
        double result = 0;

        for (int i = 0; i < minSize; i++) {
            result += vector1.vectorArray[i] * vector2.vectorArray[i];
        }
        return result;
    }

    @Override
    public String toString() {
        int arrayLength = this.vectorArray.length;
        StringBuilder result = new StringBuilder("{");

        for (int i = 0; i < arrayLength - 1; i++) {
            String str = String.format(" %.2f ,", this.vectorArray[i]);
            result = result.append(str);
        }
        return result.append(String.format(" %.2f }", this.vectorArray[arrayLength - 1])).toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object.getClass() != this.getClass()) {
            return false;
        }

        Vector vector = (Vector) object;
        int n = this.vectorArray.length;

        if (vector.vectorArray.length != n) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            if (this.vectorArray[i] != vector.vectorArray[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.vectorArray);
    }
}
