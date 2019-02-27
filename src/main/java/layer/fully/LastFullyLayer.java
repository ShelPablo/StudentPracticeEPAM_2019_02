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

    void uploadCoefficients(String filename) {
        Path path = Paths.get(filename);

        if (!Files.exists(path)) {
            File file = new File(filename);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try{
            FileWriter fileWriter = new FileWriter(filename, false);
            fileWriter.append("[\n");
            for (int x = 0; x < coefficients.getSize(1); x++) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < coefficients.getSize(2); i++) {
                    double d = coefficients.get(x,i);
                    if (i ==coefficients.getSize(2)){
                        stringBuilder.append(String.valueOf(d)).append("\n");
                    }
                    else {
                        stringBuilder.append(String.valueOf(d)).append(" ");
                    }
                }
                fileWriter.write(stringBuilder.toString());
                fileWriter.append("\n");
            }
            fileWriter.append("]\n");
            fileWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    void downloadCoefficients(String filename) {
        try{

            File file = new File(filename);
            FileReader fr = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fr);

            String string;
            List<Double> doubleList = new ArrayList<>();
            List<List<Double>> doubleListList = new ArrayList<>();
            while ((string = bufferedReader.readLine())!=null){

                if (string.contains("[")){
                    continue;
                }
                if (string.contains("]")){
                    coefficients = new MatrixClass(doubleListList);
                    break;
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
