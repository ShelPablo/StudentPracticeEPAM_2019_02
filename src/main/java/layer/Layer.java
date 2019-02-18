package layer;

<<<<<<< HEAD
public interface Layer {
    void apply();
=======
import matrix.Matrix;

import java.util.List;

public interface Layer {

    Layer setInput(List<Matrix> input);

    List<Matrix> getOutput();

    List<Matrix> apply(List<Matrix> input);
>>>>>>> e4a47d4c097699924763e8f74e6e06c96d769afc
}
