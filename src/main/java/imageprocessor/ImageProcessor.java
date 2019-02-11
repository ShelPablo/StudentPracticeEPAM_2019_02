package imageprocessor;

import matrix.Matrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class ImageProcessor {

    //класс изображения(?) будет наследовать этот абстрактный класс и реализовывать loadImage - полная изоляция таким образом

    private ImageLoader imageLoader;//типо "объект интерфейса" - чтобы матрица не знала откуда она взялась

    private List<Matrix> image;//возвращаемое значение  в размере 3 матриц!

    public List<Matrix> loadImage(String filename) {

        return image = imageLoader.loadImageAsMatrix(filename);
    }

    public abstract void showImage();

}
