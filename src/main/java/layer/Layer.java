package layer;

import matrix.Matrix;

import java.util.List;

public interface Layer {
    List<Matrix> apply(List<Matrix> input);
}
