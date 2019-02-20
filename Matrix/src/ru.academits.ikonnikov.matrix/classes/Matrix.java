package ru.academits.ikonnikov.matrix.classes;

import java.lang.IllegalArgumentException;
import java.lang.IndexOutOfBoundsException;
import java.util.Arrays;

import ru.academits.ikonnikov.vector.classes.Vector;

import static ru.academits.ikonnikov.matrix.classes.MatrixDeterminant.getMatrixDeterminant;
import static ru.academits.ikonnikov.vector.classes.Vector.multiplyScalar;

public class Matrix {
    private Vector[] vectors;

    public Matrix(int rowsNumber, int columnsNumber) {
        if (rowsNumber < 1 || columnsNumber < 1) {
            throw new IllegalArgumentException("The parameters 'rowsNumber' and 'columnsNumber' must be > 0!");
        }

        this.vectors = new Vector[rowsNumber];

        for (int i = 0; i < rowsNumber; i++) {
            this.vectors[i] = new Vector(columnsNumber);
        }
    }

    public Matrix(Matrix matrix) {
        this.vectors = new Vector[matrix.vectors.length];

        for (int i = 0; i < matrix.vectors.length; i++) {
            this.vectors[i] = new Vector(matrix.vectors[i].components);
        }
    }

    public Matrix(double[][] array) {
        this.vectors = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            this.vectors[i] = new Vector(array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        this.vectors = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            this.vectors[i] = new Vector(vectors[i]);
        }
    }

    public int getRowsNumber() {
        return this.vectors.length;
    }

    public int getColumnsNumber() {
        return this.vectors[0].getSize();
    }

    public Vector getRowVector(int index) {
        if (index < 0 || index > this.vectors.length) {
            throw new IndexOutOfBoundsException("The index is not correct!");
        }

        return this.vectors[index];
    }

    public void setVectorRow(int index, Vector component) {
        if (index < 0 || index > this.vectors.length) {
            throw new IndexOutOfBoundsException("The index is not correct!");
        }

        if (this.vectors[0].getSize() != component.getSize()) {
            throw new IllegalArgumentException("The size of component is not correct!");
        }

        this.vectors[index] = component;
    }

    public Vector getColumnVector(int index) {
        if (index < 0 || index > this.vectors[0].getSize()) {
            throw new IndexOutOfBoundsException("The index is not correct!");
        }

        Vector vectorColumn = new Vector(this.vectors.length);

        for (int i = 0; i < this.vectors.length; i++) {
            vectorColumn.components[i] = this.vectors[i].components[index];
        }
        return vectorColumn;
    }

    public Matrix add(Matrix matrix) {
        if (this.vectors.length != matrix.vectors.length || this.vectors[0].getSize() != matrix.vectors[0].getSize()) {
            throw new IllegalArgumentException("The addition is impossible, because matrices have not the same dimension!");
        }

        for (int i = 0; i < this.vectors.length; i++) {
            this.vectors[i].add(matrix.vectors[i]);
        }

        return this;
    }

    public Matrix subtract(Matrix matrix) {
        if (this.vectors.length != matrix.vectors.length || this.vectors[0].getSize() != matrix.vectors[0].getSize()) {
            throw new IllegalArgumentException("The subtraction is impossible, because matrices have not the same dimension!");
        }

        for (int i = 0; i < this.vectors.length; i++) {
            this.vectors[i].subtract(matrix.vectors[i]);
        }

        return this;
    }

    public Matrix multiplyByScalar(double scalar) {
        for (Vector vector : this.vectors) {
            vector.multiplyByScalar(scalar);
        }
        return this;
    }

    public Matrix transpose() {
        int columnsNumber = this.vectors[0].getSize();
        Matrix resultMatrix = new Matrix(columnsNumber, this.vectors.length);

        for (int i = 0; i < columnsNumber; i++) {
            resultMatrix.vectors[i] = this.getColumnVector(i);
        }

        this.vectors = Arrays.copyOf(resultMatrix.vectors, columnsNumber);
        return this;
    }

    public double calculateDeterminant() {
        double[][] array = new double[this.vectors.length][this.vectors[0].getSize()];

        for (int i = 0; i < this.vectors.length; i++) {
            array[i] = Arrays.copyOf(this.vectors[i].components, this.vectors[0].getSize());
        }
        return getMatrixDeterminant(array);
    }

    public static Matrix add(Matrix matrix1, Matrix matrix2) {
        if (matrix1.vectors.length != matrix2.vectors.length || matrix1.vectors[0].getSize() != matrix2.vectors[0].getSize()) {
            throw new IllegalArgumentException("The addition is impossible, because matrices have not the same dimension!");
        }

        Matrix matrixCopy = new Matrix(matrix1);
        return matrixCopy.add(matrix2);
    }

    public static Matrix subtract(Matrix matrix1, Matrix matrix2) {
        if (matrix1.vectors.length != matrix2.vectors.length || matrix1.vectors[0].getSize() != matrix2.vectors[0].getSize()) {
            throw new IllegalArgumentException("The subtraction is impossible, because matrices have not the same dimension!");
        }

        Matrix matrixCopy = new Matrix(matrix1);
        return matrixCopy.subtract(matrix2);
    }

    public Vector multiplyByVector(Vector vector) {
        if (this.vectors[0].getSize() != vector.getSize()) {
            throw new IllegalArgumentException ("The multiplication is impossible, because size of vector is not equal to the number of columns of matrix");
        }

        Vector resultVector = new Vector(this.vectors.length);

        for (int i = 0; i < this.vectors.length; i++) {
            resultVector.components[i] = multiplyScalar(vector, this.vectors[i]);
        }
        return resultVector;
    }

    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {
        if (matrix1.vectors[0].getSize() != matrix2.vectors.length) {
            throw new IllegalArgumentException ("The multiplication is impossible, because number of columns of the first matrix  is not equal to the number of rows of the second matrix!");
        }

        Matrix resultMatrix = new Matrix(matrix1.vectors.length, matrix2.vectors[0].getSize());

        for (int i = 0; i < matrix1.vectors.length; i++) {
            for (int j = 0; j < matrix2.vectors[0].getSize(); j++) {
                Vector vectorRow = matrix1.vectors[i];
                Vector vectorColumn = matrix2.getColumnVector(j);
                resultMatrix.vectors[i].components[j] = multiplyScalar(vectorRow, vectorColumn);
            }
        }
        return resultMatrix;
    }

    @Override
    public String toString() {
        int rowsNumber = this.vectors.length;
        StringBuilder result = new StringBuilder("{");

        for (int i = 0; i < (rowsNumber - 1); i++) {
            String str = this.vectors[i].toString();
            result.append(str).append(", ");
        }

        String str = this.vectors[rowsNumber - 1].toString();
        return result.append(str).append("}").toString();
    }
}
