import imageprocessor.*;
import matrix.*;

import java.util.List;

public class Program {
    public static void main(String[] args)
    {
        ImageLoader loader = new ImageLoaderClass();
        ImageProcessor proc = new ImageProcessorClass();
        proc.loadImage("test.png");
        proc.showImage();
    }
}
