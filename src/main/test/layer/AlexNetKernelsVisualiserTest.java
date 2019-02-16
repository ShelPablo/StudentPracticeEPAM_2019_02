package layer;

import matrix.Matrix;
import matrix.MatrixClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
