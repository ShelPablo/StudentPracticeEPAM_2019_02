package layer.fully;

import mathfunctions.Sigmoid;
import matrix.Matrix;
import matrix.MatrixClass;

import java.util.ArrayList;

import java.util.List;

public class LastFullyLayer {

    //128+1
    List<Double> input;

    //(128+1)x6
    Matrix coefficients;

    //6
    List<Double> output;

    private double trainingRate = 0.1;

    List<Double> train(List<Double> trueOutput) {

        List<Double> deltaW = new ArrayList<>();
        List<Double> deltaWWithoutRate = new ArrayList<>();
        double error ;
        double sigmoidDerivative;
        for (int i = 0; i < this.output.size(); i++) {
            //(y-y')
            error = trueOutput.get(i) - this.output.get(i);
            //sigmoidDerivative(y')
            sigmoidDerivative = Sigmoid.sigmoidDerivative(this.output.get(i));
            // Delta W = dE/dy'*  dy/dz * rate
            deltaW.add(error * sigmoidDerivative *this.trainingRate);
            deltaWWithoutRate.add(error*sigmoidDerivative);
        }

        //create Matrix from piece of deltaW
        List<List<Double>> deltaWList = new ArrayList<>();
        deltaWList.add(deltaW);
        Matrix deltaWMatrix = new MatrixClass(deltaWList); //(1x6)

        //Delta W = Delta W * dz/dw
        deltaWMatrix = deltaWMatrix.t(); //(6x1);
        List<List<Double>> inputList = new ArrayList<>();
        inputList.add(this.input);
        Matrix inputMatrix = new MatrixClass(inputList); //(1x106)
        deltaWMatrix = deltaWMatrix.x(inputMatrix); //(6x1)x(1x106)=(6x106)

        // W = W + Delta W (W - coefficients)
        List<List<Double>> coefficientsList= new ArrayList<>();
        for(int i = 0; i<this.coefficients.getSize(1);i++){
            List<Double>  coefficientsRow = new ArrayList<>();
            for(int j=0;j<this.coefficients.getSize(2);j++){
                coefficientsRow.add(this.coefficients.get(i,j)+deltaWMatrix.get(i,j));
            }
            coefficientsList.add(coefficientsRow);
        }

        // deltaX  = dE/dy'*  dy/dz * dz/dx
        List<List<Double>> deltaWListWithoutRate = new ArrayList<>();
        deltaWListWithoutRate.add(deltaWWithoutRate);
        Matrix deltaWMatrixWithoutRate = new MatrixClass(deltaWListWithoutRate); //(1x6)
        List<Double> deltaX = deltaWMatrixWithoutRate.x(coefficients).getMatrix().get(0);//(1x6)x(6x106)=(1x106)

        //change coefficients
        this.coefficients = new MatrixClass(coefficientsList);

        return deltaX;
    }

    void uploadCoefficients() {
    }

    ;

    void downloadCoefficients() {
    }

    ;


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

    };



}
