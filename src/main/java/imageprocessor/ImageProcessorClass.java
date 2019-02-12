package imageprocessor;

import matrix.Matrix;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import java.util.List;

import javax.swing.*;
import java.awt.*;

public class ImageProcessorClass extends ImageProcessor {

    private ImageLoader imageLoader;

    private Image loadedImage;

    private String filename;

    private List<Matrix> image;

    public List<Matrix> loadImage(String filename) {
        this.imageLoader = new ImageLoaderClass();
        this.filename = filename;
        return image = imageLoader.loadImageAsMatrix(filename);
    };

    @Override
    public void showImage() {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                ImageFrame frame = new ImageFrame(image);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class ImageFrame extends JFrame
{
    private Image image;

    public ImageFrame(List<Matrix> matrixList)
    {

        Matrix redLayer =  matrixList.get(0);
        Matrix greenLayer =  matrixList.get(1);
        Matrix blueLayer =  matrixList.get(2);
        int HEIGHT = matrixList.get(0).getSize(2);
        int WIDTH = matrixList.get(0).getSize(1);

        BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D)img.getGraphics();
        for(int i = 0; i < WIDTH; i++) {
            for(int j = 0; j < HEIGHT; j++) {
                int red = (int)redLayer.get(i,j);
                int green = (int)greenLayer.get(i,j);
                int blue = (int)blueLayer.get(i,j);
                g.setColor(new Color(red, green, blue));
                g.fillRect(i, j, 1, 1);
            }
        }
        Image showimage = img;
        setTitle("ImageObserver");
        int imageWidth = showimage.getWidth(this);
        int imageHeight = showimage.getHeight(this);
        if (imageHeight < 200 || imageWidth < 200)
        {
            setTitle("UpscaledImageObserver");
            int scaleCoef = 1;
            if (imageHeight >= imageWidth) {scaleCoef = 200/imageHeight;}
            else { scaleCoef = 200/imageWidth; }

            showimage = showimage.getScaledInstance(imageWidth*scaleCoef, imageHeight*scaleCoef, Image.SCALE_SMOOTH);
        }
        setSize(showimage.getWidth(this), showimage.getHeight(this));
        ImageComponent component = new ImageComponent(showimage);
        add(component);
    }
}

class ImageComponent extends JComponent
{
    public ImageComponent(Image image)
    {
        this.image = image;
    }

    public void paintComponent(Graphics g) {
        if (image == null) return;
        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);
        if (imageHeight < 200 || imageWidth < 200)
        {
            int scaleCoef = 1;
            if (imageHeight >= imageWidth) {scaleCoef = 200/imageHeight;}
            else { scaleCoef = 200/imageWidth; }

            image = image.getScaledInstance(imageWidth*scaleCoef, imageHeight*scaleCoef, Image.SCALE_SMOOTH);
        }
        // Display image in the left corner
        g.drawImage(image, 0, 0, null);
    }
    private Image image;
}
