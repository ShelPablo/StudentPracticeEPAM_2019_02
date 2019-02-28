package imageprocessor;
import java.awt.*;

import java.awt.image.BufferedImage;

import java.io.IOException;

public class ImageCropper {
    public final int TARGET_WIDTH = 560;



    public final int TARGET_HEIGHT = 168;







    public BufferedImage cropImageToGivenSize(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {



        int imageType = originalImage.getType();



        if(imageType == 0) imageType = 1;



        System.out.println("Original Image Dimension: " + originalImage.getWidth() + "x" + originalImage.getHeight());







        float widthRatio = (float)originalImage.getWidth() / targetWidth;



        float heightRatio =(float)originalImage.getHeight() / targetHeight;







        BufferedImage resizedImage = originalImage;



        int resizedWidth = originalImage.getWidth();



        int resizedHeight = originalImage.getHeight();



        if(widthRatio > heightRatio){ //shrink to fixed height



            resizedWidth = Math.round(originalImage.getWidth() / heightRatio);



            resizedHeight = targetHeight;



        }else{ //shrink to fixed width



            resizedWidth = targetWidth;



            resizedHeight = Math.round(originalImage.getHeight() / widthRatio);



        }



        resizedImage = resizeImage(originalImage, 1, resizedWidth, resizedHeight);







        int startX = resizedWidth/2 - targetWidth/2;



        int startY = resizedHeight/2 - targetHeight/2;



        BufferedImage SubImage = resizedImage.getSubimage(0, 0, targetWidth, targetHeight);











        System.out.println("Image cropped successfully: ");



        return SubImage;



    }







    private  BufferedImage resizeImage(BufferedImage originalImage, int type,



                                       Integer img_width, Integer img_height)



    {



        BufferedImage resizedImage = new BufferedImage(img_width, img_height, type);



        Graphics2D g = resizedImage.createGraphics();



        g.drawImage(originalImage, 0, 0, img_width, img_height, null);



        g.dispose();







        return resizedImage;



    }
}
