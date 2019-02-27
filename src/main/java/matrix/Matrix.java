package matrix;

import java.util.List;

public interface Matrix {

    List<List<Double>> getMatrix();


    double get(int rowIdx, int colIdx);

    void set(int rowIdx, int colIdx, double value);

    List<Double> getRow(int rowIdx);

    List<Double> getCol(int colIdx);

    void setRow(int rowIdx, List<Double> value);

    /*transpose matrix*/
    Matrix t();

    /*element-by-element multiplication*/
    Matrix dot(Matrix matrix);

    /*row-on-column matrix multiplication*/
    Matrix x(Matrix matrix) ;

    Double convolute(Matrix matrix);


    /**
     *
     * @param dimension: 1 - rows; 2 - columns
     * @return number of rows/columns
     */
    int getSize(int dimension);


    Matrix subMatrix(int rowIdx, int colIdx, int size1, int size2);


    //Matrix from string
    //Matrix fromString(String string);
}
