package matrix;

import java.io.IOException;

public interface MatrixLoader {

   Matrix LoaderFromFile(String filename) throws IOException;
}
