package layer.conv;

import imageprocessor.ImageProcessorClass;
import layer.ConvolutionLayer;
import layer.kernels.KernelsReader;
import matrix.Matrix;

import java.util.*;

public class AlexConv1Layer extends ConvolutionLayer {

    public AlexConv1Layer(){
        setKernels(KernelsReader.readKernelsFromFile("src/main/resources/AlexK.txt"));
        setStride(4);
    }
    @Override
    public List<Matrix> apply(List<Matrix> input){

        super.setInput(input);

        List<Matrix> output = new ArrayList<>();

        for (List<Matrix> kernel: getKernels()){

            Matrix result = super.convolute3d(this.getInput(), kernel);
            output.add(result);
        }

        setOutput(output);
        return output;
    }

    /*public static void main(String[] args){
        ImageProcessorClass imageProcessorClass = new ImageProcessorClass();
        List<Matrix> input = imageProcessorClass.loadImage("src/main/resources/2.jpg");

        AlexConv1Layer acl = new AlexConv1Layer(input);
        acl.apply();

        List<Matrix> output = acl.getOutput();
        System.out.println(output.size());
        System.out.println(output.get(0).getSize(1));
    }*/
}
