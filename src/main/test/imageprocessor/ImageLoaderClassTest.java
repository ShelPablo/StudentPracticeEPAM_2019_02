package imageprocessor;

import org.junit.Assert;
import org.junit.Test;
import matrix.*;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class ImageLoaderClassTest {

    @Test
    public void loadImageAsMatrix() {
        ImageLoader img_loader = new ImageLoaderClass();
        List<Matrix> img_matrix = img_loader.loadImageAsMatrix("resources/img.bmp");

        List<Double> expectedValue = img_matrix.get(0).getRow(0);
        List<Double> actualValue = new ArrayList<>(Arrays.asList(237., 63., 237., 63., 237.));

        Assert.assertEquals(expectedValue, actualValue);
    }
}