package layer;

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
            Matrix result = convolute3d(getInput(), kernel);
            output.add(result);
        }

        setOutput(output);
    }
}
