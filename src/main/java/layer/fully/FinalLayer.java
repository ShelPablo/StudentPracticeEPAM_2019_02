package layer.fully;

import matrix.Matrix;

import java.util.ArrayList;
import java.util.List;

public class FinalLayer {

    private List<Matrix> input;

    private List<Double> output;

    private List<List<Matrix>> coefficientsSet;

    public void setInput(List<Matrix> input){ this.input = input; }

    public List<Matrix> getInput(){ return input; }

    public void setOutput(List<Double> output){ this.output = output; }

    public List<Double> getOutput(){ return output; }

    public void setCoefficientsSet(List<List<Matrix>> coefficientsSet){ this.coefficientsSet = coefficientsSet; }

    public List<List<Matrix>> getCoefficientsSet(){ return coefficientsSet; }

    public List<Double> apply(List<Matrix> input) {

        if (coefficientsSet.get(0).size() == input.size()){
            setInput(input);

            List<Double> output = new ArrayList<>();

            for (List<Matrix> coefficients: coefficientsSet){
                double result = 0;

                for (int i = 0; i < coefficients.size(); i++){
                    double value = coefficients.get(i).convolute(input.get(i));
                    result += value;
                }

                result = result / (forNormalization(coefficients)*forNormalization(input));

                output.add(result);
            }

            setOutput(output);

            return output;
        }
        else throw new IllegalArgumentException("The dimensions of coefficients and input must be equal");
    }

    private Double forNormalization(List<Matrix> matrixSet){
        double result = 0;

        for (Matrix matrix: matrixSet){
            for (int i = 0; i < matrix.getSize(1); i++){
                for (int j = 0; j < matrix.getSize(2); j++){
                    double value = Math.pow(matrix.get(i, j), 2);
                    result += value;
                }
            }
        }

        return Math.sqrt(result);
    }

    public void uploadCeffSetToFile(String filename) {

    }

    public void downloadCeffSetFromFile(String filename) {

    }





}
