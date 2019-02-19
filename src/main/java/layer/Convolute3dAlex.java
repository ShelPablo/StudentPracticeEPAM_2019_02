package layer;

import matrix.Matrix;

import java.util.ArrayList;
import java.util.List;

public class Convolute3dAlex extends ConvLayerClass{


    public Convolute3dAlex() {

        super(null,null, 4);
    }

    @Override
    public void apply(){
        KernelsReader kernelsReader = new KernelsReader();
        super.kernel = kernelsReader.readKernelsFromFile("./src/main/resourses/AlexK.txt");

        List<Matrix> result = new ArrayList<>();
        for(List<Matrix> value: kernel){
            result.add(convolute3d(input,value).get(0));
        }
        super.output =result;
    }

}
