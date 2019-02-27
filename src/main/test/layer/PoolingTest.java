package layer;

import layer.pool.MaxPoolLayer;
import matrix.Matrix;
import matrix.MatrixClass;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PoolingTest {

    @Test
    public void apply() {
        List<List<Double>> _matrix1 = new ArrayList<>();

        _matrix1.add(new ArrayList<>(Arrays.asList(1., 0., 2., 3., 1.)));
        _matrix1.add(new ArrayList<>(Arrays.asList(4., 6., 6., 8., 2.)));
        _matrix1.add(new ArrayList<>(Arrays.asList(3., 1., 1., 0., 0.)));
        _matrix1.add(new ArrayList<>(Arrays.asList(1., 2., 2., 4., 1.)));
        _matrix1.add(new ArrayList<>(Arrays.asList(0., 2., 3., 1., 1.)));

        Matrix matrix1 = new MatrixClass(_matrix1);

        List<Matrix> matrices = new ArrayList<>();
        matrices.add(matrix1);

        Layer poolingLayer = new MaxPoolLayer(2, 2);
        poolingLayer.apply(matrices);

        List<Matrix> output = poolingLayer.getOutput();

        // Required output
        List<List<Double>> _matrix2 = new ArrayList<>();
        _matrix2.add(new ArrayList<>(Arrays.asList(6., 8.)));
        _matrix2.add(new ArrayList<>(Arrays.asList(3., 4.)));

        Matrix matrix2 = new MatrixClass(_matrix2);

        Assert.assertEquals(output.get(0).getMatrix(), matrix2.getMatrix());
    }
}