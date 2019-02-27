package layer.fully;

import matrix.Matrix;
import matrix.MatrixClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        //save input
        //apply
        //save output

        //formula
        return null;//deltaX;
    }

    void seedCoefficients() {
        //random values in [-0.5 0.5]
        Random r = new Random();
        double rMin = -0.5;
        double rMax = 0.5;

        for (int k = 0; k < 6; k++) {
            List<List<Double>> randomMatrix = new ArrayList<>();

            for (int i = 0; i < 15; i++) {
                List<Double> randomRow = new ArrayList<>();

                for (int j = 0; j < 7; j++) {
                    randomRow.add(rMin + (rMax - rMin) * r.nextDouble());
                }

                randomMatrix.add(randomRow);
            }

            Matrix matrix = new MatrixClass(randomMatrix);
            this.coefficients.add(matrix);
        }
    }

    void uploadCoefficients() {};
    void downloadCoefficients() {};
}
