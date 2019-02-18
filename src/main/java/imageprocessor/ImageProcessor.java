package imageprocessor;

import matrix.Matrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class ImageProcessor {

<<<<<<< HEAD

    private ImageLoader imageLoader;
=======
    private ImageLoader imageLoader = new ImageLoaderClass();
>>>>>>> e4a47d4c097699924763e8f74e6e06c96d769afc

    private List<Matrix> image;

    public List<Matrix> getImage(){
        return image;
    }

    public List<Matrix> loadImage(String filename) {

        return image = imageLoader.loadImageAsMatrix(filename);
    }
<<<<<<< HEAD


=======
>>>>>>> e4a47d4c097699924763e8f74e6e06c96d769afc

    public abstract void showImage();

}
