package neuralnetworks;

import layer.Layer;

public interface LayeredNeuralNetwork {

    LayeredNeuralNetwork addLayer(Layer newLayer);

    void train(String pathToTrainingSet);

    void test(String pathToTestSet);


}
