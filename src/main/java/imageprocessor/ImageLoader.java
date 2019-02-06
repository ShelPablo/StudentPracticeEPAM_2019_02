package imageprocessor;

import matrix.Matrix;

public interface ImageLoader {

    Matrix loadImageAsMatrix(String filename);
}
