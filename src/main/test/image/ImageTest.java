import imageprocessor.ImageLoader;
import imageprocessor.ImageLoaderClass;
import imageprocessor.ImageProcessor;
import imageprocessor.ImageProcessorClass;
import org.junit.Test;
public class ImageTest {

    @Test
    public void showImage() throws InterruptedException {
        ImageLoader imageLoader = new ImageLoaderClass();
        ImageProcessor imageProcessor = new ImageProcessorClass(imageLoader);
        imageProcessor.loadImage("1.jpg");
        imageProcessor.showImage();
        Thread.sleep(1000);
    }

}