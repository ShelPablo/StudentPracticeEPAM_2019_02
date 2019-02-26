package layer.fully;

import matrix.Matrix;

import java.util.List;

public class PreFullyLayer {

    //8x16x(96+1)
    List<Matrix> input;
    //8x16x(96+1)
    List<List<List<Double>>> coefficients;
    //128  (8x16)
    List<Double> output;

    private double trainingRate = 0.1;

    public List<Double> apply(List<Matrix> input) {
        //convolute by 3-rd dimension
        //8x16
        //foreach sigmoid
        return output;
    }



    List<Matrix> train(List<Double> deltaY){
        //save input
        //apply
        //save output

        //formula
        return null;//deltaX;

    }

    void seedCoefficients() {
        //random values in [-0.5 0.5]
    };
    void uploadCoefficients() {};
    void downloadCoefficients() {};

}
