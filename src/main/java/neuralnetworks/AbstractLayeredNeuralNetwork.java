package neuralnetworks;

import layer.Layer;
import matrix.Matrix;

import java.util.Deque;
import java.util.List;
import java.util.function.Function;

public abstract class AbstractLayeredNeuralNetwork implements LayeredNeuralNetwork {

    Function<List<Matrix>, List<Matrix>> apply;

    private Deque<Layer> layers;

    private List<Double> output;


    public LayeredNeuralNetwork addLayer(Layer newLayer) {
        newLayer.setInput(layers.getLast().getOutput());
        layers.add(newLayer);
        return this;
    };


    public void build() {
        layers.stream();

    }



//    public List<Matrix>  apply(List<Matrix> input) {
//        layers.getFirst().setInput(input);
//        //layers.forEach(l->l.apply());
//        return layers.getLast().getOutput();
//    };






}
