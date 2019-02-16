package layer;

import matrix.Matrix;
import matrix.MatrixClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.File;
import java.io.IOException;

public class KernelsReader {

    public static List<List<Matrix>> readKernelsFromFile(String filename){

        List<List<Matrix>> kernels = new ArrayList<>();
        ArrayList<Matrix> kernel = new ArrayList<>();

        try{
            File file = new File(filename);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();
            while (line != null) {

                for (int i = 0; i < 3; i++){
                    List<List<Double>> matrix = new ArrayList<>();

                    for (int n = 0; n < 11; n++) {
                        matrix.add(new ArrayList<>(Collections.nCopies(11, 0.)));
                    }
                    for (int j = 0; j < 11; j++){
                        line = reader.readLine();
                        line = line.replace("[", "");
                        line = line.replace("]", "");
                        String[] m = line.split(" ");
                        for (int k = 0; k < m.length; k++){
                            matrix.get(j).set(k, Double.parseDouble(m[k]));
                        }
                    }

                    kernel.add(new MatrixClass(matrix));
                }

                kernels.add(kernel);

                kernel = new ArrayList<>();

                reader.readLine();
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return kernels;
    }

    /*public static void main(String[] args){

        List<List<Matrix>> kernels = KernelsReader.readKernelsFromFile("src/main/resources/AlexK.txt");

        for (List<Matrix> list: kernels){
            for (Matrix m: list){
                for (int i = 0; i < 11; i++){
                    for (int j = 0; j < 11; j++){
                        System.out.print(m.get(i, j) + " ");
                    }
                    System.out.println(" ");
                }
                System.out.println(" ");
                System.out.println(" ");
            }
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");
        }
    }*/
}
