package imageprocessor;

import imageprocessor.ImageProcessorClass;
import org.junit.Test;

public class ImageProcessorTest {

    @Test
    public void showImage() throws InterruptedException {
        ImageProcessorClass imageProcessorClass = new ImageProcessorClass();
        imageProcessorClass.loadImage("src/main/resources/1.jpg");
        imageProcessorClass.showImage();
        Thread.sleep(3000);
    }
}