package imageprocessor;

import matrix.Matrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class ImageProcessor {


    private ImageLoader imageLoader = new ImageLoaderClass();

    private List<Matrix> image;

    public List<Matrix> getImage(){
        return image;
    }

    public List<Matrix> loadImage(String filename) {

        return image = imageLoader.loadImageAsMatrix(filename);
    }

    public abstract void showImage();

}
