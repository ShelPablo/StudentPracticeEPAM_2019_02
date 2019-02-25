package matrix;

import java.util.*;

public class MatrixClass implements Matrix {

    private List<List<Double>> matrix;

    public List<List<Double>> getMatrix() {
        return matrix;
    }

    public MatrixClass(List<List<Double>> matrix) {
        this.matrix = matrix;
    }

    public MatrixClass(int nRows, int nColumns) {
        this.matrix = new ArrayList<>();
        for (int i = 0; i < nRows; i++) {
            this.matrix.add((new ArrayList<Double>(Collections.nCopies(nColumns, 0d))));
        }
//                List<Double> zerosRow = Collections.nCopies(nColumns, 0d);
//        this.matrix = Collections.nCopies(nRows, zerosRow);
    }

    @Override
    public void set(int rowIdx, int colIdx, double value) {
        this.matrix.get(rowIdx).set(colIdx, value);
    }

    public double get(int rowIdx, int colIdx) {
        if ((rowIdx >= 0) && (rowIdx < getSize(1)) &&
                (colIdx >= 0) && (colIdx < getSize(2))) {
            return matrix.get(rowIdx).get(colIdx);
        } else throw new IllegalArgumentException("Index out of bounds: rowIdx=" + rowIdx + "; colIdx=" + colIdx);
    }

    public List<Double> getRow(int rowIdx) {
        if ((rowIdx >= 0) && (rowIdx < getSize(1))) {
            return matrix.get(rowIdx);
        } else throw new IllegalArgumentException("Row index out of bounds: " + rowIdx);
    }

    public List<Double> getCol(int colIdx) {
        if ((colIdx >= 0) && (colIdx < getSize(2))) {
            List<Double> column = new ArrayList<>();
            for (List<Double> row : matrix) {
                column.add(row.get(colIdx));
            }
            return column;
        } else throw new IllegalArgumentException("Column index out of bounds");
    }

    /*transpose matrix*/
    public Matrix t() {
        List<List<Double>> transposedMatrix = new ArrayList<>();

        int n = getSize(2);
        for (int i = 0; i < n; i++) {
            transposedMatrix.add(getCol(i));
        }
        return new MatrixClass(transposedMatrix);
    }

    /*element-by-element multiplication*/
    public Matrix dot(Matrix matrix) {

        if (getSize(1) != matrix.getSize(1) &&
                getSize(2) != matrix.getSize(2)) {
            throw new RuntimeException("Matrix sizes are different");
        }

        List<List<Double>> hadamardMatrix = new ArrayList<List<Double>>();

        for (int i = 0; i < getSize(1); i++) {
            List<Double> rowHadamartMatrix = new ArrayList<Double>();
            for (int j = 0; j < getSize(2); j++) {
                rowHadamartMatrix.add(get(i, j) * matrix.get(i, j));
            }
            hadamardMatrix.add(rowHadamartMatrix);
        }
        return new MatrixClass(hadamardMatrix);
    }

    /*row-on-column matrix multiplication*/
    public Matrix x(Matrix matrix) {
        if (getSize(2) == matrix.getSize(1)) {
            List<List<Double>> result = new ArrayList<>();
            for (int i = 0; i < getSize(1); i++) {
                result.add(new ArrayList<>(Collections.nCopies(matrix.getSize(2), 0.)));
            }

            for (int i = 0; i < getSize(1); i++) {
                for (int j = 0; j < matrix.getSize(2); j++) {
                    for (int k = 0; k < getSize(2); k++) {
                        double val = get(i, k) * matrix.get(k, j);
                        result.get(i).set(j, result.get(i).get(j) + val);
                    }
                }
            }
            return new MatrixClass(result);
        } else throw new IllegalArgumentException(
                "The number of columns in the first matrix and the number of rows in the second matrix must be equal");
    }

    @Override
    public Double convolute(Matrix matrix) {
        if (getSize(2) == matrix.getSize(1)) {
            double result = 0;
            for (int i = 0; i < getSize(1); i++) {
                for (int j = 0; j < matrix.getSize(2); j++) {
                    result += this.get(i, j) * matrix.get(i, j);
                }
            }
            return result;
        } else throw new IllegalArgumentException("Matrix dimensions must be equal");
    }

    public int getSize(int dimension) {
        switch (dimension) {
            case 1:
                return matrix.size();
            case 2:
                return matrix.get(0).size();
            default:
                throw new IllegalArgumentException("Two dimensions: 1 - rows; 2 - columns");
        }
    }

    @Override
    public void setRow(int rowIdx, List<Double> value) {
        this.getRow(rowIdx).clear();
        this.getRow(rowIdx).addAll(0, value);
    }

    @Override
    public Matrix subMatrix(int rowIdx, int colIdx, int size1, int size2) {
        Matrix result = new MatrixClass(size1, size2);
        for (int i = 0; i < size1; i++) {
            List<Double> subRow = this.getRow(rowIdx + i).subList(colIdx, colIdx + size2);
            result.setRow(i, subRow);
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        for (int i = 0; i < this.getSize(1); i++) {
            for (int j = 0; j < this.getSize(2); j++) {
                sb.append(this.get(i, j)).append("  ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatrixClass that = (MatrixClass) o;
        for (int i = 0; i < this.getSize(1); i++) {
            for (int j = 0; j < this.getSize(2); j++) {
                if (this.get(i, j) != that.get(i, j)) return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matrix);
    }

    //@Override
    public static Matrix fromString(String string) {

        List<List<Double>> matrixFromString = new ArrayList<>();

        String[] stringMatrix = string.split(";'\n'");
        for (String s : stringMatrix) {
            String[] rowStringMatrix = s.split(",");
            List<Double> rowFromString = new ArrayList<>();
            for (String _s : rowStringMatrix) {

                double el = Double.parseDouble(_s);
                rowFromString.add(el);
            }
            matrixFromString.add(rowFromString);
        }

        return new MatrixClass(matrixFromString);
    }
}