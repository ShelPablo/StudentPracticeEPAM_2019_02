package imageprocessor;

import matrix.Matrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class ImageProcessor {

<<<<<<< HEAD
=======

    private ImageLoader imageLoader;

    private List<Matrix> image;
>>>>>>> 1b517098b6f0dcdf337ab1e98597fefc14f87203

    public List<Matrix> loadImage(String filename) {

        return image = imageLoader.loadImageAsMatrix(filename);
    }

    private ImageLoader imageLoader;

    private List<Matrix> image;



    public abstract void showImage();

}
