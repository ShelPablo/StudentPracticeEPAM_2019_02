package layer.fully;

import matrix.Matrix;

import matrix.MatrixClass;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;

public class PreFullyLayer {

    //8x16x(96+1)
    List<Matrix> input;
    //8x16x(96+1)
    List<Matrix> coefficients;
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

        List<Matrix> deltaX = new ArrayList<>();

        for (int i = 0; i < coefficients.size(); i++){
            deltaX.add(new MatrixClass(coefficients.get(0).getSize(1),
                    coefficients.get(0).getSize(2)));
        }

        Matrix deltaYasMatrix = new MatrixClass(coefficients.get(0).getSize(1),
                coefficients.get(0).getSize(2));
        Matrix outputAsMatrix = new MatrixClass(coefficients.get(0).getSize(1),
                coefficients.get(0).getSize(2));

        int index = 0;
        for (int i = 0; i < coefficients.get(0).getSize(1); i++){
            for (int j = 0; j < coefficients.get(0).getSize(2); j++){
                deltaYasMatrix.set(i, j, deltaY.get(index));
                outputAsMatrix.set(i, j, output.get(index));
                index = index + 1;
            }
        }

        //formula
        for (int i = 0; i < coefficients.size(); i++){
            for (int j = 0; j < coefficients.get(0).getSize(1); j++){
                for (int k = 0; k < coefficients.get(0).getSize(2); k++){
                    double newCoefficient = coefficients.get(i).get(j, k) + deltaYasMatrix.get(j, k) *
                            trainingRate*input.get(i).get(j, k)*(outputAsMatrix.get(j, k)*
                            (1 - outputAsMatrix.get(j, k)));
                    this.coefficients.get(i).set(j, k, newCoefficient);

                    deltaX.get(i).set(j, k, coefficients.get(i).get(j, k)*
                            deltaYasMatrix.get(j, k));
                }
            }
        }

        return deltaX;
    }

    void seedCoefficients() {
        //random values in [-0.5 0.5]
    };
    void uploadCoefficients() {};
    void downloadCoefficients() {};

}
