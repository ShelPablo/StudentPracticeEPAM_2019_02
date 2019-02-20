package neuralnetworks;

import layer.Layer;
import layer.conv.AlexConv1Layer;
import layer.conv.Simple3dConvLayer;
import layer.fully.FinalLayer;
import layer.fully.FullyConnectedLayerBuilder;
import layer.pool.MaxPoolLayer;
import matrix.Matrix;

import java.util.List;

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
        return finalLayer.apply(pool1.apply(conv1.apply(input)));
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
        //foreach Group
        //  foreach image in TrainingSet
        //      trainCoefSetForGroup
        //xWeightCoefs
        //finalLayer.setCoefs(coefficients)
        //finalLayer.uploadCoefs
    }

    private void xWeightCoefs() {
        //multiply coefs by weights, that are 0 near the border
    }





}
