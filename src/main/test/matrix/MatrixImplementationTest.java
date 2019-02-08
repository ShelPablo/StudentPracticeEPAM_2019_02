package matrix;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MatrixImplementationTest {


    public Matrix getTestMatrix()
    {
        ArrayList<ArrayList<Double>> array = new ArrayList<ArrayList<Double>>();
        array.add(new ArrayList<Double>(){{add(1.0);add(2.0);add(3.0);}});
        array.add(new ArrayList<Double>(){{add(4.0);add(5.0);add(6.0);}});
        return new Matrix(array);
    }

    public Matrix getBMatrix()
    {
        ArrayList<ArrayList<Double>> array = new ArrayList<ArrayList<Double>>();
        array.add(new ArrayList<Double>(){{add(2.0);add(9.0);}});
        array.add(new ArrayList<Double>(){{add(8.0);add(5.0);}});
        array.add(new ArrayList<Double>(){{add(11.0);add(3.0);}});
        return new Matrix(array);
    }



    @Test
    public void getMatrix() {
    Matrix matrix = getTestMatrix();
        ArrayList<ArrayList<Double>> array = new ArrayList<ArrayList<Double>>();
        array.add(new ArrayList<Double>(){{add(1.0);add(2.0);add(3.0);}});
        array.add(new ArrayList<Double>(){{add(4.0);add(5.0);add(6.0);}});
        assertEquals(array, matrix.getMatrix());
    }

    @Test
    public void get() {
        Matrix matrix = getTestMatrix();
        assertEquals(1, (int)matrix.get(0,0));
        assertEquals(5, (int)matrix.get(1,1));
    }


    @Test
    public void getRow() {
        ArrayList<Double> expected = new ArrayList<Double>(){{add(1.0);add(2.0);add(3.0);}};
        assertEquals(expected, getTestMatrix().getRow(0));
    }

    @Test
    public void getCol() {
        ArrayList<Double> expected = new ArrayList<Double>(){{add(2.0);add(5.0);}};
        assertEquals(expected, getTestMatrix().getCol(1));
    }

    @Test
    public void t() {
        Matrix matrix = getTestMatrix();
        matrix = matrix.t();
        ArrayList<ArrayList<Double>> expected = new ArrayList<ArrayList<Double>>();
        expected.add(new ArrayList<Double>(){{add(1.0);add(4.0);}});
        expected.add(new ArrayList<Double>(){{add(2.0);add(5.0);}});
        expected.add(new ArrayList<Double>(){{add(3.0);add(6.0);}});
        Matrix expectedMatrix = new Matrix(expected);
        assertEquals(expected, matrix.getMatrix());
    }

    @Test
    public void dot() {
        Matrix matrix = getTestMatrix();
        ArrayList<ArrayList<Double>> bArray = new ArrayList<ArrayList<Double>>();
        bArray.add(new ArrayList<Double>(){{add(1.0);add(4.0);}});
        bArray.add(new ArrayList<Double>(){{add(2.5);add(5.0);}});
        Matrix bMatrix = new Matrix(bArray);

        ArrayList<ArrayList<Double>> expected = new ArrayList<ArrayList<Double>>();
        expected.add(new ArrayList<Double>(){{add(1.0);add(8.0);add(3.0);}});
        expected.add(new ArrayList<Double>(){{add(10.0);add(25.0);add(6.0);}});
        matrix = matrix.dot(bMatrix);
        assertEquals(expected, matrix.getMatrix());
    }

    @Test
    public void x() {
        Matrix matrix = this.getTestMatrix();
		
        ArrayList<ArrayList<Double>> multArray = new ArrayList<ArrayList<Double>>();
        multArray.add(new ArrayList<Double>(){{add(4.0);add(8.0);}});
        multArray.add(new ArrayList<Double>(){{add(5.0);add(11.0);}});
        multArray.add(new ArrayList<Double>(){{add(9.0);add(2.0);}});

        ArrayList<ArrayList<Double>> expectedArr = new ArrayList<ArrayList<Double>>();
        expectedArr.add(new ArrayList<Double>(){{add(41.0);add(36.0);}});
        expectedArr.add(new ArrayList<Double>(){{add(95.0);add(99.0);}});

        Matrix bmatrix = new Matrix(multArray);
        matrix = matrix.x(bmatrix);
        assertEquals(matrix.getMatrix(), expectedArr);
    }

    @Test
    public void getSize() {
        Matrix matrix = getTestMatrix();
        assertEquals(2, matrix.getSize(1));
        assertEquals(3, matrix.getSize(2));
    }
}