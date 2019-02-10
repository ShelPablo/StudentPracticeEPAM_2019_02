package matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatrixClass implements Matrix {

    private List<List<Double>> matrix;

    public List<List<Double>> getMatrix(){
        return matrix;
    }

    public MatrixClass(List<List<Double>> matrix){
        this.matrix = matrix;
    }

    public double get(int rowIdx, int colIdx){
        if((rowIdx >= 0) && (rowIdx < getSize(1)) &&
                (colIdx >= 0) && (colIdx < getSize(2))) {
            return matrix.get(rowIdx).get(colIdx);
        }
        else throw new IllegalArgumentException("Row index or column index out of bounds");
    }

    public List<Double> getRow(int rowIdx){
        if ((rowIdx >= 0) && (rowIdx < getSize(1))) {
            return matrix.get(rowIdx);
        }
        else throw new IllegalArgumentException("Row index out of bounds");
    }

    public List<Double> getCol(int colIdx) {
        if ((colIdx >= 0) && (colIdx < getSize(2))){
            List<Double> column = new ArrayList<>();
            for (List<Double> row: matrix) {
                column.add(row.get(colIdx));
            }
            return column;
        }
        else throw new IllegalArgumentException("Column index out of bounds");
    }

    /*transpose matrix*/
    public Matrix t(){
        List<List<Double>> transposedMatrix = new ArrayList<>();

        int n = getSize(2);
        for (int i = 0; i < n; i++){
            transposedMatrix.add(getCol(i));
        }
        return new MatrixClass(transposedMatrix);
    }

    /*element-by-element multiplication*/
    public Matrix dot(Matrix matrix){
        if ((getSize(1) == matrix.getSize(1)) &&
                (getSize(2) == matrix.getSize(2)))
        {
            for (int i = 0; i < getSize(1); i++){
                for (int j = 0; j < getSize(2); j++){
                    this.matrix.get(i).set(j,this.matrix.get(i).get(j) * matrix.get(i,j));
                }
            }
            return new MatrixClass(this.matrix);
        }
        else throw new IllegalArgumentException("Matrices dimensions must be equal");
    }

    /*row-on-column matrix multiplication*/
    public Matrix x(Matrix matrix){
        if (getSize(2) == matrix.getSize(1))
        {
            List<List<Double>> result = new ArrayList<>();
            for (int i = 0; i < getSize(1); i++) {
                result.add(new ArrayList<>(Collections.nCopies(matrix.getSize(2), 0.)));
            }

            for (int i = 0; i < getSize(1); i++){
                for (int j = 0; j < matrix.getSize(2); j++){
                    for (int k = 0; k < getSize(2); k++){
                        double val = get(i,k) * matrix.get(k,j);
                        result.get(i).set(j, result.get(i).get(j) + val);
                    }
                }
            }
            return new MatrixClass(result);
        }
        else throw new IllegalArgumentException(
                "The number of columns in the first matrix and the number of rows in the second matrix must be equal");
    }

    public int getSize(int dimension){
        switch(dimension)
        {
            case 1:
                return matrix.size();
            case 2:
                return matrix.get(0).size();
            default:
                throw new IllegalArgumentException("Two dimensions: 1 - rows; 2 - columns");
        }
    }

}
