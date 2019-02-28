package ru.academits.ikonnikov.vector.classes;

import java.lang.IllegalArgumentException;
import java.lang.IndexOutOfBoundsException;
import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int vectorSize) {
        if (vectorSize < 1) {
            throw new IllegalArgumentException("The parameter 'vectorSize' must be > 0!");
        }

        this.components = new double[vectorSize];
    }

    public Vector(Vector vector) {
        this.components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("The length of array must be > 0!");
        }

        this.components = Arrays.copyOf(array, array.length);
    }

    public Vector(int vectorSize, double[] array) {
        if (vectorSize < 1) {
            throw new IllegalArgumentException("The parameter 'vectorSize' must be > 0!");
        }

        this.components = Arrays.copyOf(array, vectorSize);
    }

    public int getSize() {
        return this.components.length;
    }

    public Vector add(Vector vector) {
        if (vector.components.length > this.components.length) {
            this.components = Arrays.copyOf(this.components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            this.components[i] += vector.components[i];
        }

        return this;
    }

    public Vector subtract(Vector vector) {
        if (vector.components.length > this.components.length) {
            this.components = Arrays.copyOf(this.components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            this.components[i] -= vector.components[i];
        }

        return this;
    }

    public Vector multiplyByScalar(double scalar) {
        for (int i = 0; i < this.components.length; i++) {
            this.components[i] *= scalar;
        }
        return this;
    }

    public Vector turn() {
        return this.multiplyByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double item : this.components) {
            sum += item * item;
        }
        return Math.sqrt(sum);
    }

    public double getComponent(int index) {
        if (index < 0 || index >= this.components.length) {
            throw new IndexOutOfBoundsException("The index of array isn't correct!");
        }
        return this.components[index];
    }

    public void setComponent(int index, double arrayComponent) {
        if (index < 0 || index >= this.components.length) {
            throw new IndexOutOfBoundsException("The index of array isn't correct!");
        }
        this.components[index] = arrayComponent;
    }

    public static Vector add(Vector vector1, Vector vector2) {
        Vector vectorCopy = new Vector(vector1);
        return vectorCopy.add(vector2);
    }

    public static Vector subtract(Vector vector1, Vector vector2) {
        Vector vectorCopy = new Vector(vector1);
        return vectorCopy.subtract(vector2);
    }

    public static double multiplyScalar(Vector vector1, Vector vector2) {
        int minSize = Math.min(vector1.components.length, vector2.components.length);
        double result = 0;

        for (int i = 0; i < minSize; i++) {
            result += vector1.components[i] * vector2.components[i];
        }
        return result;
    }

    @Override
    public String toString() {
        int arrayLength = this.components.length;
        StringBuilder result = new StringBuilder("{");

        for (int i = 0; i < (arrayLength - 1); i++) {
            String str = String.format(" %.2f ,", this.components[i]);
            result.append(str);
        }
        String str = String.format(" %.2f }", this.components[arrayLength - 1]);
        return result.append(str).toString();
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

        if (vector.components.length != this.components.length) {
            return false;
        }

        for (int i = 0; i < this.components.length; i++) {
            if (this.components[i] != vector.components[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.components);
    }
}



