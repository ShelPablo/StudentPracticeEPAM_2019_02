package layer;

import matrix.Matrix;
import matrix.MatrixClass;
import java.util.ArrayList;
import java.util.List;

public abstract class ConvLayerClass implements Layer {

    protected List<List<Matrix>> _kernel;
    protected List<Matrix> _input;
    protected List<Matrix> _output;
    protected int _stair;

    public ConvLayerClass(List<List<Matrix>> kernel, List<Matrix> input, int stair){
        _kernel = kernel;
        _input = input;
        _stair = stair;
    }


    public  void apply() {

    }

    public List<Matrix> convolute(List<Matrix> input) {
        List<Matrix> res = new ArrayList<>();
        for (List<Matrix> filter : _kernel) {
            res.add((convolute3d(input, filter).get(0)));
        }
        return res;
    }

    public List<Matrix> convolute3d(List<Matrix> input, List<Matrix> kernel) {
        //check layer's count
        if (input.size() != kernel.size()) {
            throw new RuntimeException("Kernel and image have a different number of layer");
        }

        int height = kernel.get(0).getSize(1);
        int width = kernel.get(0).getSize(2);


        List<List<Double>> resultList = new ArrayList<>();


        for (int i = 0; i < input.get(0).getSize(1) / _stair; i++) {
            List<Double> resultRow = new ArrayList<>();
            for (int j = 0; j < input.get(0).getSize(2) / _stair; j++) {
                resultRow.add(0d);
            }
            resultList.add(resultRow);
        }

        //take all of layer
        for (int k = 0; k < input.size(); k++) {
            Matrix rgbInputMatrix = input.get(k);
            //row

            for (int i = 0; i < (rgbInputMatrix.getSize(1) - ((rgbInputMatrix.getSize(1) % _stair) * _stair)) - height; i += _stair) {

                //col
                for (int j = 0; j < (rgbInputMatrix.getSize(2) - ((rgbInputMatrix.getSize(2) % _stair) * _stair)) - height - width; j += _stair) {

                    List<List<Double>> pieceInputMatrix = takePieceInputMatrix(rgbInputMatrix, width, height, i, j);
                    Matrix m = kernel.get(k).dot(new MatrixClass(pieceInputMatrix));
                    if (k == input.size() - 1 && resultList.get(i / 4).get(j / 4) + Sum(m) < 0d) {
                        resultList.get(i / _stair).set(j / _stair,0d);
                    } else {
                        resultList.get(i / _stair).set(j / _stair,resultList.get(i / 4).get(j / 4) + Sum(m));
                    }

                }
            }
        }

        List<Matrix> resMatrix = new ArrayList<>();
        resMatrix.add(new MatrixClass(resultList));
        return resMatrix;
    }

    public List<List<Double>> takePieceInputMatrix(Matrix inputMatrix, int width, int height, int col, int row) {

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

    public Double Sum(Matrix m) {

        double res = 0d;
        for (int i = 0; i < m.getSize(1); i++) {
            for (int j = 0; j < m.getSize(2); j++) {
                res += m.get(i, j);
            }
        }
        return res;

    }




}
