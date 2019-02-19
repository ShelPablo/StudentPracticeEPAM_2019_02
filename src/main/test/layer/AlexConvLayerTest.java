package layer;

import imageprocessor.ImageProcessorClass;
import layer.conv.AlexConv1Layer;
import matrix.Matrix;
import org.junit.Test;

import java.util.List;

public class AlexConvLayerTest {

    @Test
    public void apply() throws InterruptedException {
        ImageProcessorClass imageProcessorClass = new ImageProcessorClass();
        List<Matrix> input = imageProcessorClass.loadImage("src/main/resources/TrainingSet/rub50/1.jpg");

        Layer acl = new AlexConv1Layer();
        acl.apply(input);

        List<Matrix> output = acl.getOutput();
        System.out.println(output.size());
        System.out.println(output.get(0).getSize(1));

        // Show the result of the convolution with the first filter (kernel)
        new ImageProcessorClass().showImageAfter3dConvolution(acl.getOutput(), 0);
        Thread.sleep(1000);
    }
}