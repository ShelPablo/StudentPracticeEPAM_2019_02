package layer;

import matrix.Matrix;
import matrix.MatrixClass;

import java.util.ArrayList;
import java.util.List;

public class PoolingLayerClass implements Layer{

    private int cellSize1;
    private int cellSize2;
    private List<Matrix> input;
    private List<Matrix> output;



    public PoolingLayerClass(int cellSize1,int cellSize2,List<Matrix> input){
        this.cellSize1 = cellSize1;
        this.cellSize2 = cellSize2;
        this.input = input;
    }
    public List<Matrix> getOutput(){
        return this.output;
    }


    public void apply() {
        List<Matrix> result = new ArrayList<>();
        for(Matrix m : this.input){
            result.add(pooling(m));
        }
        this.output = result;
    }

    public Matrix pooling(Matrix input){

        List<List<Double>> result = new ArrayList<>();

        int p = input.getSize(1) - (input.getSize(1) % this.cellSize1);
        for(int i =0; i < input.getSize(1) - (input.getSize(1) % this.cellSize1);i+=this.cellSize1)
        {
            List<Double> resultRow = new ArrayList<>();
            for(int j =0; j < input.getSize(2) - (input.getSize(2) % this.cellSize1);j+=this.cellSize2){
                resultRow.add(max(takePieceInputMatrix(input,cellSize2,cellSize1,j,i)));
            }
            result.add(resultRow);
        }
        return new MatrixClass(result);
    }

    private double max(List<List<Double>> input){

        double max = input.get(0).get(0);

        for (List<Double> doubles : input) {
            for (int j = 0; j < doubles.size();j++) {
                double value = doubles.get(j);
                if (value > max) {
                    max = value;
                }
            }
        }

        return max;
    }

    private List<List<Double>> takePieceInputMatrix(Matrix inputMatrix, int width, int height, int col, int row) {

        List<List<Double>> pieceInputMatrix = new ArrayList<>();

        for (int j = row; j < row + width; j++) {
            List<Double> rowPiece = new ArrayList<>();
            for (int i = col; i < col + height; i++) {
                rowPiece.add(inputMatrix.get(j, i));
            }
            pieceInputMatrix.add(rowPiece);
        }

        return pieceInputMatrix;

    }




}
