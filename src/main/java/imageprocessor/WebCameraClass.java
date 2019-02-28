package imageprocessor;


import com.github.sarxos.webcam.Webcam;



import com.github.sarxos.webcam.WebcamPanel;



import com.github.sarxos.webcam.WebcamResolution;



import layer.fully.FinalLayer;
import matrix.Matrix;
import neuralnetworks.SimpleCorrelator;


import javax.imageio.ImageIO;



import javax.swing.*;



import java.awt.*;



import java.awt.event.ActionEvent;



import java.awt.image.BufferedImage;



import java.io.File;



import java.io.IOException;


import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;


import java.util.Arrays;
import java.util.List;



import java.util.concurrent.Executor;



import java.util.concurrent.Executors;











@SuppressWarnings("serial")



public class WebCameraClass extends JFrame {



    static FinalLayer f;







    private class SnapMeAction extends AbstractAction {







        public SnapMeAction() {



            super("Snapshot");



        }







        @Override



        public void actionPerformed(ActionEvent e) {



            ImageCropper crop = new ImageCropper();



            try {



                for (int i = 0; i < webcams.size(); i++) {







                    Webcam webcam = webcams.get(i);







                    BufferedImage in = webcam.getImage();



                    BufferedImage cropped = crop.cropImageToGivenSize(webcam.getImage(), 128, 64);



                    Image show = cropped;



                    File file = new File(String.format("banknote.jpg", i));



                    ImageIO.write(cropped, "jpg", file);



                    System.out.format("Image for %s saved in %s \n", webcam.getName(), file);

                    applySimpleCorrelator();

                }



            } catch (IOException e1) {



                e1.printStackTrace();



            }



        }



    }


    public void applySimpleCorrelator(){
        SimpleCorrelator simpleCorrelator = new SimpleCorrelator();

        //simpleCorrelator.trainFinalLayer();

        //List<Double> thresholds = Arrays.asList(0.6, 0.5, 0.6, 0.6, 0.6, 0.6);

        //simpleCorrelator.setThresholds(thresholds);

        /*System.out.println("");

        for (Double d: thresholds){
            System.out.println(d);
        }*/

        System.out.println("");

        ImageProcessor imageProcessor = new ImageProcessorClass();

        URL url = simpleCorrelator.getClass().getClassLoader().getResource("TestSet/rub50/72.jpg");
        File image = null;
//        try {
//            image = new File(url.toURI());
//        } catch (URISyntaxException e) {
//            image = new File(url.getPath());
//        }
        image = new File("banknote.jpg");

        String separator ;
        String _char;
        if(File.separatorChar=='/'){
            separator = "/";
            _char = "/";
        }else{
            separator ="\\\\";
            _char = "\\";
        }

        //String[] path = image.getPath().split(separator);
        //String relativePath = path[path.length-3]+_char+path[path.length-2]+_char+path[path.length-1];

        List<Matrix> input = imageProcessor.loadImage(image.getPath());

        List<Boolean> result = simpleCorrelator.apply(input);

        DialogDecisionClass dialogDecisionClass = new DialogDecisionClass(result);

        System.out.println("");
        for (Boolean r: result){
            System.out.println(r);
        }
        System.out.println("");
        System.out.println("");
    }






    private class StartAction extends AbstractAction implements Runnable {







        public StartAction() {



            super("Start");



        }







        @Override



        public void actionPerformed(ActionEvent e) {







            btStart.setEnabled(false);



            btSnapMe.setEnabled(true);







            // remember to start panel asynchronously - otherwise GUI will be



            // blocked while OS is opening webcam HW (will have to wait for



            // webcam to be ready) and this causes GUI to hang, stop responding



            // and repainting







            executor.execute(this);



        }







        @Override



        public void run() {







            btStop.setEnabled(true);







            for (WebcamPanel panel : panels) {



                panel.start();



            }



        }



    }







    private class StopAction extends AbstractAction {







        public StopAction() {



            super("Stop");



        }







        @Override



        public void actionPerformed(ActionEvent e) {







            btStart.setEnabled(true);



            btSnapMe.setEnabled(false);



            btStop.setEnabled(false);







            for (WebcamPanel panel : panels) {



                panel.stop();



            }



        }



    }











    private Executor executor = Executors.newSingleThreadExecutor();







    //private Dimension size = WebcamResolution.QQVGA.getSize();



    private Dimension size = WebcamResolution.QQVGA.getSize();



    private Dimension viewSize = new Dimension();



    private List<Webcam> webcams = Webcam.getWebcams();



    private List<WebcamPanel> panels = new ArrayList<WebcamPanel>();







    private JButton btSnapMe = new JButton(new SnapMeAction());



    private JButton btStart = new JButton(new StartAction());



    private JButton btStop = new JButton(new StopAction());







    public WebCameraClass() {







        super("Test Snap Different Size");



        for (Webcam webcam : webcams) {



            viewSize.setSize(size.getWidth()*2, size.getHeight()*2);



            webcam.setViewSize(size);



            WebcamPanel panel = new WebcamPanel(webcam, viewSize, false);



            panel.setFPSDisplayed(true);



            panel.setFillArea(true);



            panels.add(panel);



        }







        // start application with disable snapshot button - we enable it when



        // webcam is started







        btSnapMe.setEnabled(false);



        btStop.setEnabled(false);







        setLayout(new FlowLayout());







        for (WebcamPanel panel : panels) {



            add(panel);



        }







        add(btSnapMe);



        add(btStart);



        add(btStop);







        pack();



        setVisible(true);



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }







    public static void main(String[] args) {



        new WebCameraClass();



    }



}
