package imageprocessor;

import matrix.Matrix;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class ImageProcessorClass extends ImageProcessor {


    private ImageLoader imageLoader;

    private List<Matrix> image;

    public ImageProcessorClass(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;

    }

    public List<Matrix> loadImage(String filename) {

        return image = imageLoader.loadImageAsMatrix(filename);
    }

    @Override
    public void showImage() {

        int width = image.get(0).getSize(2);
        int height = image.get(0).getSize(1);

        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(width,height));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        BufferedImage bufImg = new BufferedImage( width,height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color color = new Color((int) image.get(0).get(i, j), (int) image.get(1).get(i, j), (int) image.get(2).get(i, j));
                int rgb = color.getRGB();
                bufImg.setRGB(j, i, rgb);
            }
        }

        Graphics g = frame.getGraphics();
        g.drawImage(bufImg, 0, 0, width, height, null);

    }

}
