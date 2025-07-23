package imageProcessing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageReconstructor {
	
	public static BufferedImage getFullImage(BufferedImage[] quarters){
		BufferedImage finalImage;
		BufferedImage fullImage;
		
		int width = quarters[0].getWidth();
		int height = quarters[0].getHeight();
		
		fullImage = new BufferedImage(2 * width, 2 * height, BufferedImage.TYPE_INT_RGB);
		Graphics g = fullImage.getGraphics();
		g.drawImage(quarters[0], 0, 0, null);
		g.drawImage(quarters[1], width, 0, null);
		g.drawImage(quarters[2], 0, height, null);
		g.drawImage(quarters[3], width, height, null);
		
		finalImage = fullImage;

		return finalImage;
	}

}