package ru.academits.ikonnikov.matrix.classes;

class MatrixDeterminant {
    private static double[][] getMinor(double[][] array, int index) {
        double[][] minor = new double[array.length - 1][array.length - 1];

        for (int j = 0; j < array.length; j++) {
            for (int k = 0; k < array.length; k++) {
                if (index != k && index != j) {
                    if (index > j) {
                        if (index > k) {
                            minor[k][j] = array[k][j + 1];
                        } else {
                            minor[k - 1][j] = array[k][j + 1];
                        }
                    } else {
                        if (index < k) {
                            minor[k - 1][j - 1] = array[k][j];
                        } else {
                            minor[k][j - 1] = array[k][j];
                        }
                    }
                }
            }
        }
        return minor;
    }

    static double getMatrixDeterminant(double[][] array) {
        int matrixSize = array.length;

        if (matrixSize == 1) {
            return array[0][0];
        }

        if (matrixSize == 2) {
            return (array[0][0] * array[1][1] - array[0][1] * array[1][0]);
        }

        double matrixDeterminant = 0;

        for (int i = 0; i < matrixSize; i++) {
            matrixDeterminant += array[i][0] * Math.pow(-1, i) * getMatrixDeterminant(getMinor(array, i));
        }
        return matrixDeterminant;
    }
}

