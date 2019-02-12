package imageprocessor;

import matrix.Matrix;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class ImageProcessorC extends ImageProcessor{


    private ImageLoader imageLoader;

    private List<Matrix> image;

    public List<Matrix> loadImage(String filename) {

        return image = imageLoader.loadImageAsMatrix(filename);
    }

    private ImageProcessorC (ImageLoader imageLoader){
        this.imageLoader = imageLoader;
    }


    @Override
    public void showImage() {

        JFrame jFrame = new JFrame();
        Dimension dimension = new Dimension(image.get(0).getSize(2), image.get(0).getSize(1));
        jFrame.setPreferredSize(dimension);
        jFrame.setDefaultCloseOperation
                (JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        int w = image.get(0).getSize(2);
        int h = image.get(0).getSize(1);
        BufferedImage _image = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i<h; i++){
            for (int j = 0; j<w;j++){
                Color color = new Color((int)image.get(0).get(i,j),(int)image.get(1).get(i,j),(int)image.get(2).get(i,j) );
                int rgb = color.getRGB();
                _image.setRGB(j,i,rgb);
            }
        }
        Graphics graphics = jFrame.getGraphics();
        graphics.drawImage(_image, 0,0,w,h,null);


    }
}
