package neuralnetworks;

import layer.Layer;
import matrix.Matrix;

import java.util.List;

public interface LayeredNeuralNetwork {

    LayeredNeuralNetwork addLayer(Layer newLayer);

    List<Double> apply(List<Matrix> matrixFromRGBImage);

    //void train(String pathToTrainingSet);

    //void test(String pathToTestSet);


}
