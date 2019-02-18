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
        List<List<Matrix>> kernelss = KernelsReader.readKernelsFromFile("src/main/resources/AlexK.txt");
        AlexNetKernelsVisualiser kVisualiser = new AlexNetKernelsVisualiser();
        kVisualiser.ShowKernels(kernelss);
    }
}
