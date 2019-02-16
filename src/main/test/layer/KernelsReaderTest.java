package layer;

import matrix.Matrix;
import org.junit.Test;

import java.util.List;

public class KernelsReaderTest {
    @Test
    public void readKernelsFromFileTest(){

        List<List<Matrix>> kernels = KernelsReader.readKernelsFromFile("src/main/resources/AlexK.txt");

        for (List<Matrix> list: kernels){
            for (Matrix m: list){
                for (int i = 0; i < 11; i++){
                    for (int j = 0; j < 11; j++){
                        System.out.print(m.get(i, j) + " ");
                    }
                    System.out.println(" ");
                }
                System.out.println(" ");
                System.out.println(" ");
            }
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");
        }
    }



}
