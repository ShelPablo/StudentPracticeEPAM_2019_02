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

    public ImageProcessorClass(List<Matrix> matrixList)
    {
        this.image = matrixList;
    }

    public ImageProcessorClass()
    {
        //Empty constructor
    }

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

    public void showImageAfter3dConvolution(List<Matrix> output, int indexOfMatrix) {

        int _height = output.get(indexOfMatrix).getSize(1);
        int _width = output.get(indexOfMatrix).getSize(2);

        BufferedImage bufImg = new BufferedImage(_width, _height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < _height; i++){
            for (int j = 0; j < _width; j++){
                Double value = output.get(indexOfMatrix).get(i, j);
                if (value < 0){
                    value=0.0;
                }
                if (value >255){
                    value=255.0;
                }
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
        if (HEIGHT < 50 || WIDTH < 50)
        {
            setTitle("UpscaledImageObserver");
            int scaleCoef = 1;
            if (HEIGHT >= WIDTH) {scaleCoef = 150/WIDTH;}
            else { scaleCoef = 150/WIDTH; }

            showimage = showimage.getScaledInstance(WIDTH*scaleCoef, HEIGHT*scaleCoef, Image.SCALE_SMOOTH);
        }
        else if (HEIGHT < 200 || WIDTH < 200)
        {
            setTitle("UpscaledImageObserver");
            int scaleCoef = 1;
            if (HEIGHT >= WIDTH) {scaleCoef = 600/WIDTH;}
            else { scaleCoef = 600/WIDTH; }

            showimage = showimage.getScaledInstance(WIDTH*scaleCoef, HEIGHT*scaleCoef, Image.SCALE_SMOOTH);
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
        g.drawImage(image, 0, 0, null);
    }
    private Image image;
}
