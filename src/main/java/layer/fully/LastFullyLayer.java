package layer.fully;

import matrix.Matrix;
import matrix.MatrixClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LastFullyLayer {

    //128+1
    List<Double> input;

    //(128+1)x6
    Matrix coefficients;

    //6
    List<Double> output;

    private double trainingRate = 0.1;

    List<Double> train(List<Double> trueOutput){
        //calculate Error derivative (y-y') dE/dy'
        //calculate dy/dz = sigmoidDerivative(y')
        //calc dz/dw = matrix X
        // calc Delta W = dE/dy'*  dy/dz * dz/dw * rate
        // dz/dx = W (old ) - save into intermed var
        // W = W + Delta W (W - coefficients)
        // calc Delta Y for preFullyLayer:
        // deltaX  = dE/dy'*  dy/dz * dz/dx


        //formula
        return null;//deltaX
    }

    void uploadCoefficients() {};
    void downloadCoefficients() {};

    public List<Double> apply(List<Double> input) {
        //128
        //add bias -> 128+1
        //x coefficients (Matrix multiplication)
        //6
        // foreach sigmoid
        // foreach getDecision - select max
        return null;
    }

    void seedCoefficients() {
        //random values in [-0.5 0.5]
        Random r = new Random();
        double rMin = -0.5;
        double rMax = 0.5;

        List<List<Double>> randomMatrix = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            List<Double> randomRow = new ArrayList<>();

            for (int j = 0; j < 106; j++) {
                randomRow.add(rMin + (rMax - rMin) * r.nextDouble());
            }

            randomMatrix.add(randomRow);
        }

        this.coefficients = new MatrixClass(randomMatrix);
    }
}
