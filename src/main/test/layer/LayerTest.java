package layer;

import imageprocessor.ImageLoader;
import imageprocessor.ImageLoaderClass;
import imageprocessor.ImageProcessor;
import imageprocessor.ImageProcessorClass;
import matrix.Matrix;
import org.junit.Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class LayerTest {

    @Test
    public void showImage() throws InterruptedException, IOException {

        KernelsReader kernelsReader = new KernelsReader();
        List<List<Matrix>> k = kernelsReader.readKernelsFromFile("/home/bishamon/Документы/EPAM/2/StudentPracticeEPAM_2019_02/src/main/resourses/AlexK.txt");

        Convolute3dAlex convolute3dAlex = new Convolute3dAlex();
        ImageLoader imageLoader = new ImageLoaderClass();
        ImageProcessor imageProcessor = new ImageProcessorClass(imageLoader);

        Matrix m =  convolute3dAlex.convolute3d(imageProcessor.loadImage("2.png"),k.get(0)).get(0);

        int height = m.getSize(1);
        int width = m.getSize(2);

        double min = m.get(0,0);
        double max = m.get(0,0);

        //find max and min
        for(int i=0;i<height;i++){
            for (int j=0;j<width;j++){
                if(m.get(i,j)<min){
                    min=m.get(i,j);
                }
                if(m.get(i,j)>max){
                    max=m.get(i,j);
                }
            }
        }

        //window
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(width,height));
        frame.setVisible(true);
        BufferedImage bufImg = new BufferedImage( width,height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                //normalized
                System.out.println((int) (((m.get(i,j)+min)/(max+min))*255));
                Color color = new Color((int) (((m.get(i,j)-min)/(max-min))*255), (int) (((m.get(i, j)-min)/(max-min))*255), (int) (((m.get(i, j)-min)/(max-min)))*255);
                int rgb = color.getRGB();
                bufImg.setRGB(j, i, rgb);
            }
        }

        Graphics g = frame.getGraphics();
        g.drawImage(bufImg, 0, 0, width, height, null);
       //ImageIO.write(bufImg, "PNG", new File("meow2.png"));
        Thread.sleep(3000);
    }



}