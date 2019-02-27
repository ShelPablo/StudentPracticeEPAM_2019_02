package layer.fully;

import mathfunctions.Sigmoid;
import matrix.Matrix;
import matrix.MatrixClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LastFullyLayer {

    //128+1
    List<Double> input;

    //(128+1)x6
     public Matrix coefficients;

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

    private List<Boolean> getDecision(List<Double> inputList)
    {
        List<Boolean> decision = new ArrayList<>();
        double max = Collections.max(inputList);
        for (int i = 0; i < inputList.size(); i++) {
            if (inputList.get(i) < max) { decision.add(false);  }
            else { decision.add(true); }
        }
        return  decision;
    }


    public List<Boolean> applyWithDecision(List<Double> input) {
        input.add(1.);
        List<List<Double>> l = new ArrayList<>();
            l.add(input);
        Matrix inputMatrix = new MatrixClass(l);
        Matrix outputMatrix = inputMatrix.x(this.coefficients.t());
        List<Double> preOutput = outputMatrix.getMatrix().get(0);
        for (int i = 0; i < preOutput.size(); i++)
        {
            preOutput.set(i, Sigmoid.sigmoid(preOutput.get(i)));
        }
        return getDecision(preOutput);
    }

    public List<Double> apply(List<Double> input) {
        input.add(1.);
        List<List<Double>> l = new ArrayList<>();
        l.add(input);
        Matrix inputMatrix = new MatrixClass(l);
        Matrix outputMatrix = inputMatrix.x(this.coefficients.t());
        List<Double> preOutput = outputMatrix.getMatrix().get(0);
        for (int i = 0; i < preOutput.size(); i++)
        {
            preOutput.set(i, Sigmoid.sigmoid(preOutput.get(i)));
        }
        return this.output=preOutput;
    }

    void seedCoefficients() {
        //random values in [-0.5 0.5]
    };


}
