package layer.conv;

import layer.Layer;
import matrix.Matrix;
import matrix.MatrixClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ConvLayer implements Layer {

    private List<List<Matrix>> kernels;
    private List<Matrix> input = new ArrayList<>();
    private List<Matrix> output;
    private int stride;

    public void setStride(int stride) {
        this.stride = stride;
    }

    public List<List<Matrix>> getKernels() {
        return kernels;
    }

    @Override
    public Layer setInput(List<Matrix> input) {
        this.input.clear();
        this.input.addAll(input);
        return this;
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
    protected Matrix convolute3d(List<Matrix> input, List<Matrix> kernel) {
        if (kernels.get(0).size() == input.size()) {
            int resultSize1 = (input.get(0).getSize(1) - kernels.get(0).get(0).getSize(1) ) / stride + 1;
            int resultSize2 = (input.get(0).getSize(2) - kernels.get(0).get(0).getSize(2) ) / stride + 1;

            Matrix result = new MatrixClass(resultSize1, resultSize2);

            for (int iRow = 0; iRow < resultSize1; iRow++) {
                for (int iCol = 0; iCol < resultSize2; iCol++) {
                    double conv = 0;
                    for (int i = 0; i < kernel.size(); i++) {
                        conv += kernel.get(i).convolute(
                                input.get(i).subMatrix(iRow * stride, iCol * stride,
                                        kernel.get(i).getSize(1), kernel.get(i).getSize(2)));
                    }
                    result.set(iRow, iCol, ReLU(conv));
                }
            }

            return result;
        } else throw new IllegalArgumentException("The 3-rd dimensions of kernel and input must be equal");
    }

    // Activation function
    private double ReLU(double value) {
        if (value < 0) return 0;
        else return value;
    }
}
