package mathfunctions;

import static java.lang.Math.exp;

public class Sigmoid  {

    private Sigmoid() throws NoSuchMethodException {
        throw new NoSuchMethodException();
    }

    public static double sigmoid(double x)
    {
        double y=1/(1+exp(-x));
        return y;
    }

    public static double sigmoidDerivative(double sigmoid)
    {
        //use output

        return (1-sigmoid)*sigmoid;
    }

}
