package layer.fully;

import matrix.Matrix;

import java.util.List;

public class PreFullyLayer {

    //8x16x10
    List<Matrix> input;
    //8x16x10
    List<List<List<Double>>> coefficients;
    //128  (8x16)
    List<Double> output;

    double trainingRate;

    PreFullyLayer train(List<Matrix> input, List<Double> trueOutput){
        //formula
        return this;
    }

    //void upload/download coefficients

}
