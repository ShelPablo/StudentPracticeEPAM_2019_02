package neuralnetworks;

import imageprocessor.ImageProcessor;
import imageprocessor.ImageProcessorClass;
import layer.Layer;
import layer.conv.AlexConv1Layer;
import layer.conv.Simple3dConvLayer;
import layer.fully.FinalLayer;
import layer.fully.FullyConnectedLayerBuilder;
import layer.pool.MaxPoolLayer;
import matrix.Matrix;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleCorrelator {

    List<String> fileNames; // "rub50", "rub100", "rub200"

    List<List<Matrix>> coefficientsSet;

    Matrix weights; // fromString([ 0 0 0 0 0 0 ...
    // 0 0.1 0.1 0.1 ...
    // 0 0.1 0.2 0.2 ...

    private int trainingSetVolume = 0;

    //64x128
    Layer conv1 = new AlexConv1Layer();
    //16x32x96
    Layer pool1 = new MaxPoolLayer(2, 2);
    //8x16x96
    FinalLayer finalLayer = new FinalLayer();
    //6x1


    public List<Double> apply(List<Matrix> input) {
        //conv1.apply
        //pool1.apply
        //finalLayer.apply
        return null;
    }

    //Group - recognition class (100rub, 200rub ...)
    public void trainCoefSetForGroup(List<Matrix> inputRGBimage, int groupIdx) {
        //conv1.apply
        //pool1.apply -> result
        //(coef1 + coef2 +...)/N  (coef1 + coef2 +... +result)/(N+1) =>
        // coefficientsSet = coefficientsSet*N/(N+1) + result/(N+1)
        // N - trainingSetVolume
    }


    public void trainFinalLayer() {

        File folder = new File("src/main/resources/TrainingSet");

        HashMap map = new HashMap();
        map.put("rub50", 0);
        map.put("rub100", 1);
        map.put("rub200", 2);
        map.put("rub500", 3);
        map.put("rub1000", 4);
        map.put("rub5000", 5);

        ImageProcessor imageProcessor = new ImageProcessorClass();

        for (File group : folder.listFiles()) {
            String groupName = group.getName();
            for (File image : group.listFiles()) {

                trainCoefSetForGroup(imageProcessor.loadImage(image.getPath()), (int) map.get(groupName));
            }
        }

        xWeightCoefs();

        this.finalLayer.uploadCeffSetToFile("src/main/resources/CoeffSet.txt");
        this.finalLayer.downloadCeffSetFromFile("src/main/resources/CoeffSet.txt");

    }

    private void xWeightCoefs() {
        //multiply coefs by weights, that are 0 near the border
    }


}
