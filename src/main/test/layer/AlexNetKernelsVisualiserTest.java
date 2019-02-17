package layer;

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


        kernels = KernelsReader.readKernelsFromFile("src/main/resources/AlexK.txt");

//        for (int k = 0; k < 96; k++) {
//
//            for (int x = 0; x < 3; x++) {
//
//                AlexNetKernelsVisualiser shower = new AlexNetKernelsVisualiser();
//                Matrix m = kernels.get(k).get(x);
//                //m = shower.NormalizeMatrix(m);
//                kernels.get(k).set(x, m);
//            }
//        }



        AlexNetKernelsVisualiser sh = new AlexNetKernelsVisualiser();
        sh.ShowKernels(kernels);
        Thread.sleep(2000);

    }

}
