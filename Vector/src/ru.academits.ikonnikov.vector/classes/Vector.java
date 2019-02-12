package ru.academits.ikonnikov.vector.classes;

import java.lang.IllegalArgumentException;

public class Vector {
    private int n;
    public double[] array;
    private final double EPSILON = 1.0e-10;

    public Vector(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("The parameter 'n' must be > 0!");
        }
        this.n = n;
        this.array = new double[n];

        for (int i = 0; i < n; i++) {
            this.array[i] = 0.0;
        }
    }

    public Vector(Vector vector) {
        if (vector.n < 1) {
            throw new IllegalArgumentException("The parameter 'n' must be > 0!");
        }

        this.n = vector.n;
        this.array = vector.array;
    }

    public Vector(double[] array) {
        if (array.length < 1) {
            throw new IllegalArgumentException("This array of doubles is empty!");
        }

        this.n = array.length;
        this.array = array;
    }

    public Vector(int n, double[] array) {
        if (n < 1) {
            throw new IllegalArgumentException("The parameter 'n' must be > 0!");
        }

        if (array.length < 1) {
            throw new IllegalArgumentException("This array of doubles is empty!");
        }

        this.n = n;
        this.array = new double[n];
        for (int i = 0; i < this.n; i++) {
            if (i < array.length) {
                this.array[i] = array[i];
            } else {
                this.array[i] = 0;
            }
        }
    }

    public int getSize() {
        return this.n;
    }

    public Vector add(Vector vector) {
        if (this.n < 1 || vector.n < 1) {
            throw new IllegalArgumentException("The parameter 'n' must be > 0!");
        }

        int m = vector.array.length;
        int maxSize = m > this.n ? m : this.n;
        Vector resultVector = new Vector(maxSize);

        if (m >= this.n) {
            for (int i = 0; i < m; i++) {
                if (i < this.n) {
                    resultVector.array[i] = this.array[i] + vector.array[i];
                } else {
                    resultVector.array[i] = vector.array[i];
                }
            }
        } else {
            for (int i = 0; i < this.n; i++) {
                if (i < m) {
                    resultVector.array[i] = this.array[i] + vector.array[i];
                } else {
                    resultVector.array[i] = this.array[i];
                }
            }
        }
        return resultVector;
    }

    public Vector subtract(Vector vector) {
        if (this.n < 1 || vector.n < 1) {
            throw new IllegalArgumentException("The parameter 'n' must be > 0!");
        }

        int m = vector.array.length;
        int maxSize = m > this.n ? m : this.n;
        Vector resultVector = new Vector(maxSize);

        if (m >= this.n) {
            for (int i = 0; i < m; i++) {
                if (i < this.n) {
                    resultVector.array[i] = this.array[i] - vector.array[i];
                } else {
                    resultVector.array[i] = -vector.array[i];
                }
            }
        } else {
            for (int i = 0; i < this.n; i++) {
                if (i < m) {
                    resultVector.array[i] = this.array[i] - vector.array[i];
                } else {
                    resultVector.array[i] = this.array[i];
                }
            }
        }
        return resultVector;
    }

    public Vector multiplyByScalar(double scalar) {
        if (this.n < 1) {
            throw new IllegalArgumentException("The parameter 'n' must be > 0!");
        }
        Vector resultVector = new Vector(this.n);

        for (int i = 0; i < this.n; i++) {
            if (Math.abs(this.array[i] * scalar) < EPSILON) {
                resultVector.array[i] = 0;
            } else {
                resultVector.array[i] = this.array[i] * scalar;
            }
        }

        return resultVector;
    }

    public Vector turn() {
        if (this.n < 1) {
            throw new IllegalArgumentException("The parameter 'n' must be > 0!");
        }
        Vector resultVector = new Vector(this.n);

        for (int i = 0; i < this.n; i++) {
            if (Math.abs(this.array[i]) > EPSILON) {
                resultVector.array[i] = -this.array[i];
            } else {
                resultVector.array[i] = 0.0;
            }
        }
        return resultVector;
    }

    public double getLength() {
        if (this.n < 1) {
            throw new IllegalArgumentException("The parameter 'n' must be > 0!");
        }
        double sum = 0;

        for (int i = 0; i < this.n; i++) {
            sum += this.array[i] * this.array[i];
        }
        return Math.sqrt(sum);
    }

    public double getComponent(int i) {
        if (this.n < 1) {
            throw new IllegalArgumentException("The parameter 'n' must be > 0!");
        }
        return this.array[i];
    }

    public void setComponent(int i, double component) {
        this.array[i] = component;
    }

    public static Vector add(Vector vector1, Vector vector2) {
        if (vector1.n < 1 || vector2.n < 1) {
            throw new IllegalArgumentException("The parameters 'n' must be > 0!");
        }

        int m = vector1.array.length;
        int n = vector2.array.length;
        int maxSize = m > n ? m : n;
        Vector resultVector = new Vector(maxSize);

        if (m >= n) {
            for (int i = 0; i < m; i++) {
                if (i < n) {
                    resultVector.array[i] = vector1.array[i] + vector2.array[i];
                } else {
                    resultVector.array[i] = vector1.array[i];
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (i < m) {
                    resultVector.array[i] = vector1.array[i] + vector2.array[i];
                } else {
                    resultVector.array[i] = vector2.array[i];
                }
            }
        }
        return resultVector;
    }

    public static Vector subtract(Vector vector1, Vector vector2) {
        if (vector1.n < 1 || vector2.n < 1) {
            throw new IllegalArgumentException("The parameters 'n' must be > 0!");
        }

        int m = vector1.array.length;
        int n = vector2.array.length;
        int maxSize = m > n ? m : n;
        Vector resultVector = new Vector(maxSize);

        if (m >= n) {
            for (int i = 0; i < m; i++) {
                if (i < n) {
                    resultVector.array[i] = vector1.array[i] - vector2.array[i];
                } else {
                    resultVector.array[i] = vector1.array[i];
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (i < m) {
                    resultVector.array[i] = vector1.array[i] - vector2.array[i];
                } else {
                    resultVector.array[i] = -vector2.array[i];
                }
            }
        }
        return resultVector;
    }

    public static double multiplyScalar(Vector vector1, Vector vector2) {
        if (vector1.n < 1 || vector2.n < 1) {
            throw new IllegalArgumentException("The parameters 'n' must be > 0!");
        }

        int m = vector1.array.length;
        int n = vector2.array.length;
        int minSize = m <= n ? m : n;
        double result = 0;

        for (int i = 0; i < minSize; i++) {
            result += vector1.array[i] * vector2.array[i];
        }
        return result;
    }

    @Override
    public String toString() {
        if (this.n < 1) {
            throw new IllegalArgumentException("The parameter 'n' must be > 0!");
        }
        String result = "{";
        for (int i = 0; i < this.n - 1; i++) {
            String str = String.format(" %.2f ;", this.array[i]);
            result = result.concat(str);
        }
        return result.concat(String.format(" %.2f }", this.array[this.n - 1]));
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

        if (vector.n != this.n) {
            return false;
        } else {
            for (int i = 0; i < n; i++) {
                if (Math.abs(this.array[i] - vector.array[i]) > EPSILON) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        if (this.n < 1) {
            throw new IllegalArgumentException("The parameter 'n' must be > 0!");
        }
        final int prime = 13;
        int hash = 1;

        for (int i = 0; i < this.n; i++) {
            hash = prime * hash + Double.hashCode(this.array[i]);
        }
        return hash;
    }
}
