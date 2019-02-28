package ru.academits.ikonnikov.matrix.classes;

import ru.academits.ikonnikov.vector.classes.Vector;

import java.lang.IllegalArgumentException;
import java.lang.IndexOutOfBoundsException;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsNumber, int columnsNumber) {
        if (rowsNumber < 1 || columnsNumber < 1) {
            throw new IllegalArgumentException("The parameters 'rowsNumber' and 'columnsNumber' must be > 0!");
        }

        this.rows = new Vector[rowsNumber];

        for (int i = 0; i < rowsNumber; i++) {
            this.rows[i] = new Vector(columnsNumber);
        }
    }

    public Matrix(Matrix matrix) {
        this.rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; i++) {
            this.rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        boolean isEmpty = false;
        int maxSize = 0;

        if (array.length == 0) {
            isEmpty = true;
        } else {
            for (double[] doubles : array) {
                if (doubles.length > maxSize) {
                    maxSize = doubles.length;
                }
            }
        }

        if (isEmpty || maxSize == 0) {
            throw new IllegalArgumentException("This array is empty!");
        }

        this.rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            this.rows[i] = new Vector(maxSize, array[i]);
        }
    }

    private static double[] getArray(Vector vector) {
        double[] array = new double[vector.getSize()];

        for (int i = 0; i < vector.getSize(); i++) {
            array[i] = vector.getComponent(i);
        }
        return array;
    }

    public Matrix(Vector[] rows) {
        if (rows.length < 1) {
            throw new IllegalArgumentException("The length of array must be > 0!");
        }
        this.rows = new Vector[rows.length];

        int maxSize = rows[0].getSize();

        for (int i = 1; i < rows.length; i++) {
            if (rows[i].getSize() > maxSize) {
                maxSize = rows[i].getSize();
            }
        }

        for (int i = 0; i < rows.length; i++) {
            this.rows[i] = new Vector(maxSize, getArray(rows[i]));
        }
    }

    public int getRowsNumber() {
        return this.rows.length;
    }

    public int getColumnsNumber() {
        return this.rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= this.rows.length) {
            throw new IndexOutOfBoundsException("The index is not correct!");
        }

        return new Vector(this.rows[index]);
    }

    public void setRow(int index, Vector row) {
        if (index < 0 || index >= this.rows.length) {
            throw new IndexOutOfBoundsException("The index is not correct!");
        }

        if (this.getColumnsNumber() != row.getSize()) {
            throw new IllegalArgumentException("The size of component is not correct!");
        }

        this.rows[index] = new Vector(row);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= this.getColumnsNumber()) {
            throw new IndexOutOfBoundsException("The index is not correct!");
        }

        Vector vectorColumn = new Vector(this.rows.length);

        for (int i = 0; i < this.rows.length; i++) {
            vectorColumn.setComponent(i, this.rows[i].getComponent(index));
        }
        return vectorColumn;
    }

    public Matrix add(Matrix matrix) {
        if (this.rows.length != matrix.rows.length || this.getColumnsNumber() != matrix.getColumnsNumber()) {
            throw new IllegalArgumentException("The addition is impossible, because matrices have not the same dimension!");
        }

        for (int i = 0; i < this.rows.length; i++) {
            this.rows[i].add(matrix.rows[i]);
        }

        return this;
    }

    public Matrix subtract(Matrix matrix) {
        if (this.rows.length != matrix.rows.length || this.getColumnsNumber() != matrix.getColumnsNumber()) {
            throw new IllegalArgumentException("The subtraction is impossible, because matrices have not the same dimension!");
        }

        for (int i = 0; i < this.rows.length; i++) {
            this.rows[i].subtract(matrix.rows[i]);
        }

        return this;
    }

    public Matrix multiplyByScalar(double scalar) {
        for (Vector vector : this.rows) {
            vector.multiplyByScalar(scalar);
        }
        return this;
    }

    public Matrix transpose() {
        int columnsNumber = this.getColumnsNumber();
        Vector[] columns = new Vector[columnsNumber];

        for (int i = 0; i < columnsNumber; i++) {
            columns[i] = this.getColumn(i);
        }

        this.rows = columns;

        return this;
    }

    public double calculateDeterminant() {
        if (this.getRowsNumber() != this.getColumnsNumber()) {
            throw new IllegalArgumentException("The number of columns must be equal to the number of rows!");
        }
        double[][] array = new double[this.rows.length][this.rows.length];

        for (int i = 0; i < this.rows.length; i++) {
            for (int j = 0; j < this.rows.length; j++) {
                array[i][j] = this.rows[i].getComponent(j);
            }
        }
        return MatrixDeterminant.getMatrixDeterminant(array);
    }

    public static Matrix add(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsNumber() != matrix2.getColumnsNumber()) {
            throw new IllegalArgumentException(" addition is impossible, because matrices have not the same dimension!");
        }

        Matrix matrixCopy = new Matrix(matrix1);
        return matrixCopy.add(matrix2);
    }

    public static Matrix subtract(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsNumber() != matrix2.getColumnsNumber()) {
            throw new IllegalArgumentException("The subtraction is impossible, because matrices have not the same dimension!");
        }

        Matrix matrixCopy = new Matrix(matrix1);
        return matrixCopy.subtract(matrix2);
    }

    public Vector multiplyByVector(Vector vector) {
        if (this.getColumnsNumber() != vector.getSize()) {
            throw new IllegalArgumentException("The multiplication is impossible, because size of vector is not equal to the number of columns of matrix");
        }

        Vector resultVector = new Vector(this.rows.length);

        for (int i = 0; i < this.rows.length; i++) {
            resultVector.setComponent(i, Vector.multiplyScalar(vector, this.rows[i]));
        }
        return resultVector;
    }

    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsNumber() != matrix2.rows.length) {
            throw new IllegalArgumentException("The multiplication is impossible, because number of columns of the first matrix  is not equal to the number of rows of the second matrix!");
        }

        Matrix resultMatrix = new Matrix(matrix1.rows.length, matrix2.getColumnsNumber());

        for (int i = 0; i < matrix1.rows.length; i++) {
            for (int j = 0; j < matrix2.getColumnsNumber(); j++) {
                Vector vectorRow = matrix1.rows[i];
                Vector vectorColumn = matrix2.getColumn(j);
                resultMatrix.rows[i].setComponent(j, Vector.multiplyScalar(vectorRow, vectorColumn));
            }
        }
        return resultMatrix;
    }

    @Override
    public String toString() {
        int rowsNumber = this.rows.length;
        StringBuilder result = new StringBuilder("{");

        for (int i = 0; i < (rowsNumber - 1); i++) {
            String str = this.rows[i].toString();
            result.append(str).append(", ");
        }

        String str = this.rows[rowsNumber - 1].toString();
        return result.append(str).append("}").toString();
    }
}
