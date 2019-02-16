import layer.AlexNetKernelsVisualiser;
import layer.KernelsReader;
import matrix.Matrix;
import matrix.MatrixClass;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Program {
    public static void main(String[] args)
    {
        List<List<Matrix>> kernels = new ArrayList<>();
        for (int k = 0; k < 96; k++) {
            List<Matrix> img = new ArrayList<>();

            for (int x = 0; x < 3; x++) {
                List<List<Double>> _matrix = new ArrayList<>();

                for (int i = 0; i < 11; i++) {
                    _matrix.add(new ArrayList<>());
                    for (int j = 0; j < 11; j++) {
                        _matrix.get(i).add((double) ThreadLocalRandom.current().nextInt(0, 100 + 1));
                    }

                }
                AlexNetKernelsVisualiser shower = new AlexNetKernelsVisualiser();
                Matrix m = new MatrixClass(_matrix);
                //Matrix normalized = shower.NormalizeMatrix(m);
                img.add(m);

            }
            kernels.add(img);
        }
        List<List<Matrix>> kernelss = KernelsReader.readKernelsFromFile("src/main/resources/AlexK.txt");
        AlexNetKernelsVisualiser sh = new AlexNetKernelsVisualiser();
        sh.ShowKernels(kernelss);
    }
}
