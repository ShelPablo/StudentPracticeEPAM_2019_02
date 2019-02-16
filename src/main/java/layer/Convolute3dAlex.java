package layer;

public class Convolute3dAlex extends ConvLayerClass{


    public Convolute3dAlex() {

        super(null,null, 4);
    }

    @Override
    public void apply(){
        KernelsReader kernelsReader = new KernelsReader();
        super._kernel = kernelsReader.readKernelsFromFile("./src/main/resourses/AlexK.txt");
        super._output = convolute(_input);
    }

}
