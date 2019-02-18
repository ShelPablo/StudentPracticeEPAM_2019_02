package layer;

import imageprocessor.ImageProcessorClass;
import matrix.Matrix;
import org.junit.Test;

import java.util.List;

public class AlexConvolutionLayerTest {

    @Test
    public void apply() throws InterruptedException {
        ImageProcessorClass imageProcessorClass = new ImageProcessorClass();
        List<Matrix> input = imageProcessorClass.loadImage("src/main/resources/rosy.jpg");

        AlexConvolutionLayer acl = new AlexConvolutionLayer(input);
        acl.apply();

        List<Matrix> output = acl.getOutput();
        System.out.println(output.size());
        System.out.println(output.get(0).getSize(1));

        // Show the result of the convolution with the first filter (kernel)
        acl.showImageAfter3dConvolution(0);
        Thread.sleep(15000);
    }
}