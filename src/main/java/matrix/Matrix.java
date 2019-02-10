package matrix;

import java.util.List;

public interface Matrix {
    
    List<List<Double>> getMatrix();

    List<List<Double>> getMatrix();

    double get(int rowIdx, int colIdx);

    List<Double> getRow(int rowIdx);

    List<Double> getCol(int colIdx);

    /*transpose matrix*/
    Matrix t();

    /*element-by-element multiplication*/
    Matrix dot(Matrix matrix);

    /*row-on-column matrix multiplication*/
    Matrix x(Matrix matrix);

    /**
     *
     * @param dimension: 1 - rows; 2 - columns
     * @return number of rows/columns
     */
    int getSize(int dimension);

}
