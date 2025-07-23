package imageProcessing;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PipedOutputStream;
import javax.imageio.ImageIO;

public class Consumer extends Thread {

    private QuartersBuffer buffer;
    private BufferedImage[] imageQuarters;
    private ZoomImage zoomImage;
    private PipedOutputStream out;

    public Consumer(QuartersBuffer buffer, PipedOutputStream outPipe) {
        imageQuarters = new BufferedImage[4];
        this.buffer = buffer;
        this.out = outPipe;
        zoomImage = new ZoomImage();
    }

    @Override
    public void run() {
        long startTimeR = System.nanoTime();
        long startTime;
        long endTime;

        try {
            for (int i = 0; i < 4; i++) {
                startTime = System.nanoTime();
                imageQuarters[i] = buffer.get(i);
                endTime = System.nanoTime();
                ReceivingMessage mess = new ReceivingMessage();
                mess.writeMessage(i, endTime - startTime);

                // Preia și zoomeaza sfertul
                imageQuarters[i] = processAndZoomQuarter(imageQuarters[i]);

                // Asteapta un moment, doar pentru a simula prelucrarea
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTimeR = System.nanoTime();
        System.out.println("\n" + "Done receiving the image from ProducerClass in " + (endTimeR - startTimeR)
                + " nanoseconds.");

        startTime = System.nanoTime();
        BufferedImage fullImage = ImageReconstructor.getFullImage(imageQuarters);
        endTime = System.nanoTime();
        System.out.println("Done reconstructing the image from quarters in " + (endTime - startTime) + " nanoseconds.");

        // Zoom pe intreaga imagine
        BufferedImage zoomedImage = zoomImage.startZoomImage(imageQuarters);

        // Scrie imaginea finala zoomata
        writeZoomedQuarter(zoomedImage);
    }

    private BufferedImage processAndZoomQuarter(BufferedImage quarter) {
        // Proceseaza sfertul si face zoom
        return zoomImage.startZoomQuarter(quarter);
    }

    private void writeZoomedQuarter(BufferedImage zoomedQuarter) {
        try {
            ImageIO.write(zoomedQuarter, "bmp", out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException thrown when trying to write...");
        }
    }
}
