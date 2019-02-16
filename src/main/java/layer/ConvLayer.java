package layer;

import matrix.Matrix;

import java.util.List;

public abstract class ConvLayer implements Layer {

    List<Matrix> kernel;

    List<Matrix> input;

    List<Matrix> output;

    @Override
    public List<Matrix> apply(List<Matrix> input) {
        return null;
    }


}
