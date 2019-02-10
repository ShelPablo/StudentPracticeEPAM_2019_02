package imageprocessor;

import matrix.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class ImageLoaderClass implements ImageLoader {

    public List<Matrix> loadImageAsMatrix(String filename){
        BufferedImage bufImg = null;
        try
        {
            bufImg = ImageIO.read(new File(filename));
        } catch (IOException e){e.printStackTrace();}

        List<List<Double>> red_matrix = new ArrayList<>();
        List<List<Double>> green_matrix = new ArrayList<>();
        List<List<Double>> blue_matrix = new ArrayList<>();

        int height = bufImg.getHeight();
        int width = bufImg.getWidth();

        for(int y = 0; y < height; y++) {
            List<Double> red_line = new ArrayList<>();
            List<Double> green_line = new ArrayList<>();
            List<Double> blue_line = new ArrayList<>();

            for(int x = 0; x < width; x++) {
                Color color = new Color(bufImg.getRGB(x,y));

                red_line.add((double) color.getRed());
                green_line.add((double) color.getGreen());
                blue_line.add((double) color.getBlue());
            }
            red_matrix.add(red_line);
            green_matrix.add(green_line);
            blue_matrix.add(blue_line);
        }

        List<Matrix> imgMatrix = new ArrayList<>();

        imgMatrix.add(new MatrixClass(red_matrix));
        imgMatrix.add(new MatrixClass(green_matrix));
        imgMatrix.add(new MatrixClass(blue_matrix));

        return imgMatrix;
    }
}
