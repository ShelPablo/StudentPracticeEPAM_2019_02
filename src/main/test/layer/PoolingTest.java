package layer;

import matrix.Matrix;
import matrix.MatrixClass;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PoolingTest {

    @Test
    public void poolingTest(){

        List<List<Double>> matrix = new ArrayList<>();

        double k =0;
        for(int i = 0; i<9;i++){
            List<Double> matrixRow = new ArrayList<>();
            for(int j = 0; j<9;j++){
                matrixRow.add(k);
                k++;
            }
            matrix.add(matrixRow);
        }

        List<Matrix> m = new ArrayList<>();
        m.add(new MatrixClass(matrix));
        PoolingLayerClass poolingLayerClass = new PoolingLayerClass(4,4,m);

        List<List<Double>> result = new ArrayList<>();
        List<Double> resultRow = new ArrayList<>();
        resultRow.add(30d);
        resultRow.add(34d);
        result.add(resultRow);
        resultRow = new ArrayList<>();
        resultRow.add(66d);
        resultRow.add(70d);
        result.add(resultRow);


        poolingLayerClass.apply();
        Assert.assertEquals(result,poolingLayerClass.getOutput().get(0).getMatrix());

    }

}
