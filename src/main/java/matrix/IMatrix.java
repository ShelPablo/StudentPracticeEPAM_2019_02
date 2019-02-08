package matrix;

import java.util.List;

public interface IMatrix {

    double get(int rowIdx, int colIdx);

    List<Double> getRow(int rowIdx);

    List<Double> getCol(int colIdx);

    /*transpose matrix*/
    IMatrix t();

    /*element-by-element multiplication*/
    IMatrix dot(IMatrix matrix);

    /*row-on-column matrix multiplication*/
    IMatrix x(IMatrix matrix);

    /**
     *
     * @param dimension: 1 - rows; 2 - columns
     * @return number of rows/columns
     */
    int getSize(int dimension);

}
