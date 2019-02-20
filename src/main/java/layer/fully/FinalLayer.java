package layer.fully;

import matrix.Matrix;

import java.util.List;

public class FinalLayer {

    private List<Matrix> input;

    private List<Double> output;

    private List<List<Matrix>> coefficientsSet;

    public List<Double> apply(List<Matrix> input) {
        //save input

        //calculate output

        return output;
    }

    public void uploadCeffSetToFile(String filename) {

    }

    public void downloadCeffSetFromFile(String filename) {

    }

/*    public Double calculateSquaredDeviationForInput(int groupIdx, List<Matrix> input) {
        // (input - coefficients(groupIdx))^2
        return null;
    }

    public Double calculateAbsDeviationForInput(int groupIdx, List<Matrix> input) {
        // abs(input - coefficients(groupIdx))
        return null;
    }
*/
}
