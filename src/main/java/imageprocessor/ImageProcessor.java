package imageprocessor;

import matrix.Matrix;

import java.util.List;

public abstract class ImageProcessor {

    private ImageLoader imageLoader;

    private List<Matrix> image;

    public List<Matrix> loadImage(String filename) {
        return image = imageLoader.loadImageAsMatrix(filename);
    };

    public abstract void showImage();

}
