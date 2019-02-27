package layer.conv;

import layer.kernels.KernelsReader;
import matrix.Matrix;
import org.omg.CORBA.INTERNAL;

import java.util.*;

public class AlexConv1Layer extends ConvLayer {

    public AlexConv1Layer(){
        List<List<Matrix>> kernels = KernelsReader.readKernelsFromFile("AlexK.txt");
        List<Integer> index = new ArrayList<>(Arrays.asList(78,79,55,44,43,39,38,31,9,3));
        List<List<Matrix>> _kernels = new ArrayList<>();
        for (int i = 0; i<index.size();i++){
            _kernels.add(kernels.get(index.get(i)));
        }
        setKernels(kernels);
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
