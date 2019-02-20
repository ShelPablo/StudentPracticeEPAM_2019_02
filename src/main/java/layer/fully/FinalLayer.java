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

    public List<Double> apply(List<Matrix> input) {
        //save input

        //calculate output

        return output;
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
                    doubleList = new ArrayList<>();
                    continue;
                }
                String[] strings = string.split(" ");
                for (String s: strings) {
                    doubleList.add(Double.parseDouble(s));
                }
                doubleListList.add(doubleList);
                doubleList = new ArrayList<>();
                string = bufferedReader.readLine();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}

