package layer.conv;

import layer.kernels.KernelsReader;
import matrix.Matrix;

import java.util.*;

public class AlexConv1Layer extends ConvLayer {

    public AlexConv1Layer(){
        setKernels(KernelsReader.readKernelsFromFile("AlexK.txt"));
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
}
