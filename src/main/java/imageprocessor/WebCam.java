package imageprocessor;

import org.bytedeco.javacv.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static org.bytedeco.javacpp.opencv_core.IplImage;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;

public class WebCam implements Runnable {
    final int INTERVAL = 100;
    CanvasFrame canvas = new CanvasFrame("Web Cam");
    IplImage img;

    public WebCam() {
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        canvas.setFocusable(true);

        canvas.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent evt) {}
            public void keyReleased(KeyEvent evt) {}

            public void keyTyped(KeyEvent evt) {
                cvSaveImage("webCam.jpg", img);
            }
        });
    }

    public void run() {

        FrameGrabber grabber = new VideoInputFrameGrabber(0);
        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();

        try {
            grabber.start();
            while (true) {
                Frame frame = grabber.grab();
                img = converter.convert(frame);
                canvas.showImage(converter.convert(img));
                Thread.sleep(INTERVAL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WebCam cam = new WebCam();
        Thread th = new Thread(cam);
        th.start();
    }
}
