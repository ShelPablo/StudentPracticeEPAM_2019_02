package layer;

import matrix.Matrix;
import matrix.MatrixClass;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class AlexConvLayer extends ConvLayer {

    public AlexConvLayer(List<Matrix> input, int stride){
        setKernel(KernelsReader.readKernelsFromFile("/Users/mrgrigorev/IdeaProjects/Practice/src/main/java/resources/AlexK.txt"));
        setInput(input);
        setStride(stride);
    }

    @Override
    public void apply() {
        List<List<Matrix>> out = convol3d();
        setOutput(out);
    }












}
