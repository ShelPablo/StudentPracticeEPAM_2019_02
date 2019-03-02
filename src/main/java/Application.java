import imageprocessor.ImageProcessor;
import imageprocessor.ImageProcessorClass;
import layer.Layer;
import layer.conv.AlexConv1Layer;
import layer.conv.Simple3dConvLayer;
import layer.fully.FullyConnectedLayerBuilder;
import layer.pool.MaxPoolLayer;
import matrix.Matrix;
import neuralnetworks.CNN;
import neuralnetworks.LayeredNeuralNetwork;
import neuralnetworks.SimpleCorrelator;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
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

/*    public static void main(String[] args) {
        SimpleCorrelator simpleCorrelator = new SimpleCorrelator();

        simpleCorrelator.trainFinalLayer();

        List<Double> thresholds = Arrays.asList(0.6, 0.5, 0.6, 0.6, 0.6, 0.6);

        simpleCorrelator.setThresholds(thresholds);

        System.out.println("");

        for (Double d: thresholds){
            System.out.println(d);
        }

        System.out.println("");

        ImageProcessor imageProcessor = new ImageProcessorClass();

        URL url = simpleCorrelator.getClass().getClassLoader().getResource("banknote.jpg");
        File image = null;
        try {
            image = new File(url.toURI());
        } catch (URISyntaxException e) {
            image = new File(url.getPath());
        }

        String separator ;
        String _char;
        if(File.separatorChar=='/'){
            separator = "/";
            _char = "/";
        }else{
            separator ="\\\\";
            _char = "\\";
        }

        String[] path = image.getPath().split(separator);
        String relativePath = path[path.length-3]+_char+path[path.length-2]+_char+path[path.length-1];

        List<Matrix> input = imageProcessor.loadImage(relativePath);

        List<Boolean> result = simpleCorrelator.apply(input);

        System.out.println("");
        for (Boolean r: result){
            System.out.println(r);
        }
        System.out.println("");
        System.out.println("");

        /*for (File img : image.listFiles()){
            String[] path = img.getPath().split(separator);
            String relativePath = path[path.length-3]+_char+path[path.length-2]+_char+path[path.length-1];

            List<Matrix> input = imageProcessor.loadImage(relativePath);

            List<Boolean> result = simpleCorrelator.apply(input);

            System.out.println("");
            for (Boolean r: result){
                System.out.println(r);
            }
            System.out.println("");
            System.out.println("");
        }*/

    }
*/






}