package layer.fully;

import mathfunctions.Sigmoid;
import matrix.Matrix;
import matrix.MatrixClass;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
        List<List<Double>> listList = new ArrayList<>();
        for (int x = 0; x < input.get(0).getSize(1);x++){
            List<Double> list = new ArrayList<>();
            for (int y = 0; y < input.get(0).getSize(2);y++){
                double elem =0.0;
                for (int i = 0; i <input.size();i++){
                    elem += input.get(i).get(x,y);
                }
                list.add(elem);
            }
            listList.add(list);
        }
        //8x16---> 128x1
        Matrix matrix = new MatrixClass(listList);
        output = new ArrayList<>();
        for (int i = 0; i < matrix.getSize(1);i++){
            for (int j = 0; j< matrix.getSize(2);j++){
                output.add(matrix.get(i,j));
            }
        }
        //foreach sigmoid

        for (int i = 0; i<output.size();i++){
            double _d = Sigmoid.sigmoid(output.get(i));
            output.set(i,_d);
        }
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

        try {
            FileWriter fileWriter = new FileWriter(filename, false);

            for (int x = 0; x < coefficients.size(); x++) {
                fileWriter.append("[\n");
                for (int i = 0; i < coefficients.get(x).getSize(1); i++) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int j = 0; j < coefficients.get(x).getSize(2); j++) {
                        double d = coefficients.get(x).get(i,j);
                        if (j ==coefficients.get(x).getSize(2)){
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
            }
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

            coefficients = new ArrayList<>();

            String string;
            List<Double> doubleList = new ArrayList<>();
            List<List<Double>> doubleListList = new ArrayList<>();
            while ((string = bufferedReader.readLine())!=null){

                if (string.contains("[")){
                    continue;
                }
                if (string.contains("]")){
                    coefficients.add(new MatrixClass(doubleListList));
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
