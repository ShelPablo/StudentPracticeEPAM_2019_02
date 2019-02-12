package imageprocessor;

import matrix.Matrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class ImageProcessor {


    public List<Matrix> loadImage(String filename) {

        return image = imageLoader.loadImageAsMatrix(filename);
    }

    private ImageLoader imageLoader;

    private List<Matrix> image;



    public abstract void showImage();

}
