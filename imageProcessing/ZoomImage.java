package imageProcessing;

import java.awt.image.BufferedImage;

public class ZoomImage {

    private final int zoomFactor = 2;

    public BufferedImage startZoomQuarter(BufferedImage quarter) {
    	//verificam daca avem sfertul
        if (quarter == null) {
            System.out.println(" ");
            return null;
        }

        int originalWidth = quarter.getWidth();
        int originalHeight = quarter.getHeight();

        int newWidth = originalWidth * zoomFactor;
        int newHeight = originalHeight * zoomFactor;
        BufferedImage zoomedQuarter = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

        System.out.println("Zooming process started...");

        for (int i = 0; i < newWidth; i++) {
            for (int j = 0; j < newHeight; j++) {
                int originalX = i / zoomFactor;
                int originalY = j / zoomFactor;

                // Ne asiguram ca x si y se afla in intervalul sfertului curent
                originalX = Math.min(originalX, originalWidth - 1);
                originalY = Math.min(originalY, originalHeight - 1);

                int pixel = quarter.getRGB(originalX, originalY);
                zoomedQuarter.setRGB(i, j, pixel);
            }
        }

        System.out.println("Zooming process completed.");

        return zoomedQuarter;
    }

    public BufferedImage startZoomImage(BufferedImage[] quarters) {
        if (quarters == null || quarters.length == 0 || quarters[0] == null) {
            System.out.println("Error: Invalid input quarters for zooming.");
            return null;
        }

        int originalWidth = quarters[0].getWidth();
        int originalHeight = quarters[0].getHeight();

        int newWidth = originalWidth * zoomFactor;
        int newHeight = originalHeight * zoomFactor;
        BufferedImage zoomedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

        System.out.println("Zooming entire image process started...");

        for (BufferedImage quarter : quarters) {
            BufferedImage zoomedQuarter = startZoomQuarter(quarter);
            //facem zoom la intreaga imagine
            if (zoomedQuarter != null) {
                for (int i = 0; i < newWidth; i++) {
                    for (int j = 0; j < newHeight; j++) {
                        zoomedImage.setRGB(i, j, zoomedQuarter.getRGB(i, j));
                    }
                }
            }
        }

        System.out.println("Zooming entire image process completed.");

        return zoomedImage;
    }
}
