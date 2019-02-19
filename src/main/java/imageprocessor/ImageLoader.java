package imageprocessor;

import matrix.Matrix;

import java.util.List;

public interface ImageLoader {

    List<Matrix> loadImageAsMatrix(String filename);

}
