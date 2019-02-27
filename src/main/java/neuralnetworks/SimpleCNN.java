package neuralnetworks;

import imageprocessor.ImageProcessor;
import imageprocessor.ImageProcessorClass;
import layer.Layer;
import layer.conv.AlexConv1Layer;
import layer.fully.FinalLayer;
import layer.fully.LastFullyLayer;
import layer.fully.PreFullyLayer;
import layer.pool.MaxPoolLayer;
import matrix.Matrix;
import matrix.MatrixClass;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SimpleCNN {

    List<String> fileNames; // "rub50", "rub100", "rub200" ...


   //64x128
    Layer conv1 = new AlexConv1Layer();
    //16x32x96
    Layer pool1 = new MaxPoolLayer(2, 2);
    //8x16x96
    PreFullyLayer preFullyLayer = new PreFullyLayer();
    //6x1
    LastFullyLayer lastFullyLayer = new LastFullyLayer();

    private List<Double> thresholds;

    public List<Boolean> apply(List<Matrix> input) {
        //conv1.apply
        //pool1.apply
        //finalLayer.apply
        return getDecision(lastFullyLayer.apply(preFullyLayer.apply(pool1.apply(conv1.apply(input)))));
    }

    //Group - recognition class (100rub, 200rub ...)
    public void train(List<Matrix> inputRGBimage, List<Double> trueOutput) {
        //foreach layer apply (without decision)
        //foreach fully layers train, starting with LastFully.train(trueOutput)

    }

    void trainWholeCNN() {
        //foreach group
        // foreach image
        //  train(image, trueOutput(group))
    }

    private List<Boolean> getDecisionByMax (List<Double> inputList)
    {
        List<Boolean> decision = new ArrayList<>();
        double max = Collections.max(inputList);
        for (int i = 0; i < inputList.size(); i++) {
            if (inputList.get(i) < max) { decision.add(false);  }
            else { decision.add(true); }
        }
        return  decision;
    }

    private List<Boolean> getDecision(List<Double> correlationCoefficients) {
        List<Boolean> decisions = new ArrayList<>();
        for (int i = 0; i < this.thresholds.size(); i++) {
            decisions.add(correlationCoefficients.get(i) > this.thresholds.get(i)  );
        }
        return decisions;
    }


    public List<Double> applyWithoutDecision(List<Matrix> input){


        return null;
    }
}
