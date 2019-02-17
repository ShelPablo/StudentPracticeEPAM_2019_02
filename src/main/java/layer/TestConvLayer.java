package layer;

import matrix.Matrix;
import matrix.MatrixClass;
import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;


public class TestConvLayer {

    @Test
    public void convol3d(){
        List<Double> list = Arrays.asList(2.0,2.0,2.0,2.0,2.0,2.0,2.0,2.0,2.0,2.0);
        List<List<Double>> listList = Arrays.asList(list,list,list,list,list,list,list,list,list,list);

        Matrix m0 = new MatrixClass(listList);
        Matrix m1 = new MatrixClass(listList);
        Matrix m2 = new MatrixClass(listList);
        List<Matrix> input = Arrays.asList(m0,m1,m2);

        List<Double> listk = Arrays.asList(2.0,2.0,2.0,2.0,2.0);
        List<List<Double>> listListk = Arrays.asList(listk,listk,listk,listk,listk);

        Matrix k0 = new MatrixClass(listListk);
        Matrix k1 = new MatrixClass(listListk);
        Matrix k2 = new MatrixClass(listListk);
        List<Matrix> kernel = Arrays.asList(k0,k1,k2);

        List<List<Matrix>> kernels = Arrays.asList(kernel,kernel,kernel,kernel,kernel);

        AlexConvLayer convLayer = new AlexConvLayer(input,2);
        List<List<Matrix>> actual_matrix = convLayer.convol3d();

        List<Double> rowRes = Arrays.asList(12.0,12.0,12.0);
        List<List<Double>> res = Arrays.asList(rowRes,rowRes,rowRes);
        Matrix e_matrix = new MatrixClass(res);
        List<Matrix> l_matrix = Arrays.asList(e_matrix,e_matrix,e_matrix);
        List<List<Matrix>> expected_matrix = Arrays.asList(l_matrix,l_matrix,l_matrix,l_matrix,l_matrix,l_matrix);

        for (int i = 0; i<actual_matrix.size();i++){
            for (int j = 0; j<actual_matrix.get(i).size();j++){
                Assert.assertEquals(expected_matrix.get(i).get(j).getMatrix(),actual_matrix.get(i).get(j).getMatrix());
            }
        }


    }

}
