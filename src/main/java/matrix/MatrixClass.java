package matrix;

import java.util.ArrayList;
import java.util.List;

public class MatrixClass implements Matrix {
    private List<List<Double>> matrix;

    public MatrixClass(List<List<Double>> matrix) {
        this.matrix = matrix;
    }

    public double get(int rowIdx, int colIdx) {
        return matrix.get(rowIdx).get(colIdx);
    }

    public List<Double> getRow(int rowIdx) {
        return matrix.get(rowIdx);
    }

    public List<Double> getCol(int colIdx) {
        List<Double> column = new ArrayList<Double>();
        for (List<Double> row:matrix) {
            column.add(row.get(colIdx));
            
        }
        return column;
    }

    public Matrix t() {
        List<List<Double>> TMatrix=new ArrayList<List<Double>>();
        MatrixClass Matrix=new MatrixClass(TMatrix);
        for (int i=0;i<getSize(2);i++){
            TMatrix.add(getCol(i));

        }
        return Matrix;
    }

    public Matrix dot(Matrix matrix) {
        if(getSize(1)!=matrix.getSize(1) && getSize(2)!=matrix.getSize(2))
            throw new MError("Differen sizes");

        List<List<Double>> NewMatrix=new ArrayList<List<Double>>();
        for (int i=0; i<getSize(1);i++) {
            List<Double> row = new ArrayList<Double>();
            for (int j=0;j<getSize(2);j++){
                row.add(get(i,j)*matrix.get(i,j));
            }
            NewMatrix.add(row);
        }
        return new MatrixClass(NewMatrix);
    }

    public Matrix x(Matrix matrix) {
        if (getSize(2)!=matrix.getSize(1))
            throw new MError("Differen sizes");

        List<List<Double>> NewMatrix=new ArrayList<List<Double>>();
        double value;
        int n =0;
        while (n<matrix.getSize(2)) {
            List<Double> row = new ArrayList<Double>();
            for (int i = 0; i < getSize(1); i++) {
                value = 0;
                for (int j = 0; j < getSize(2); j++) {
                    value += get(i, j) * matrix.get(j, i);
                }
                row.add(value);
            }
            NewMatrix.add(row);
            n++;
        }
        return new MatrixClass(NewMatrix);
    }

    public int getSize(int dimension) {
        if (dimension==2)
            return getRow(0).size();
        if (dimension==1)
            return getCol(0).size();
        return 0;
    }
}
