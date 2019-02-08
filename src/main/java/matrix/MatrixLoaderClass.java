package matrix;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class MatrixLoaderClass implements MatrixLoader {

    public Matrix LoaderFromFile(String filename) throws IOException {

        BufferedReader bf = new BufferedReader (new FileReader(filename));

        int lineCount = 0;
        String line;

        List<List<Double>> matrix = new ArrayList<List<Double>>();
        while ((line= bf.readLine()) != null)
        {
            String[] values = line.split(" ");
            List<Double> rows = new ArrayList<Double>();
            for (String value : values) {

                rows.add(Double.parseDouble(value));
            }
            matrix.add(rows);

            lineCount++;
        }

        if(matrix.isEmpty()){
            throw new MatrixError("Matrix is empty in the LoaderFromFile method in the MatrixLoaderClass");
        }

        int countCol=(matrix.get(0)).size();
        for(List<Double> row : matrix){
           if(row.size()!= countCol){
               throw new MatrixError("Wrong matrix");
           }
        }

        return new MatrixClass(matrix);
    }
}
