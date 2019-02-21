package neuralnetworks;

import layer.Layer;
import layer.conv.AlexConv1Layer;
//import layer.conv.Simple3dConvLayer;
import layer.fully.FinalLayer;
import layer.fully.FullyConnectedLayerBuilder;
import layer.pool.MaxPoolLayer;
import matrix.Matrix;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SimpleCorrelator {

    List<String> fileNames; // "rub50", "rub100", "rub200"

    List<List<Matrix>> coefficientsSet;

    List<Double> thresholds;

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


    public List<Boolean> apply(List<Matrix> input) {
        //conv1.apply
        //pool1.apply
        //finalLayer.apply
        //getDecision
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
/*
    public List<Double> getSTD() {
        //foreach Group
        //    sum = 0
        //  foreach image in TrainingSet
        //      sum += calculateSquaredDeviationForInput
        //
        //sqrt(sum/N)
        return null;
    }
    public List<Double> getMaxDeviation() {
        //foreach Group
        //  max = 0
        //  foreach image in TrainingSet
        //      max ?= calculateSquaredDeviationForInput
        //   addToList(max)
        //return List<max>
        return null;
    }
*/

    private List<Boolean> getDecision(List<Double> correlationCoefficients) {
        List<Boolean> decisions = new ArrayList<>();
        for (int i = 0; i < this.thresholds.size(); i++) {
            decisions.add(correlationCoefficients.get(i) > this.thresholds.get(i)  );
        }

        return decisions;
    }


    public List<Double> getThresholds() {

        List<Boolean> trainOutput = new ArrayList<>();
        File folder = new File("/src/main/java/resources");
        File[] folderEntries = folder.listFiles();
        int x = 0;
        for (File entry : Objects.requireNonNull(folderEntries))
        {
            if (entry.isDirectory())
            {
                x++;
                File[] collections = entry.listFiles();
                int y = 0;
                for (File picture : Objects.requireNonNull(collections))
                {
                    if (entry.isDirectory()){
                        y++;

                    }
                }
            }
        }
        //applywithoutdecision - list double
        //foreach Group
        //  foreach image in TrainingSet
        //     trainOutput =  apply().get(groupIdx)
        //     min ?= trainOutput
        //  addToList(min)
        //return List
        return null;
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