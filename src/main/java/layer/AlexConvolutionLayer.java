package layer;

import imageprocessor.ImageProcessorClass;
import layer.kernels.KernelsReader;
import matrix.Matrix;

import java.util.*;

public class AlexConvolutionLayer extends ConvolutionLayer {

    public AlexConvolutionLayer(List<Matrix> input){
        setInput(input);
        setKernels(KernelsReader.readKernelsFromFile("src/main/resources/AlexK.txt"));
        setStride(4);
    }

    public void apply(){

        List<Matrix> output = new ArrayList<>();

        for (List<Matrix> kernel: getKernels()){
            Matrix result = convolute3d(getInput());
            output.add(result);
        }

        setOutput(output);
    }

    /*public static void main(String[] args){
        ImageProcessorClass imageProcessorClass = new ImageProcessorClass();
        List<Matrix> input = imageProcessorClass.loadImage("src/main/resources/2.jpg");

        AlexConvolutionLayer acl = new AlexConvolutionLayer(input);
        acl.apply();

        List<Matrix> output = acl.getOutput();
        System.out.println(output.size());
        System.out.println(output.get(0).getSize(1));
    }*/
}
