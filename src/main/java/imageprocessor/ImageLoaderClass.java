package imageprocessor;

import matrix.Matrix;
import matrix.MatrixClass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ImageLoaderClass implements ImageLoader {

    public ImageLoaderClass(){}

    public List<Matrix> loadImageAsMatrix(String filename){

        BufferedImage bufImg = null;
        try
        {
            bufImg = ImageIO.read(new File(filename));
        } catch (IOException e){e.printStackTrace();}

        if (bufImg != null){
            List<List<Double>> _matrixOfRed = new ArrayList<>();
            List<List<Double>> _matrixOfGreen = new ArrayList<>();
            List<List<Double>> _matrixOfBlue = new ArrayList<>();

            int width = bufImg.getWidth();
            int height = bufImg.getHeight();

            for (int i = 0; i < height; i++) {
                _matrixOfRed.add(new ArrayList<>(Collections.nCopies(width, 0.)));
                _matrixOfGreen.add(new ArrayList<>(Collections.nCopies(width, 0.)));
                _matrixOfBlue.add(new ArrayList<>(Collections.nCopies(width, 0.)));
            }

            for (int y = 0; y < height; y++){
                for (int x = 0; x < width; x++){
                    Color color = new Color(bufImg.getRGB(x,y));
                    _matrixOfRed.get(y).set(x, (double) color.getRed());
                    _matrixOfGreen.get(y).set(x, (double) color.getGreen());
                    _matrixOfBlue.get(y).set(x, (double) color.getBlue());
                }
            }

            Matrix matrixOfRed = new MatrixClass(_matrixOfRed);
            Matrix matrixOfGreen = new MatrixClass(_matrixOfGreen);
            Matrix matrixOfBlue = new MatrixClass(_matrixOfBlue);

            return new ArrayList<>(Arrays.asList(matrixOfRed, matrixOfGreen, matrixOfBlue));
        }

        else throw new NullPointerException("Failed to get image matrices");
    }
}
