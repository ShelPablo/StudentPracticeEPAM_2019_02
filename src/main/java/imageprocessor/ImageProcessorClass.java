package imageprocessor;

import matrix.Matrix;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class ImageProcessorClass extends ImageProcessor {

    public ImageProcessorClass(){}

    public void showImage(){

        List<Matrix> matrixOfRGB = getImage();
        int height = matrixOfRGB.get(0).getSize(1);
        int width = matrixOfRGB.get(0).getSize(2);

        Matrix matrixOfRed = matrixOfRGB.get(0);
        Matrix matrixOfGreen = matrixOfRGB.get(1);
        Matrix matrixOfBlue = matrixOfRGB.get(2);

        BufferedImage bufImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                Double red = matrixOfRed.get(y,x);
                Double green = matrixOfGreen.get(y,x);
                Double blue = matrixOfBlue.get(y,x);
                Color color = new Color(red.intValue(),green.intValue(),blue.intValue());
                bufImg.setRGB(x, y, color.getRGB());
            }
        }

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(width, height);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bufImg, 0, 0, null);
            }
        };

        frame.add(panel);
        frame.setVisible(true);
    }

    // Shows the result of convolution with a specific filter
    public void showImageAfter3dConvolution(List<Matrix> output, int indexOfMatrix) {

        int _height = output.get(indexOfMatrix).getSize(1);
        int _width = output.get(indexOfMatrix).getSize(2);

        BufferedImage bufImg = new BufferedImage(_width, _height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < _height; i++){
            for (int j = 0; j < _width; j++){
                Double value = output.get(indexOfMatrix).get(i, j);
                Color color = new Color(value.intValue(), value.intValue(), value.intValue());
                bufImg.setRGB(j, i, color.getRGB());
            }
        }

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(_width, _height);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bufImg, 0, 0, null);
            }
        };

        frame.add(panel);
        frame.setVisible(true);
    }
}
