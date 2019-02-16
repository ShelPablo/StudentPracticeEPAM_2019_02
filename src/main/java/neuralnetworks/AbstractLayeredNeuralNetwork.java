package neuralnetworks;

import layer.Layer;
import matrix.Matrix;

import java.util.Deque;
import java.util.List;

public abstract class AbstractLayeredNeuralNetwork {

    private Deque<Layer> layers;

    public AbstractLayeredNeuralNetwork addLayer(Layer newLayer) {
        newLayer.setInput(layers.getLast().getOutput());
        layers.add(newLayer);
        return this;
    };


    public void build() {
        layers.stream();

    }



    public List<Matrix>  apply(List<Matrix> input) {
        layers.getFirst().setInput(input);
        //layers.forEach(l->l.apply());
        return layers.getLast().getOutput();
    };






}
