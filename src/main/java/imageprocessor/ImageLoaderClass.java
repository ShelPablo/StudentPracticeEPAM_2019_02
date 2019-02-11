package imageprocessor;

import matrix.Matrix;
import matrix.MatrixClass;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ImageLoaderClass implements ImageLoader {
    public List<Matrix> loadImageAsMatrix(String filename) {
        BufferedImage bufImg = null;
        try {
            bufImg = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<List<Double>> red = new ArrayList<>();
        List<List<Double>> green = new ArrayList<>();
        List<List<Double>> blue = new ArrayList<>();

        if (bufImg == null) {
            throw new RuntimeException("Image Error");
        } else {
            for (int i = 0; i < bufImg.getHeight(); i++) {
                List<Double> redRow = new ArrayList<>();
                List<Double> greenRow = new ArrayList<>();
                List<Double> blueRow = new ArrayList<>();
                for (int j = 0; j < bufImg.getWidth(); j++) {
                    Color color = new Color(bufImg.getRGB(j, i));
                    redRow.add((double) color.getRed());
                    greenRow.add((double) color.getGreen());
                    blueRow.add((double) color.getBlue());
                   }
                red.add(redRow);
                green.add(greenRow);
                blue.add(blueRow);
            }
        }

        List<Matrix> listMatrix = new ArrayList<>();
        listMatrix.add(new MatrixClass(red));
        listMatrix.add(new MatrixClass(green));
        listMatrix.add(new MatrixClass(blue));

        return listMatrix;
    }
}
