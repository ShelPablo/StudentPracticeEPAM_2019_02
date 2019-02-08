package matrix;

import java.util.ArrayList;
import java.util.List;

public class MatrixClass implements Matrix {

    private List<List<Double>> matrix;

    public MatrixClass(List<List<Double>> matrix) {
        this.matrix = matrix;
    }

    public List<List<Double>> getMatrix(){
        return matrix;
    }
    public double get(int rowIdx, int colIdx) {

        if (rowIdx < 0 || colIdx < 0 || rowIdx > getSize(1) || colIdx > getSize(2)) {
            throw new MatrixError("Wrong arguments for the get method  in the MatrixClass");
        }

        return (matrix.get(rowIdx)).get(colIdx);

    }

    public List<Double> getRow(int rowIdx) {
        if (rowIdx < 0 || rowIdx > getSize(1)) {
            throw new MatrixError("Wrong argument: " + rowIdx + " for the getRow method in the MatrixClass");
        }
        return matrix.get(rowIdx);
    }

    public List<Double> getCol(int colIdx) {

        if (colIdx < 0 || colIdx > getSize(2)) {
            throw new MatrixError("Wrong argument: " + colIdx + " for the getCol method in the MatrixClass");
        }

        List<Double> col = new ArrayList<Double>();
        for (int i = 0; i < getSize(1); i++) {
            col.add(get(i, colIdx));
        }

        return col;
    }

    public Matrix t() {

        List<List<Double>> Tmatrix = new ArrayList<List<Double>>();
        for (int i = 0; i < getSize(2); i++) {
            Tmatrix.add(getCol(i));
        }
        return new MatrixClass(Tmatrix);
    }

    public Matrix dot(Matrix matrix) {

        if (getSize(1) != matrix.getSize(1) &&
                getSize(2) != matrix.getSize(2)) {
            throw new MatrixError("Matrix sizes are different for the dot method in the MatrixClass");
        }

        List<List<Double>> HadamardMatrix = new ArrayList<List<Double>>();

        for (int i = 0; i < getSize(1); i++) {
            List<Double> rowHadamartMatrix = new ArrayList<Double>();
            for (int j = 0; j < getSize(2); j++) {
                rowHadamartMatrix.add(get(i, j) * matrix.get(i, j));
            }
            HadamardMatrix.add(rowHadamartMatrix);
        }
        return new MatrixClass(HadamardMatrix);
    }

    /* x :D */
    public Matrix x(Matrix matrix) {

        if (getSize(2) != matrix.getSize(1)) {
            throw new MatrixError("Matrix sizes are wrong for the x method in the MatrixClass");
        }

        List<List<Double>> NewMatrix = new ArrayList<List<Double>>();
        List<Double> rowNewMatrix= new ArrayList<Double>();;
        int k = 0;
        while (k < matrix.getSize(2)) {
            rowNewMatrix = new ArrayList<Double>();
            for (int i = 0; i < getSize(1); i++) {
                double value = 0;
                for (int j = 0; j < getSize(2); j++) {
                    value += get(i, j) * matrix.get(j, i);
                }
                rowNewMatrix.add(value);
            }
            NewMatrix.add(rowNewMatrix);
            k++;
        }

        return new MatrixClass(NewMatrix);
    }

    public int getSize(int dimension) {
        int size;
        switch (dimension) {
            case 1:
                size = matrix.size();
                break;
            case 2:
                size = (matrix.get(0)).size();
                break;
            default:
                throw new MatrixError("Wrong argument: " + dimension + " for the getSize method in the MatrixClass");
        }
        return size;
    }
}