package tests;

import matrix.MatrixClass;
import matrix.MatrixError;
import org.junit.*;


import java.util.*;

import static junit.framework.TestCase.assertEquals;

public class MatrixTests {
    @Test
    public void call() {

        Double[][] matrix1 =
                {{2d, -3d},
                        {4d, -6d}};
        List<List<Double>> myMatrix = ToListFromArray(matrix1);
        Double[][] array =
                {{9d, -6d},
                        {6d, -4d}};
        List<List<Double>> matrix2 = ToListFromArray(array);
        Double[] array2 = {2d, -3d};
        List<Double> getRowRes = ToListFromArray(array2);
        array2 = new Double[]{-3d, -6d};
        List<Double> getColRes = ToListFromArray(array2);
        array = new Double[][]{{2d, 4d},
                {-3d, -6d}};
        List<List<Double>> tRes = ToListFromArray(array);
        array = new Double[][]{{18d, 18d},
                {24d, 24d}};
        List<List<Double>> dotRes = ToListFromArray(array);
        array = new Double[][]{{0d, 0d},
                {0d, 0d}};
        List<List<Double>> xRes = ToListFromArray(array);
        MatrixClass matrix = new MatrixClass(myMatrix);

        assertEquals(-6d, matrix.get(1, 1));
        assertEquals(getRowRes, matrix.getRow(0));
        assertEquals(getColRes, matrix.getCol(1));
        assertEquals(tRes, matrix.t().getMatrix());
        assertEquals(dotRes, (matrix.dot(new MatrixClass(matrix2))).getMatrix());
        assertEquals(xRes, (matrix.x(new MatrixClass(matrix2))).getMatrix());
        assertEquals(2, matrix.getSize(1));
    }

    @Test(expected = MatrixError.class)
    public void Err() {

        Double[][] matrix1 =
                {{2d, -3d},
                        {4d, -6d}};
        List<List<Double>> matrix1_1 = ToListFromArray(matrix1);
        MatrixClass matrix = new MatrixClass(matrix1_1);
        List<Double> testrow = new ArrayList<Double>();
        testrow.add(0d);
        List<List<Double>> test = new ArrayList<List<Double>>();
        test.add(testrow);
        matrix.x(new MatrixClass(test));
        matrix.dot(new MatrixClass(test));

        matrix.getSize(0);
        matrix.getCol(-1);
        matrix.getRow(27);
        matrix.get(27, 0);
    }

    //I hate java
    public static List<List<Double>> ToListFromArray(Double[][] stupidArg) {
        List<List<Double>> strupidVar = new ArrayList<List<Double>>();

        for (Double[] row : stupidArg) {
            strupidVar.add(Arrays.asList(row));
        }
        return strupidVar;
    }

    public static List<Double> ToListFromArray(Double[] var) {

        return Arrays.asList(var);
    }


}
