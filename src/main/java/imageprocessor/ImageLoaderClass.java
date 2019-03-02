package imageprocessor;

import matrix.Matrix;
import matrix.MatrixClass;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;


public class ImageLoaderClass extends JComponent implements ImageLoader
{
    private Image image;

    public Image getImage(String filename)
    {
        try
        {
            image = ImageIO.read(new File(filename));
            return image;
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }


    @Override
    public List<Matrix> loadImageAsMatrix(String filename) {

        URL url = this.getClass().getClassLoader().getResource(filename);
        File file = new File(filename);

//        try {
//            file = new File(url.toURI());
//        } catch (URISyntaxException e) {
//            file = new File(url.getPath());
//        }

        try
        {
            image = ImageIO.read(file);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }

        if(image == null) return null;
        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);
        List<List<Double>> redLayer =  new ArrayList<List<Double>>();
        List<List<Double>> greenLayer =  new ArrayList<List<Double>>();
        List<List<Double>> blueLayer =  new ArrayList<List<Double>>();
        BufferedImage img = (BufferedImage)image;
        for (int i = 0; i<imageWidth; i++)
        {
            redLayer.add(new ArrayList<Double>());
            greenLayer.add(new ArrayList<Double>());
            blueLayer.add(new ArrayList<Double>());
            for (int j = 0; j < imageHeight; j++)
            {
                int pixel = img.getRGB(i, j);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                redLayer.get(i).add((double)red);
                greenLayer.get(i).add((double)green);
                blueLayer.get(i).add((double)blue);
            }
        }
        Matrix red = new MatrixClass(redLayer);
        Matrix green = new MatrixClass(greenLayer);
        Matrix blue = new MatrixClass(blueLayer);

        List<Matrix> matrixList = new ArrayList<Matrix>(Arrays.asList(red, green, blue));
        return  matrixList;
    }
}