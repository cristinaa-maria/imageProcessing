package imageProcessing;

import java.awt.image.BufferedImage;

public interface ImageReaderInterface {
	
	public  BufferedImage readQuarter(Integer q);
	public  BufferedImage readFullImage(String fileName);
	public BufferedImage getQuarter(BufferedImage fullImage, Integer q);

}