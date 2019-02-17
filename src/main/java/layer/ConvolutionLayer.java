package layer;

import matrix.Matrix;
import matrix.MatrixClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ConvolutionLayer {

    private List<List<Matrix>> kernels;
    private List<Matrix> input;
    private List<Matrix> output;
    private int stride;

    public void setStride(int stride) {
        this.stride = stride;
    }

    public List<List<Matrix>> getKernels() {
        return kernels;
    }

    public List<Matrix> getInput() {
        return input;
    }

    public List<Matrix> getOutput() {
        return output;
    }

    public void setKernels(List<List<Matrix>> kernels) {
        this.kernels = kernels;
    }

    public void setInput(List<Matrix> input) {
        this.input = input;
    }

    public void setOutput(List<Matrix> output) {
        this.output = output;
    }

    private double getFilteredValue(Matrix input, int indexOfRow, int indexOfCol, Matrix kernel) {

        double filteredValue = 0;

        List<List<Double>> _partOfInputImage = new ArrayList<>();

        for (int i = 0; i < kernel.getSize(1); i++) {
            _partOfInputImage.add(new ArrayList<>(Collections.nCopies(kernel.getSize(2), 0.)));
        }

        for (int i = 0; i < kernel.getSize(1); ++i) {
            for (int j = 0; j < kernel.getSize(2); ++j) {
                _partOfInputImage.get(i).set(j, input.get(indexOfRow + i, indexOfCol + j));
            }
        }

        Matrix partOfInputImage = new MatrixClass(_partOfInputImage);

        Matrix result = partOfInputImage.dot(kernel);

        for (int i = 0; i < result.getSize(1); ++i) {
            for (int j = 0; j < result.getSize(2); ++j) {
                filteredValue += result.get(i, j);
            }
        }

        return filteredValue;
    }

    // Convolution of kernel with input Matrix
    private Matrix convolution(Matrix input, Matrix kernel) {

        List<List<Double>> result = new ArrayList<>();

        int rows = (input.getSize(1) - kernel.getSize(1)) / stride + 1;
        int columns = (input.getSize(2) - kernel.getSize(2)) / stride + 1;

        for (int i = 0; i < rows; i++) {
            result.add(new ArrayList<>(Collections.nCopies(columns, 0.)));
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int indexOfRow = i * stride;
                int indexOfCol = j * stride;
                double newValue = getFilteredValue(input, indexOfRow, indexOfCol, kernel);
                result.get(i).set(j, newValue);
            }
        }

        return new MatrixClass(result);
    }

    // Convolution of each kernel with each input Matrix in List
    public List<Matrix> convolute(List<Matrix> input, List<Matrix> kernel) {

        List<Matrix> result = new ArrayList<>();

        for (Matrix _kernel : kernel) {
            for (Matrix _input : input) {
                Matrix _result = convolution(_input, _kernel);
                result.add(_result);
            }
        }
        return result;
    }

    // 3d convolution of kernel with input
    protected Matrix convolute3d(List<Matrix> input) {
        if (kernels.get(0).size() == input.size()) {
            Matrix result = new MatrixClass(43333333, 33333335);
            for (List<Matrix> kernel : kernels) {

                int rows = (input.get(0).getSize(1) - kernel.get(0).getSize(1)) / stride + 1;
                int columns = (input.get(0).getSize(2) - kernel.get(0).getSize(2)) / stride + 1;

                Matrix redConvolution = convolution(input.get(0), kernel.get(0));
                Matrix greenConvolution = convolution(input.get(1), kernel.get(1));
                Matrix blueConvolution = convolution(input.get(2), kernel.get(2));

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        double sumOfValues = redConvolution.get(i, j) + greenConvolution.get(i, j)
                                + blueConvolution.get(i, j);
                        //result.get(i).set(j, ReLU(sumOfValues));
                    }
                }

            }
            return result;
        }
        else throw new IllegalArgumentException("The 3-rd dimensions of kernel and input must be equal");
    }

    public abstract void apply();

    // Activation function
    private double ReLU(double value) {
        if (value < 0) return 0;
        else return value;
    }
}
