package cache.optimisation;

import java.util.Random;

public class MatrixMultiplication {

    public static void main(String[] args) {
        int rowsA = 1000; // Number of rows in matrix A
        int colsA = 1000; // Number of columns in matrix A
        int rowsB = 1000; // Number of rows in matrix B (must match colsA)
        int colsB = 1000; // Number of columns in matrix B

        // Generate random matrices
        int[][] matrixA = generateRandomMatrix(rowsA, colsA);
        int[][] matrixB = generateRandomMatrix(rowsB, colsB);

        Integer[][] objMatrixA = generateRandomObjectMatrix(rowsA, colsA);
        Integer[][] objMatrixB = generateRandomObjectMatrix(rowsB, colsB);

        multiplyObjectMatrices_i_j_k(objMatrixA, objMatrixB);

        System.out.println("MatrixMultiplication with primitives");
        // Multiply the matrices
        multiplyMatrices_i_j_k(matrixA, matrixB);
        multiplyMatrices_j_i_k(matrixA, matrixB);
        multiplyMatrices_j_k_i(matrixA, matrixB);
        multiplyMatrices_i_k_j(matrixA, matrixB);
        multiplyMatrices_k_i_j(matrixA, matrixB);
        multiplyMatrices_k_j_i(matrixA, matrixB);
        completeArray(matrixA, matrixB);
    }

    private static void multiplyMatrices_k_j_i(int[][] matrixA, int[][] matrixB) {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;

        int[][] resultMatrix = new int[rowsA][colsB];

        long start = System.currentTimeMillis();
        for (int k = 0; k < colsA; k++) {
            for (int j = 0; j < colsB; j++) {
                for (int i = 0; i < rowsA; i++) {
                    resultMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        long end = System.currentTimeMillis();

        System.out.printf("execution k j i millis %d%n", end - start);
    }

    private static void multiplyMatrices_i_k_j(int[][] matrixA, int[][] matrixB) {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;

        int[][] resultMatrix = new int[rowsA][colsB];

        long start = System.currentTimeMillis();
        for (int i = 0; i < rowsA; i++) {
            for (int k = 0; k < colsA; k++) {
                for (int j = 0; j < colsB; j++) {
                    resultMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        long end = System.currentTimeMillis();

        System.out.printf("execution i k j millis %d%n", end - start);

    }

    private static void multiplyMatrices_j_k_i(int[][] matrixA, int[][] matrixB) {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;

        int[][] resultMatrix = new int[rowsA][colsB];

        long start = System.currentTimeMillis();
        for (int j = 0; j < colsB; j++) {
            for (int k = 0; k < colsA; k++) {
                for (int i = 0; i < rowsA; i++) {
                    resultMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        long end = System.currentTimeMillis();

        System.out.printf("execution j k i millis %d%n", end - start);
    }

    // Function to generate a matrix with random integers
    public static int[][] generateRandomMatrix(int rows, int cols) {
        Random random = new Random();
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(10); // Random integers from 0 to 9
            }
        }
        return matrix;
    }

    public static Integer[][] generateRandomObjectMatrix(int rows, int cols) {
        Random random = new Random();
        Integer[][] matrix = new Integer[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(10); // Random integers from 0 to 9
            }
        }
        return matrix;
    }

    public static void multiplyObjectMatrices_i_j_k(Integer[][] matrixA, Integer[][] matrixB) {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;

        int[][] resultMatrix = new int[rowsA][colsB];

        long start = System.currentTimeMillis();
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    resultMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        long end = System.currentTimeMillis();

        System.out.printf("execution Integer object matrix i j k millis %d%n", end - start);
    }


    public static int[][] multiplyMatrices_i_j_k(int[][] matrixA, int[][] matrixB) {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;

        int[][] resultMatrix = new int[rowsA][colsB];

        long start = System.currentTimeMillis();
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    resultMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        long end = System.currentTimeMillis();

        System.out.printf("execution i j k millis %d%n", end - start);
        return resultMatrix;
    }

    public static int[][] multiplyMatrices_j_i_k(int[][] matrixA, int[][] matrixB) {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;

        int[][] resultMatrix = new int[rowsA][colsB];

        long start = System.currentTimeMillis();
        for (int j = 0; j < colsB; j++) {
            for (int i = 0; i < rowsA; i++) {
                for (int k = 0; k < colsA; k++) {
                    resultMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        long end = System.currentTimeMillis();

        System.out.printf("execution j i k millis %d%n", end - start);
        return resultMatrix;
    }

    public static void multiplyMatrices_k_i_j(int[][] matrixA, int[][] matrixB) {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;

        int[][] resultMatrix = new int[rowsA][colsB];

        long start = System.currentTimeMillis();
        for (int k = 0; k < colsA; k++) {
            for (int i = 0; i < rowsA; i++) {
                for (int j = 0; j < colsB; j++) {
                    resultMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        long end = System.currentTimeMillis();

        System.out.printf("execution k i j millis %d%n", end - start);
    }

    public static void completeArray(int[][] matrixA, int[][] matrixB) {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;

        int[][] resultMatrix = new int[rowsA][colsB];

        long start = System.currentTimeMillis();
        for (int k = 0; k < colsA; k++) {
            for (int i = 0; i < rowsA; i++) {
                int[] matrixA_i = matrixA[i];
                int[] matrixB_k = matrixB[k];
                for (int j = 0; j < colsB; j++) {
                    resultMatrix[i][j] += matrixA_i[k] * matrixB_k[j];
                }
            }
        }
        long end = System.currentTimeMillis();

        System.out.printf("fastest %d%n", end - start);
    }
}
