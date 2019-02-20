package imageprocessor;

import layer.kernels.AlexNetKernelsVisualiser;
import layer.kernels.KernelsReader;
import matrix.Matrix;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AlexNetKernelsVisualiserTest {
    @Test
    public void ShowKernelsTest() throws InterruptedException {
        List<List<Matrix>> kernels = new ArrayList<>();


        kernels = KernelsReader.readKernelsFromFile("AlexK.txt");

        AlexNetKernelsVisualiser sh = new AlexNetKernelsVisualiser();
        sh.ShowKernels(kernels);
        Thread.sleep(2000);

    }
}
