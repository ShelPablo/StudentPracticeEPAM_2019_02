package layer;

import matrix.Matrix;
import matrix.MatrixClass;
import java.util.ArrayList;
import java.util.List;

public abstract class ConvLayer implements Layer {

    private List<List<Matrix>> kernels;
    private List<Matrix> input;
    private List<List<Matrix>> output;
    private int stride;


    public void setStride(int stride){
        this.stride = stride;
    }

    public void setKernel(List<List<Matrix>> kernels){
        this.kernels = kernels;
    }

    public List<List<Matrix>> getOutput(){
        return output;
    }
    public void setInput(List<Matrix> input){
        this.input = input;
    }
    public void setOutput(List<List<Matrix>> output){
        this.output = output;
    }


    public List<List<Matrix>> convol3d() {

        for (int s = 0; s<kernels.size();s++){

            List<Matrix> rowOutput = new ArrayList<>();
            List<List<Double>> convMatrix = new ArrayList<>();

            int i = 0;
            while (i<input.get(0).getSize(1)-kernels.get(s).get(0).getSize(1)+1){

                List<Double> rowConvMatrix = new ArrayList<>();


                int j = 0;
                while (j<input.get(0).getSize(2)-kernels.get(s).get(0).getSize(2)+1){

                    //мини-матрицы из матриц цветов
                    List<List<Double>> asKernel0 = new ArrayList<>();
                    List<List<Double>> asKernel1 = new ArrayList<>();
                    List<List<Double>> asKernel2 = new ArrayList<>();

                    for (int k = i; k < i + kernels.get(s).get(0).getSize(1); k++) {

                        //ряды для мини-матриц
                        List<Double> rowAsKernel0 = new ArrayList<>();
                        List<Double> rowAsKernel1 = new ArrayList<>();
                        List<Double> rowAsKernel2 = new ArrayList<>();

                        for (int l = j; l < j + kernels.get(s).get(0).getSize(2); l++) {

                            double el0 = input.get(0).get(k, l);
                            double el1 = input.get(1).get(k, l);
                            double el2 = input.get(2).get(k, l);

                            rowAsKernel0.add(el0);
                            rowAsKernel1.add(el1);
                            rowAsKernel2.add(el2);

                        }
                        asKernel0.add(rowAsKernel0);
                        asKernel1.add(rowAsKernel1);
                        asKernel2.add(rowAsKernel2);
                    }
                    Matrix m0 = new MatrixClass(asKernel0);
                    Matrix m1 = new MatrixClass(asKernel1);
                    Matrix m2 = new MatrixClass(asKernel2);

                    m0.dot(kernels.get(s).get(0));
                    m1.dot(kernels.get(s).get(1));
                    m2.dot(kernels.get(s).get(2));

                    Double el = null;
                    for (int x = 0; x < m0.getSize(1); x++) {
                        for (int y = 0; y < m0.getSize(2); y++) {
                            el = m0.get(x, y) + m1.get(x, y) + m2.get(x, y);
                        }
                    }
                    rowConvMatrix.add(el);
                    j+=stride;
                }
                convMatrix.add(rowConvMatrix);
                i+=stride;
            }
            rowOutput.add(new MatrixClass(convMatrix));
            rowOutput.add(new MatrixClass(convMatrix));
            rowOutput.add(new MatrixClass(convMatrix));
        }
        return output;
    }
}
