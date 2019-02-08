import matrix.Matrix;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args)
    {
        ArrayList<ArrayList<Double>> array = new ArrayList<ArrayList<Double>>();
        array.add(new ArrayList<Double>(){{add(1.0);add(2.0);add(3.0);}});
        array.add(new ArrayList<Double>(){{add(4.0);add(5.0);add(6.0);}});

        Matrix matrix = new Matrix(array);
        ArrayList<ArrayList<Double>> multArray = new ArrayList<ArrayList<Double>>();
        multArray.add(new ArrayList<Double>(){{add(4.0);add(8.0);}});
        multArray.add(new ArrayList<Double>(){{add(5.0);add(11.0);}});
        multArray.add(new ArrayList<Double>(){{add(3.0);add(1.5);}});

        Matrix bmatrix = new Matrix(multArray);
        matrix = matrix.x(bmatrix);
        for (ArrayList<Double> row : matrix.getMatrix())
        {
            System.out.println();
            row.forEach((n) -> System.out.print(n + " "));
        }
    }
}
