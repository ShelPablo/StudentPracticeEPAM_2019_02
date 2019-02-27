import imageprocessor.ImageLoader;
import imageprocessor.ImageLoaderClass;
import layer.Layer;
import layer.conv.AlexConv1Layer;
import layer.conv.Simple3dConvLayer;
import layer.fully.FinalLayer;
import layer.fully.FullyConnectedLayerBuilder;
import layer.pool.MaxPoolLayer;
import matrix.Matrix;
import neuralnetworks.CNN;
import neuralnetworks.LayeredNeuralNetwork;
import neuralnetworks.SimpleCorrelator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {

/*    public static void main(String[] args) {

        //64x128
        Layer conv1 = new AlexConv1Layer();
        //16x32x96
        Layer conv2 = new Simple3dConvLayer(3, 3, 96, 2);
        //8x16
        Layer pool1 = new MaxPoolLayer(2, 2);
        //4x8
        Layer fully = new FullyConnectedLayerBuilder()
                .setLayersNumber(2)
                .switchToLayer(0).setSize(32, 16)
                .switchToLayer(1).setSize(16, 6)
                //.initMethod('random')
                .build();
        //32->16->6

        LayeredNeuralNetwork cnn = new CNN()
                .addLayer(conv1)
                .addLayer(conv2)
                .addLayer(pool1)
                .addLayer(fully);

       // cnn.train("folderTrain");

       // cnn.test("folderTest");

    }
*/

    public static void main(String[] args) {
        SimpleCorrelator simpleCorrelator = new SimpleCorrelator();
        //simpleCorrelator.trainFinalLayer();
        //simpleCorrelator.getThresholds();
        simpleCorrelator.setThresholds(new ArrayList<>(Arrays.asList(0.6,0.6,0.6,0.6,0.6,0.6)));
        String path = "TestSet/rub5000/100.jpg";
        ImageLoader imageLoader = new ImageLoaderClass();
        System.out.println(simpleCorrelator.apply(imageLoader.loadImageAsMatrix(path)));
    }
}
