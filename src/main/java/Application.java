import layer.Layer;
import layer.conv.AlexConv1Layer;
import layer.conv.Simple3dConvLayer;
import layer.fully.FullyConnectedLayerBuilder;
import layer.pool.MaxPoolLayer;
import neuralnetworks.CNN;
import neuralnetworks.LayeredNeuralNetwork;

public class Application {

    public static void main(String[] args) {

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
}
