package org.example;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class App {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat video = new Mat();
        VideoCapture videoCapture = new VideoCapture("Path to movie");

        JFrame jFrame = new JFrame("Car detection");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel jLabel = new JLabel();
        jFrame.setContentPane(jLabel);
        jFrame.setSize(1000, 700);
        jFrame.isResizable();
        jFrame.setVisible(true);

        while (true) {
            if (videoCapture.read(video)) {

                ImageIcon image = new ImageIcon(Mat2BufferedImage(video));
                jLabel.setIcon(image);
                jLabel.repaint();
            }
        }
    }

    public static BufferedImage Mat2BufferedImage(Mat m) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (m.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = m.channels() * m.cols() * m.rows();
        byte[] b = new byte[bufferSize];
        m.get(0, 0, b);
        BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        return image;
    }
}
