package layer.pool;

import layer.Layer;
import matrix.Matrix;
import matrix.MatrixClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxPoolLayer implements Layer {

    public MaxPoolLayer(int cellSize1, int cellSize2){
        this.cellSize1 = cellSize1;
        this.cellSize2 = cellSize2;
    }

    private int cellSize1;
    private int cellSize2;
    private List<Matrix> input = new ArrayList<>();
    private List<Matrix> output = new ArrayList<>();

    public List<Matrix> getInput(){
        return this.input;
    }

    public List<Matrix> getOutput(){
        return this.output;
    }

    @Override
    public Layer setInput(List<Matrix> input) {
        this.input.clear();
        this.input.addAll(input);
        return this;
    }
    // Maximum function
    private double getMaxValue(Matrix input, int indexOfRow, int indexOfCol){
        double maxValue = 0;

        for (int i = indexOfRow; i < (indexOfRow + cellSize1); i++) {
            for (int j = indexOfCol; j < (indexOfCol + cellSize2); j++) {
                if (input.get(i, j) > maxValue)
                    maxValue = input.get(i, j);
            }
        }

        return maxValue;
    }

    public List<Matrix> apply(List<Matrix> input){
        this.setInput(input);
        output.clear();
        for (Matrix matrix: input){
            List<List<Double>> result = new ArrayList<>();
            int rows = matrix.getSize(1) / cellSize1;
            int columns = matrix.getSize(2) / cellSize2;

            for (int i = 0; i < rows; i++) {
                result.add(new ArrayList<>(Collections.nCopies(columns, 0.)));
            }

            for (int i = 0; i < rows; i++){
                for (int j = 0; j < columns; j++){
                    int indexOfRow = i * cellSize1;
                    int indexOfCol = j * cellSize2;
                    result.get(i).set(j, getMaxValue(matrix, indexOfRow, indexOfCol));
                }
            }

            output.add(new MatrixClass(result));
        }
        return this.getOutput();
    }
}