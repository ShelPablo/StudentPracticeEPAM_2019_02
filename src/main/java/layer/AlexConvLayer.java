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

    public static void main(String[] args){
        List<Double> list = Arrays.asList(2.0,2.0,2.0,2.0,2.0,2.0,2.0,2.0,2.0,2.0);
        List<List<Double>> listList = Arrays.asList(list,list,list,list,list,list,list,list,list,list);

        Matrix m0 = new MatrixClass(listList);
        Matrix m1 = new MatrixClass(listList);
        Matrix m2 = new MatrixClass(listList);
        List<Matrix> input = Arrays.asList(m0,m1,m2);

        List<Double> listk = Arrays.asList(2.0,2.0,2.0,2.0,2.0);
        List<List<Double>> listListk = Arrays.asList(listk,listk,listk,listk,listk);

        Matrix k0 = new MatrixClass(listListk);
        Matrix k1 = new MatrixClass(listListk);
        Matrix k2 = new MatrixClass(listListk);
        List<Matrix> kernel = Arrays.asList(k0,k1,k2);

        List<List<Matrix>> kernels = Arrays.asList(kernel,kernel,kernel,kernel,kernel);
        AlexConvLayer convLayer = new AlexConvLayer(input,2);
        convLayer.setKernel(kernels);
        List<List<Matrix>> actual_matrix = convLayer.convol3d();
        actual_matrix.size();
    }













}
