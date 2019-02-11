package matrix;

import java.util.ArrayList;
import java.util.List;

public class MatrixC implements Matrix {

    ArrayList<ArrayList<Double>> matrix = new ArrayList<>();

    public MatrixC(ArrayList<ArrayList<Double>> matrix){
        this.matrix = matrix;
    }

    public double get(int rowIdx, int colIdx){
        if (rowIdx<=getSize(1) && colIdx<=getSize(2) && rowIdx>0 && colIdx>0){
            return matrix.get(rowIdx).get(colIdx);
        }
        else{
            throw new ErrorCatcher("Enter correct ids:");
        }
    }


    public List<Double> getRow(int rowIdx){
        if (rowIdx>0 && rowIdx<getSize(1)){
            return matrix.get(rowIdx);
        }
        else {
            throw new ErrorCatcher("Enter correct id:");
        }
    }

    public List<Double> getCol(int colIdx){
        if (colIdx>0 && colIdx<getSize(2)){
            ArrayList<Double> col = new ArrayList<>();
            Double el;
            for (int i = 0; i<matrix.size();i++){
                el = matrix.get(i).get(colIdx);
                col.add(el);
            }
            return col;
        }
        else {
            throw new ErrorCatcher("Enter correct id:");
        }
    }

    /*transpose matrix*/
    public Matrix t(){
        ArrayList<ArrayList<Double>> Transpose_Matrix = new ArrayList<>();
        for (int i = 0; i<matrix.get(0).size();i++){
            ArrayList<Double> col1 = new ArrayList<>();
            for (int j = 0; j<matrix.size();j++){
                double ele = matrix.get(j).get(i);
                col1.add(ele);
            }
            Transpose_Matrix.add(col1);
        }
        return new MatrixC(Transpose_Matrix);
    }

    /*element-by-element multiplication*/
    public Matrix dot(Matrix matrix){
        //условие возможности
        if (getSize(1)==matrix.getSize(1) && getSize(2)==matrix.getSize(2)){
            ArrayList<ArrayList<Double>> sum = new ArrayList<>(); //итоговая матрица
            for (int i = 0; i< getSize(1);i++){
                ArrayList<Double> sum_row = new ArrayList<>();
                for (int j = 0; j<matrix.getSize(2);j++){
                    double sum_el = get(i,j)*matrix.get(i,j);
                    sum_row.add(sum_el);
                }
                sum.add(sum_row);
            }
            return new MatrixC(sum);
        }
        else {
            throw new ErrorCatcher("Incompatible matrices:");
        }

    }

    //    /*row-on-column matrix multiplication*/
    public Matrix x(Matrix matrix){

        //условие возможности
        if (getSize(2)==matrix.getSize(1)){
            ArrayList<ArrayList<Double>> sum2 = new ArrayList<>();
            for (int i = 0; i<getSize(2);i++){
                ArrayList<Double> sum2_col = new ArrayList<>();
                for (int j = 0; j<getSize(1);j++){
                    double xsum = 0;
                    for (int k =0; k<getSize(2);k++){
                        xsum+=get(j,k)*matrix.get(k,j);
                    }
                    sum2_col.add(xsum);
                }
                sum2.add(sum2_col);
            }
            return new MatrixC(sum2);
        }
        else {
            throw new ErrorCatcher("Incompatible matrices:");
        }

    }



    //if 1 - rows, 2 - cols
    public int getSize(int dimension) {
        int result;
        switch(dimension){
            case 1:
                result = matrix.size();
                break;
            case 2:
                result = matrix.get(0).size();
                break;
            default:
                throw  new ErrorCatcher("Enter 1 or 2");
        }
        return result;
    }
}
