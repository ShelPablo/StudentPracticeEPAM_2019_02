package layer.fully;

import matrix.Matrix;
import matrix.MatrixClass;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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

            //List<Integer> indicesOfNecessaryFilters = Arrays.asList(3, 9, 31, 38, 39, 43, 44, 55, 78, 79);

            for (List<Matrix> coefficients: coefficientsSet){
                double result = 0;

                //List<Matrix> necessaryInput = new ArrayList<>();
                //List<Matrix> necessaryCoefficients = new ArrayList<>();

                /*for (Integer i: indicesOfNecessaryFilters){
                    necessaryInput.add(input.get(i));
                    necessaryCoefficients.add(coefficients.get(i));
                }*/

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

        Path path = Paths.get(filename);

        if (!Files.exists(path)){
            File file = new File(filename);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileWriter fileWriter = new FileWriter(filename, false);

            for (int i = 0; i < coefficientsSet.size(); i++) {
                fileWriter.append("{\n");
                for (int j = 0; j < coefficientsSet.get(i).size(); j++) {
                    fileWriter.append("[");
                    for (int x = 0; x < coefficientsSet.get(i).get(j).getSize(1); x++) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int y = 0; y < coefficientsSet.get(i).get(j).getSize(2); y++) {
                            double d = coefficientsSet.get(i).get(j).get(x, y);
                            stringBuilder.append(String.valueOf(d)).append(" ");
                        }
                        fileWriter.append("\n");
                        fileWriter.write(stringBuilder.toString());
                    }
                    fileWriter.append("\n]\n");
                }
                fileWriter.append("}\n");
            }
            fileWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void downloadCeffSetFromFile(String filename) {
        try{
            File file = new File(filename);
            FileReader fr = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fr);
            coefficientsSet = new ArrayList<>();
            String string;
            List<Matrix> matrixList = new ArrayList<>();
            List<Double> doubleList = new ArrayList<>();
            List<List<Double>> doubleListList = new ArrayList<>();
            while ((string = bufferedReader.readLine())!=null){
                if (string.contains("[")||string.contains("{")){
                    continue;
                }
                if (string.contains("}")){
                    coefficientsSet.add(matrixList);
                    matrixList = new ArrayList<>();
                    continue;
                }
                if (string.contains("]")){
                    matrixList.add(new MatrixClass(doubleListList));
                    doubleListList = new ArrayList<>();
                    continue;
                }
                String[] strings = string.split(" ");
                for (String s: strings) {
                    doubleList.add(Double.parseDouble(s));
                }
                doubleListList.add(doubleList);
                doubleList = new ArrayList<>();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }





}