package layer;

import imageprocessor.ImageProcessor;
import imageprocessor.ImageProcessorClass;
import matrix.Matrix;
import matrix.MatrixClass;

import java.util.ArrayList;
import java.util.List;


public class AlexNetKernelsVisualiser {
    public void ShowKernels(List<List<Matrix>> kernelsList)
    {
        for (int x = 0; x < 96; x++)
        {
            for (int y = 0; y < 3; y++)
            {
                Matrix m = this.NormalizeMatrix(kernelsList.get(x).get(y));
                m = m.t();
                m = m.t();
                kernelsList.get(x).set(y, m);
            }
        }
        List<Double[][]> image = new ArrayList<>();
        image.add(new Double[121][121]);
        image.add(new Double[121][121]);
        image.add(new Double[121][121]);
        for (Double[][] arr : image)
        {
            for (int x = 0; x < 121; x++)
            {
                for (int y = 0; y < 121; y++)
                {
                    arr[x][y]=0.;
                }
            }
        }

        int xStartPosition = 1;
        int yStartPosition = 1;
        int kn = 0;
                for (int row = 0; row < 10; row++) {
                    for (int col = 0; col < 10; col++) {
                        for (int x = 0; x < 11; x++) {
                            for (int y = 0; y < 11; y++) {
                                if (kn < 96) {
                                    image.get(0)[x + xStartPosition][y + yStartPosition] = kernelsList.get(kn).get(0).get(x, y);
                                    image.get(1)[x + xStartPosition][y + yStartPosition] = kernelsList.get(kn).get(1).get(x, y);
                                    image.get(2)[x + xStartPosition][y + yStartPosition] = kernelsList.get(kn).get(2).get(x, y);
                                }
                                else
                                    break;
                            }
                        }
                        kn++;
                        xStartPosition += 12;
                    }
                    yStartPosition += 12;
                    xStartPosition = 1;
                }

        List<Matrix> img = new ArrayList<>();
        for (int x = 0; x < 3; x++) {
            List<List<Double>> _matrix = new ArrayList<>();

            for (int i = 0; i < 121; i++) {
                _matrix.add(new ArrayList<>());
                for (int j = 0; j < 121; j++) {
                    _matrix.get(i).add(image.get(x)[i][j]);
                }

            }
            img.add(new MatrixClass(_matrix));
        }
        ImageProcessor proc = new ImageProcessorClass(img);
        proc.showImage();
    }

    public double getMin(Matrix matrix)
    {
        double min = matrix.get(0,0);
        for (int i = 0; i < matrix.getSize(1); i++) {
            for (int j = 0; j < matrix.getSize(2); j++)
            {
                if (matrix.get(i, j) < min){min = matrix.get(i,j);}
            }
        }
        return min;
    }

    public Matrix NormalizeMatrix(Matrix matrix)
    {
        List<List<Double>> normalizedMatrix = new ArrayList<>();
        double min = getMin(matrix);
        double max = getMax(matrix);
        double move = 0;
        //if (min < 0)
       // {
       //     move = Math.abs(min);
        //    min += move;
        //    max += move;
       // }
        double scaleCoef = 0.;
        if (Math.abs(min) > Math.abs(max)) {
            double buf = 0;
            buf = min;
            max = min;
            min = buf;
            scaleCoef = 56.25 / Math.abs(min);
        }
        else {
           scaleCoef = 56.25 / (max);
        }
        //max += min;
        for (int i = 0; i < matrix.getSize(1); i++) {
            normalizedMatrix.add(new ArrayList<Double>());
            for (int j = 0; j < matrix.getSize(2); j++)
            {
                //normalizedMatrix.get(i).add(((matrix.get(i,j)+move+min)/(max+min))*255);
                normalizedMatrix.get(i).add(112.5+matrix.get(i,j)*317);
            }
        }
        return new MatrixClass(normalizedMatrix);
    }

    public double getMax(Matrix matrix)
    {
        double max = matrix.get(0,0);
        for (int i = 0; i < matrix.getSize(1); i++) {
            for (int j = 0; j < matrix.getSize(2); j++)
            {
                if (matrix.get(i,j) > max){max = matrix.get(i,j);}
            }
        }
        return max;
    }
}
