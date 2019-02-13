package ru.academits.ikonnikov.vector.classes;

import java.lang.IllegalArgumentException;
import java.lang.IndexOutOfBoundsException;

public class Vector {
    public double[] arrayDoubles;

    public Vector(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("The parameter 'n' must be > 0!");
        }

        this.arrayDoubles = new double[n];
    }

    public Vector(Vector vector) {
        int n = vector.arrayDoubles.length;
        this.arrayDoubles = new double[n];
        System.arraycopy(vector.arrayDoubles, 0, this.arrayDoubles, 0, n);
    }

    public Vector(double[] arrayDoubles) {
        int n = arrayDoubles.length;
        this.arrayDoubles = new double[n];
        System.arraycopy(arrayDoubles, 0, this.arrayDoubles, 0, n);
    }

    public Vector(int n, double[] arrayDoubles) {
        if (n < 1) {
            throw new IllegalArgumentException("The parameter 'n' must be > 0!");
        }

        this.arrayDoubles = new double[n];
        int minSize = Math.min(n, arrayDoubles.length);
        System.arraycopy(arrayDoubles, 0, this.arrayDoubles, 0, minSize);
    }

    public int getSize() {
        return this.arrayDoubles.length;
    }

    public Vector add(Vector vector) {
        int m = vector.arrayDoubles.length;
        int n = this.arrayDoubles.length;
        int minSize = Math.min(n, m);
        int maxSize = Math.max(n, m);

        Vector v = new Vector(maxSize, this.arrayDoubles );

        for (int i = 0; i < minSize; i++) {
            v.arrayDoubles[i] = this.arrayDoubles[i] + vector.arrayDoubles[i];
        }

        if (m >= n) {
            System.arraycopy(vector.arrayDoubles, n, v.arrayDoubles, n, m - n);
        }

        this.arrayDoubles = new double[maxSize];
        System.arraycopy(v.arrayDoubles, 0, this.arrayDoubles, 0, maxSize);

        return this;
    }

    public Vector subtract(Vector vector) {
        Vector vector1 = new Vector(vector);
        return this.add(vector1.turn());
    }

    public Vector multiplyByScalar(double scalar) {
        for (int i = 0; i < this.arrayDoubles.length; i++) {
            this.arrayDoubles[i] = this.arrayDoubles[i] * scalar;
        }
        return this;
    }

    public Vector turn() {
        return this.multiplyByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double itemArrayDouble : this.arrayDoubles) {
            sum += itemArrayDouble * itemArrayDouble;
        }
        return Math.sqrt(sum);
    }

    public double getComponent(int i) {
        if (i < 0 || i >= this.arrayDoubles.length) {
            throw new IndexOutOfBoundsException("The index of array isn't correct!");
        }
        return this.arrayDoubles[i];
    }

    public void setComponent(int i, double arrayComponent) {
        if (i < 0 || i >= this.arrayDoubles.length) {
            throw new IndexOutOfBoundsException("The index of array isn't correct!");
        }
        this.arrayDoubles[i] = arrayComponent;
    }

    public static Vector add(Vector vector1, Vector vector2) {
        Vector vector3 = new Vector(vector1);
        return vector3.add(vector2);
    }

    public static Vector subtract(Vector vector1, Vector vector2) {
        Vector vector3 = new Vector(vector1);
        Vector vector4 = new Vector(vector2);
        return vector3.subtract(vector4);
    }

    public static double multiplyScalar(Vector vector1, Vector vector2) {
        int minSize = Math.min(vector1.arrayDoubles.length, vector2.arrayDoubles.length);
        double result = 0;

        for (int i = 0; i < minSize; i++) {
            result += vector1.arrayDoubles[i] * vector2.arrayDoubles[i];
        }
        return result;
    }

    @Override
    public String toString() {
        int n = this.arrayDoubles.length;
        StringBuilder result = new StringBuilder("{");

        for (int i = 0; i < n - 1; i++) {
            String str = String.format(" %.2f ;", this.arrayDoubles[i]);
            result = result.append(str);
        }
        return result.append(String.format(" %.2f }", this.arrayDoubles[n - 1])).toString();
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
        int n = this.arrayDoubles.length;

        if (vector.arrayDoubles.length != n) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            if (this.arrayDoubles[i] != vector.arrayDoubles[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 13;
        int hash = 1;

        for (double itemArrayDouble : this.arrayDoubles) {
            hash = prime * hash + Double.hashCode(itemArrayDouble);
        }
        return hash;
    }
}
