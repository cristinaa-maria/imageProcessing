package imageProcessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PipedInputStream;
import javax.imageio.ImageIO;

public class WriterResult extends Thread {

    private BufferedImage[] imageQuarters;
    private PipedInputStream in;
    private String outputFileName;

    // Constructor cu parametrii
    public WriterResult(PipedInputStream in, String outFile) {
        this.imageQuarters = new BufferedImage[4];
        this.in = in;
        this.outputFileName = outFile;
    }

    @Override
    public void run() {
        long startTime; // variabile de masurare a duratei fiecarei etape
        long endTime;

        startTime = System.nanoTime();
        readData();
        endTime = System.nanoTime();
        System.out.println("Done reading data from the pipe in " + (endTime - startTime) + " nanoseconds.");

        // Apelul metodei startZoomImage pentru a face zoom pe sferturi și a le combina într-o singură imagine
        BufferedImage zoomedImage = startZoomImage(imageQuarters);

        // Scriem rezultatul final în fișierul de output
        writeResultToFile(zoomedImage);
    }

    // Metoda pentru a citi datele de pe pipe
    private void readData() {
        try {
            // Citim sferturile de pe pipe
            for (int i = 0; i < 4; i++) {
                imageQuarters[i] = ImageIO.read(in);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception thrown when reading from the pipe...");
        }
    }

    // Metoda pentru a scrie sferturile cu zoom in fisierul de output
    private void writeResultToFile(BufferedImage zoomedImage) {
        long startTime = System.nanoTime();

        // Scriem imaginea cu zoom in fisierul de output
        try {
            ImageIO.write(zoomedImage, "bmp", new File(outputFileName));
        } catch (IOException e) {
            System.out.println("Exception thrown when writing to the output file...");
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        System.out.println("\n" + "Done writing to the output file in " + (endTime - startTime) + " nanoseconds.");
    }

    // Metoda pentru a face zoom pe sferturi si a le combina intr-o singura imagine
    private BufferedImage startZoomImage(BufferedImage[] quarters) {
        ZoomImage zoomImage = new ZoomImage();
        return zoomImage.startZoomImage(quarters);
    }
}
