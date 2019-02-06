package imageprocessor;

import matrix.Matrix;

public abstract class ImageProcessor {

    private ImageLoader imageLoader;

    private Matrix image;

    public Matrix loadImage(String filename) {
        return image = imageLoader.loadImageAsMatrix(filename);
    };

    public abstract void showImage();

}
