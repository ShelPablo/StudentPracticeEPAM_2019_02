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
import java.util.List;

public class ImageLoaderC implements ImageLoader {

    @Override
    public List<Matrix> loadImageAsMatrix(String filename) {
        //image download
        //File folderInput = new File(filename);
        BufferedImage folderImage = null;
        try
        {
            folderImage = ImageIO.read(new File(filename));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        //getting colors
        if (folderImage!=null){
            int width = folderImage.getWidth();
            int height = folderImage.getHeight();
            List<List<Double>> redMatrix = new ArrayList<>();
            List<List<Double>> greenMatrix = new ArrayList<>();
            List<List<Double>> blueMatrix = new ArrayList<>();

            for (int i = 0; i<height;i++){
                List<Double> rowRedMatrix = new ArrayList<>();
                List<Double> rowGreenMatrix = new ArrayList<>();
                List<Double> rowBlueMatrix = new ArrayList<>();
                for(int j = 0; j<width; j++){
                    Color color = new Color(folderImage.getRGB(j,i));
                    double red = (double) color.getRed();
                    double green = (double) color.getGreen();
                    double blue = (double) color.getBlue();
                    rowRedMatrix.add(red);
                    rowBlueMatrix.add(blue);
                    rowGreenMatrix.add(green);
                }
                redMatrix.add(rowRedMatrix);
                greenMatrix.add(rowGreenMatrix);
                blueMatrix.add(rowBlueMatrix);
            }
            Matrix Red = new MatrixClass(redMatrix);
            Matrix Green = new MatrixClass(greenMatrix);
            Matrix Blue = new MatrixClass(blueMatrix);

            return new ArrayList<>(Arrays.asList(Red,Green,Blue));

        }
        else throw new NullPointerException("No image");
    }
}
