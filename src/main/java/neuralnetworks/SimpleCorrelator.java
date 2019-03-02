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
import matrix.MatrixClass;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class SimpleCorrelator {

    public SimpleCorrelator() {

        coefficientsSet = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            coefficientsSet.add(new ArrayList<>()
            );}


        URL url = this.getClass().getClassLoader().getResource("CoeffSet.txt");

        File file = null;
        try {
            file = new File(url.toURI());

        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        }

        this.finalLayer.downloadCeffSetFromFile(file.getPath());

    }

    public List<List<Matrix>> getCoefficientsSet() {
        return coefficientsSet;
    }

    List<String> fileNames; // "rub50", "rub100", "rub200"

    List<List<Matrix>> coefficientsSet;

    List<Double> thresholds;

    String weightsStr = "0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00;'\n'" +
            "0.00,0.62,0.70,0.80,0.86,0.93,0.95,0.95,0.95,0.93,0.86,0.80,0.70,0.62,0.00;'\n'" +
            "0.00,0.63,0.72,0.80,0.88,0.96,1.00,1.00,1.00,0.96,0.88,0.80,0.72,0.63,0.00;'\n'" +
            "0.00,0.63,0.72,0.80,0.88,0.96,1.00,1.00,1.00,0.96,0.88,0.80,0.72,0.63,0.00;'\n'" +
            "0.00,0.63,0.72,0.80,0.88,0.96,1.00,1.00,1.00,0.96,0.88,0.80,0.72,0.63,0.00;'\n'" +
            "0.00,0.62,0.70,0.80,0.86,0.93,0.95,0.95,0.95,0.93,0.86,0.80,0.70,0.62,0.00;'\n'" +
            "0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00";
    Matrix weights = MatrixClass.fromString(weightsStr).t();



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
        return getDecision(finalLayer.apply(pool1.apply(conv1.apply(input))));


        //getDecision
    }

    //Group - recognition class (100rub, 200rub ...)
    public void trainCoefSetForGroup(List<Matrix> inputRGBimage, int groupIdx) {
        trainingSetVolume++;
//        System.out.println(trainingSetVolume);
        List<Matrix> filteredImage = conv1.apply(inputRGBimage);
        List<Matrix> result = pool1.apply(filteredImage);
        if (coefficientsSet.get(groupIdx).size() == 0) {
            for (int l = 0; l < 96; l++) {
                coefficientsSet.get(groupIdx).add(this.createNullMatrix
                        (result.get(0).getSize(1), result.get(0).getSize(2)));
            }
        }
        for (int i = 0; i < 96; i++) {
            for (int r = 0; r < result.get(0).getSize(1); r++) {
                for (int c = 0; c < result.get(0).getSize(2); c++) {
                    coefficientsSet.get(groupIdx).get(i).set(r, c,
                            ((coefficientsSet.get(groupIdx).get(i).get(r, c) * trainingSetVolume - 1)
                                    + result.get(i).get(r, c)) / trainingSetVolume);

                }
            }
        }
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


//    private List<Boolean> getDecision(List<Double> correlationCoefficients) {
//        List<Boolean> decisions = new ArrayList<>();
//        for (int i = 0; i < this.thresholds.size(); i++) {
//            decisions.add(correlationCoefficients.get(i) > this.thresholds.get(i)  );
//        }
//
//        return decisions;
//    }

    private List<Boolean> getDecision(List<Double> inputlist)
    {
        for (int i = 0; i < inputlist.size(); i++)
        {
            System.out.println(inputlist.get(i));
        }
        System.out.println();
        List<Boolean> decision = new ArrayList<>();
        double max = Collections.max(inputlist);
        if (max < 0.51)
        {
            for (int i = 0; i < inputlist.size(); i++)
            {
                decision.add(false);
            }
        }
        else {
            for (int i = 0; i < inputlist.size(); i++) {
                if (inputlist.get(i) < max) {
                    decision.add(false);
                } else {
                    decision.add(true);
                }

            }

        }
        return decision;
    }

    public void setThresholds(List<Double> thresholds){
        this.thresholds = thresholds;
    }


    public List<Double> getThresholds() {

        URL url = this.getClass().getClassLoader().getResource("TrainingSet");
        File folder = null;
        ImageProcessor imageProcessor = new ImageProcessorClass();
        try {
            folder = new File(url.toURI());
        } catch (URISyntaxException e) {
            folder = new File(url.getPath());
        }

        HashMap map = new HashMap();
        map.put("rub50", 0);
        map.put("rub100", 1);
        map.put("rub200", 2);
        map.put("rub500", 3);
        map.put("rub1000", 4);
        map.put("rub5000", 5);


        List<Double> min = new ArrayList<>();

        for (int i = 0; i<coefficientsSet.size();i++){
            min.add(100.0);
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

        List<Double> trainOutput = new ArrayList<>();

        for (File group : folder.listFiles()) {
            String groupName = group.getName();

            for (File image : group.listFiles()) {
                String[] path = image.getPath().split(separator);
                String relativePath = path[path.length-3]+_char+path[path.length-2]+_char+path[path.length-1];
                trainOutput = applyWithoutDecision(imageProcessor.loadImage(relativePath));
                if (trainOutput.get((int) map.get(groupName))<min.get((int) map.get(groupName))){
                    min.set((int) map.get(groupName),trainOutput.get((int) map.get(groupName)));
                }
            }
        }
        this.thresholds = min;
        //applywithoutdecision - list double
        //foreach Group
        //  foreach image in TrainingSet
        //      trainCoefSetForGroup
        //xWeightCoefs
        //finalLayer.setCoefs(coefficients)
        //finalLayer.uploadCoefs
        //     trainOutput =  apply().get(groupIdx)
        //     min ?= trainOutput
        //  addToList(min)
        //return List
        return thresholds;
    }

    public List<Double> applyWithoutDecision(List<Matrix> input){

        return finalLayer.apply(pool1.apply(conv1.apply(input)));

    }

    public void trainFinalLayer() {

        URL url = this.getClass().getClassLoader().getResource("TrainingSet");
        File folder = null;
        try {

            folder = new File(url.toURI());
        } catch (URISyntaxException e) {

            folder = new File(url.getPath());
        }

        HashMap map = new HashMap();
        map.put("rub50", 0);
        map.put("rub100", 1);
        map.put("rub200", 2);
        map.put("rub500", 3);
        map.put("rub1000", 4);
        map.put("rub5000", 5);


        String separator;
        String _char;
        if (File.separatorChar == '/') {
            separator = "/";
            _char = "/";
        } else {
            separator = "\\\\";
            _char = "\\";
        }

        ImageProcessor imageProcessor = new ImageProcessorClass();


        for (File group : folder.listFiles()) {
            String groupName = group.getName();
            this.trainingSetVolume = 0;
            for (File image : group.listFiles()) {

                String[] path = image.getPath().split(separator);
                String relativePath = path[path.length - 3] + _char + path[path.length - 2] + _char + path[path.length - 1];
                trainCoefSetForGroup(imageProcessor.loadImage(relativePath), (int) map.get(groupName));
            }
        }

        xWeightCoefs();


        url = this.getClass().getClassLoader().getResource("CoeffSelt.txt");


        File file = null;
        try {
            file = new File(url.toURI());

        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        }

        this.finalLayer.setCoefficientsSet(this.coefficientsSet);

        this.finalLayer.uploadCeffSetToFile(file.getPath());

 //       this.finalLayer.downloadCeffSetFromFile(file.getPath());



    }

    private Matrix createNullMatrix(int rows, int columns) {
        List<List<Double>> _matrix = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            _matrix.add(new ArrayList<>());
            for (int j = 0; j < columns; j++) {
                _matrix.get(i).add(0.);
            }
        }
        return new MatrixClass(_matrix);
    }

    private void xWeightCoefs() {
        //multiply coefs by weights, that are 0 near the border

        List<List<Matrix>> newCoefSet = new ArrayList<>();

        //for each group
        for (int i = 0; i < coefficientsSet.size(); i++) {
            List<Matrix> newCoefSetRow = new ArrayList<>();

            //for each filter
            for (int j = 0; j < coefficientsSet.get(i).size(); j++) {
                newCoefSetRow.add(coefficientsSet.get(i).get(j).dot(weights));
            }
            newCoefSet.add(newCoefSetRow);
        }
        coefficientsSet = newCoefSet;
    }

}