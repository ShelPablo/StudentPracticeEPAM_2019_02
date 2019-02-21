package matrix;

import layer.kernels.AlexNetKernelsVisualiser;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MatrixClassTest {

    @Test
    public void get() {
        List<List<Double>> _matrix = new ArrayList<>();

        _matrix.add(new ArrayList<>(Arrays.asList(1., 2., 6.)));
        _matrix.add(new ArrayList<>(Arrays.asList(1., 8., 7.)));

        Matrix matrix = new MatrixClass(_matrix);

        double expectedValue = matrix.get(1, 1);
        // Required output
        double actualValue = 8.;

        assertEquals(expectedValue, actualValue, 0.);
    }

    @Test
    public void getRow() {
        List<List<Double>> _matrix = new ArrayList<>();

        _matrix.add(new ArrayList<>(Arrays.asList(1., 2., 6., 5.)));
        _matrix.add(new ArrayList<>(Arrays.asList(1., 8., 7., 9.)));

        Matrix matrix = new MatrixClass(_matrix);

        List<Double> expectedRow = matrix.getRow(0);
        // Required output
        List<Double> actualRow = new ArrayList<>(Arrays.asList(1., 2., 6., 5.));

        assertEquals(expectedRow, actualRow);
    }

    @Test
    public void getCol() {
        List<List<Double>> _matrix = new ArrayList<>();

        _matrix.add(new ArrayList<>(Arrays.asList(1., 3., 6., 5.)));
        _matrix.add(new ArrayList<>(Arrays.asList(1., 8., 7., 9.)));

        Matrix matrix = new MatrixClass(_matrix);

        List<Double> expectedCol = matrix.getCol(1);
        // Required output
        List<Double> actualCol = new ArrayList<>(Arrays.asList(3., 8.));

        assertEquals(expectedCol, actualCol);
    }

    @Test
    public void getMin()
    {
        List<List<Double>> _matrix = new ArrayList<>();

        _matrix.add(new ArrayList<>(Arrays.asList(3., 12., 6., 0.)));
        _matrix.add(new ArrayList<>(Arrays.asList(4., 8., 7., 9.)));

        Matrix matrix = new MatrixClass(_matrix);
        AlexNetKernelsVisualiser shower = new AlexNetKernelsVisualiser();
        assertEquals(0, (int)shower.getMin(matrix) );
    }

    @Test
    public void getMax()
    {
        List<List<Double>> _matrix = new ArrayList<>();

        _matrix.add(new ArrayList<>(Arrays.asList(3., 12., 6., 5.)));
        _matrix.add(new ArrayList<>(Arrays.asList(4., 8., 7., 9.)));

        Matrix matrix = new MatrixClass(_matrix);
        AlexNetKernelsVisualiser shower = new AlexNetKernelsVisualiser();
        assertEquals(12, (int)shower.getMax(matrix) );
    }

    @Test
    public void t() {
        List<List<Double>> _matrix1 = new ArrayList<>();

        _matrix1.add(new ArrayList<>(Arrays.asList(1., 5., 6.)));
        _matrix1.add(new ArrayList<>(Arrays.asList(1., 3., 7.)));

        Matrix matrix1 = new MatrixClass(_matrix1);

        MatrixClass expectedMatrix = (MatrixClass)matrix1.t();

        List<List<Double>> _matrix2 = new ArrayList<>();

        _matrix2.add(new ArrayList<>(Arrays.asList(1., 1.)));
        _matrix2.add(new ArrayList<>(Arrays.asList(5., 3.)));
        _matrix2.add(new ArrayList<>(Arrays.asList(6., 7.)));

        MatrixClass actualMatrix = new MatrixClass(_matrix2);

        assertEquals(expectedMatrix.getMatrix(), actualMatrix.getMatrix());
    }

    @Test
    public void dot() {
        List<List<Double>> _matrix1 = new ArrayList<>();
        List<List<Double>> _matrix2 = new ArrayList<>();

        _matrix1.add(new ArrayList<>(Arrays.asList(1., 5.)));
        _matrix1.add(new ArrayList<>(Arrays.asList(1., 3.)));
        _matrix2.add(new ArrayList<>(Arrays.asList(1., 1.)));
        _matrix2.add(new ArrayList<>(Arrays.asList(5., 3.)));

        Matrix matrix1 = new MatrixClass(_matrix1);
        Matrix matrix2 = new MatrixClass(_matrix2);

        MatrixClass expectedMatrix = (MatrixClass)matrix1.dot(matrix2);

        // Required output
        List<List<Double>> _actualMatrix = new ArrayList<>();
        _actualMatrix.add(new ArrayList<>(Arrays.asList(1., 5.)));
        _actualMatrix.add(new ArrayList<>(Arrays.asList(5., 9.)));

        MatrixClass actualMatrix = new MatrixClass(_actualMatrix);

        assertEquals(expectedMatrix.getMatrix(), actualMatrix.getMatrix());
    }

    @Test
    public void x() {
        List<List<Double>> _matrix1 = new ArrayList<>();
        List<List<Double>> _matrix2 = new ArrayList<>();

        _matrix1.add(new ArrayList<>(Arrays.asList(1., 6.)));
        _matrix1.add(new ArrayList<>(Arrays.asList(2., 2.)));
        _matrix2.add(new ArrayList<>(Arrays.asList(0., 9.)));
        _matrix2.add(new ArrayList<>(Arrays.asList(5., 6.)));

        Matrix matrix1 = new MatrixClass(_matrix1);
        Matrix matrix2 = new MatrixClass(_matrix2);

        MatrixClass expectedMatrix = (MatrixClass)matrix1.x(matrix2);
        // Required output
        List<List<Double>> _actualMatrix = new ArrayList<>();
        _actualMatrix.add(new ArrayList<>(Arrays.asList(30., 45.)));
        _actualMatrix.add(new ArrayList<>(Arrays.asList(10., 30.)));

        MatrixClass actualMatrix = new MatrixClass(_actualMatrix);

        assertEquals(expectedMatrix.getMatrix(), actualMatrix.getMatrix());
    }

    @Test
    public void convoluteTest() {
        List<List<Double>> _matrix1 = new ArrayList<>();
        List<List<Double>> _matrix2 = new ArrayList<>();

        _matrix1.add(new ArrayList<>(Arrays.asList(1., 6.)));
        _matrix1.add(new ArrayList<>(Arrays.asList(2., 2.)));
        _matrix2.add(new ArrayList<>(Arrays.asList(0., 9.)));
        _matrix2.add(new ArrayList<>(Arrays.asList(5., 6.)));

        Matrix matrix1 = new MatrixClass(_matrix1);
        Matrix matrix2 = new MatrixClass(_matrix2);
        // Required output
        Double expected = 76d;

        assertEquals(matrix1.convolute(matrix2), expected);
    }

    @Test
    public void getSize() {
        List<List<Double>> _matrix = new ArrayList<>();
        _matrix.add(new ArrayList<>(Arrays.asList(1., 4., 6., 8.)));
        _matrix.add(new ArrayList<>(Arrays.asList(1., 3., 8., 6.)));

        Matrix matrix = new MatrixClass(_matrix);

        int expectedSize = matrix.getSize(2);
        // Required output
        int actualSize = 4;

        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void subMatrixTest() {

        List<List<Double>> _matrix1 = new ArrayList<>();
        _matrix1.add(new ArrayList<>(Arrays.asList(1., 2., 3., 4.)));
        _matrix1.add(new ArrayList<>(Arrays.asList(5., 6., 7., 8.)));
        _matrix1.add(new ArrayList<>(Arrays.asList(9., 10., 11., 12.)));
        _matrix1.add(new ArrayList<>(Arrays.asList(13., 14., 15., 16.)));

        Matrix matrix1 = new MatrixClass(_matrix1);

        List<List<Double>> expected = new ArrayList<>();
        expected.add(new ArrayList<>(Arrays.asList(7., 8.)));
        expected.add(new ArrayList<>(Arrays.asList(11., 12.)));
        expected.add(new ArrayList<>(Arrays.asList(15., 16.)));

        Matrix matrix2 = new MatrixClass(expected);

        assertThat(matrix1.subMatrix(1, 2, 3, 2), is(matrix2));

    }

    @Test
    public void fromString(){
        List<List<Double>> _matrix1 = new ArrayList<>();
        _matrix1.add(new ArrayList<>(Arrays.asList(1., 2., 3., 4.)));
        _matrix1.add(new ArrayList<>(Arrays.asList(5., 6., 7., 8.)));

        Matrix expected_matrix = new MatrixClass(_matrix1);
        String string = "1.,2.,3.,4.;'\n'5.,6.,7.,8.";

        Matrix actual_matrix = MatrixClass.fromString(string);

        assertEquals(expected_matrix.getMatrix(),actual_matrix.getMatrix());

    }


}