package layer;

import matrix.Matrix;

import java.util.List;

public interface Layer {

    List<Matrix> setInput(List<Matrix> input);

    List<Matrix> getOutput();

    List<Matrix> apply(List<Matrix> input);
}
