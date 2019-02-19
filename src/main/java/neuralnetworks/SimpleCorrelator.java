package neuralnetworks;

import layer.Layer;
import layer.conv.AlexConv1Layer;
import layer.conv.Simple3dConvLayer;
import layer.fully.FinalLayer;
import layer.fully.FullyConnectedLayerBuilder;
import layer.pool.MaxPoolLayer;
import matrix.Matrix;
import matrix.MatrixClass;

import java.util.ArrayList;
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
    Layer pool1 = new MaxPoolLayer(1, 1);
    //8x16x96
    FinalLayer finalLayer = new FinalLayer();
    //6x1

    public SimpleCorrelator() {
        coefficientsSet = new ArrayList<List<Matrix>>();
        for (int i = 0; i < 6; i++) {
            coefficientsSet.add(new ArrayList<Matrix>()
            );}
        }


    public List<List<Matrix>> getCoef(){
        return this.coefficientsSet;
    }

    public List<Double> apply(List<Matrix> input) {


        //conv1.apply
        //pool1.apply
        //finalLayer.apply
        return null;
    }

    private Matrix createNullMatrix(int rows, int columns)
    {
        List<List<Double>> _matrix = new ArrayList<>();
        for (int i = 0; i < rows; i++)
        {
            _matrix.add(new ArrayList<>());
            for (int j = 0; j < columns; j++)
            {
                _matrix.get(i).add(0.);
            }
        }
        return new MatrixClass(_matrix);
    }

    //Group - recognition class (100rub, 200rub ...)
    public void trainCoefSetForGroup(List<Matrix> inputRGBimage, int groupIdx) {
        trainingSetVolume++;
        System.out.println(trainingSetVolume);
        List<Matrix> filteredImage = conv1.apply(inputRGBimage);
        List<Matrix> result = pool1.apply(filteredImage);
        if (coefficientsSet.get(groupIdx).size() == 0)
        {
            for (int l = 0; l<96; l++)
            {
                coefficientsSet.get(groupIdx).add(this.createNullMatrix
                        (result.get(0).getSize(1), result.get(0).getSize(2)));
            }
        }
        for (int i = 0; i < 96; i++)
        {
            for (int r = 0; r < result.get(0).getSize(1); r++)
            {
                for (int c = 0; c < result.get(0).getSize(2); c++)
                {
                    coefficientsSet.get(groupIdx).get(i).set(r, c,
                            ((coefficientsSet.get(groupIdx).get(i).get(r, c)*trainingSetVolume-1)
                                    +result.get(i).get(r, c))/trainingSetVolume);

                }
            }
        }
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
