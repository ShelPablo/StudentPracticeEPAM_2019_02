package matrix;
import java.util.ArrayList;

public class Matrix implements IMatrix {

    private ArrayList<ArrayList<Double>> matrix;

    public  ArrayList<ArrayList<Double>> getMatrix()
    {
        return this.matrix;
    }

    public Matrix() {
        this.matrix = new ArrayList<ArrayList<Double>>();
    }

    public Matrix(ArrayList<ArrayList<Double>> matrix) {
        this.matrix = matrix;
    }

    @Override
    public double get(int rowIdx, int colIdx) {
        double value = 0;
        try
        {
            value = matrix.get(rowIdx).get(colIdx);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return value;
    }

    @Override
    public ArrayList<Double> getRow(int rowIdx) {
        ArrayList<Double> row = new ArrayList<Double>();
        try
        {
            row = matrix.get(rowIdx);
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
        return matrix.get(rowIdx);
    }

    @Override
    public ArrayList<Double> getCol(int colIdx) {
        ArrayList<Double> column = new ArrayList<Double>();
        for ( ArrayList<Double> row : matrix) {
            try
            {
                column.add(row.get(colIdx));
            }
            catch (Exception e)
            {
                System.out.println(e);
                return null;
            }
        }
        return column;
    }

    @Override
    public Matrix t() {
        ArrayList<ArrayList<Double>> transponded = new ArrayList<ArrayList<Double>>(this.getSize(2));
        for(int i=0;i<this.getSize(2);i++) {
            ArrayList<Double> row = new ArrayList<Double>();
            for(int j=0;j<this.getSize(1);j++){
            }
        transponded.add(row);
        }
        for(int i=0;i<this.getSize(2);i++){
            for(int j=0;j<this.getSize(1);j++){
                transponded.get(i).add( this.get(j, i));
            }
        }
        return new Matrix(transponded);
    }

    @Override
    public Matrix dot(IMatrix bmatrix) {
        System.out.println(this.getSize(1));
        System.out.println(this.getSize(2));
        System.out.println(bmatrix.getSize(1));
        System.out.println(bmatrix.getSize(2));
        if (this.getSize(1) >= bmatrix.getSize(1) && this.getSize(2) >= bmatrix.getSize(2))
        {
        for(int i=0;i<bmatrix.getSize(1);i++){
            for(int j=0;j<bmatrix.getSize(2);j++){
                this.matrix.get(i).set(j, this.matrix.get(i).get(j) * bmatrix.get(i, j));
            }
        }
        return new Matrix(this.matrix);
        }
        else
        {
           throw new IndexOutOfBoundsException("Incorrect matrix demension");
        }
    }

    @Override
    public Matrix x(IMatrix bmatrix) {
        if (this.getSize(1) == bmatrix.getSize(2) && this.getSize(2) == bmatrix.getSize(1))
        {
            int m = this.getSize(1);
            int n = bmatrix.getSize(2);
            int o = bmatrix.getSize(1);
            double value = 0.0;
            ArrayList<ArrayList<Double>> result = new ArrayList<ArrayList<Double>>(m);
            for(int i=0;i<m;i++) {
                ArrayList<Double> row = new ArrayList<Double>();
                for(int j=0;j<n;j++){
                }
                result.add(row);
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < o; k++) {
                        value += this.get(i, k) * bmatrix.get(k, j);
                    }
                    result.get(i).add(value);
                    value = 0.0;
                }
            }
            return new Matrix(result);
        }
        else
        {
            throw new IndexOutOfBoundsException("Incorrect matrix demension");
        }
    }

    @Override
    public int getSize(int dimension) {
        switch (dimension)
        {
            case 1:
                return matrix.size();
            case 2:
                return matrix.get(0).size();
                default:
                        throw new IllegalArgumentException("Incorrect argument");
        }

    }
}
