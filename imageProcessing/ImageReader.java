package imageProcessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageReader implements ImageReaderInterface {

	private String fileName;

	// incapsulare folosind getter si setteri pentru campurile private
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	// constructor cu parametru
	public ImageReader(String fname) {
		fileName = fname;
	}

	// constructor fara parametrii
	public ImageReader() {
		fileName = null;
	}

	// metoda care foloseste citirea intregii imaginii dar care returneaza doar
	// sfertul corespunzator parametrului q
	@Override
	public BufferedImage readQuarter(Integer q) {

		BufferedImage image;
		BufferedImage fullImage;

		fullImage = readFullImage(fileName);
		int width = fullImage.getWidth();
		int height = fullImage.getHeight();

		int x, y, w, h;//coordonatele pentru extragerea sferturilor in functie de ce sfert este extras

		switch (q) {
		case 0:
			x = 0;
			y = 0;
			w = Math.min(width, width / 2);
			h = Math.min(height, height / 2);
			image = fullImage.getSubimage(x, y, w, h);
			break;
		case 1:
			x = Math.min(width, width / 2);
			y = 0;
			w = Math.min(width, width / 2);
			h = Math.min(height, height / 2);
			image = fullImage.getSubimage(x, y, w, h);
			break;
		case 2:
			x = 0;
			y = Math.min(height, height / 2);
			w = Math.min(width, width / 2);
			h = Math.min(height, height / 2);
			image = fullImage.getSubimage(x, y, w, h);
			break;
		case 3:
			x = Math.min(width, width / 2);
			y = Math.min(height, height / 2);
			w = Math.min(width, width / 2);
			h = Math.min(height, height / 2);
			image = fullImage.getSubimage(x, y, w, h);
			break;
		default:
			image = null;
		}

		return image;

	}

	// functie pentru citirea intregii imagini
	@Override
	public BufferedImage readFullImage(String fileName) {

		File in = new File(fileName); // creeaza un obiect de tip File
										// corespunzator numelui dat ca
										// parametru functiei
		// folosirea unui bloc try-catch pentru folosirea functiei read
		try {
			BufferedImage fullImage = ImageIO.read(in);
			return fullImage;
		} catch (IOException e) {

			System.out.println("Exception thrown when opening the file...");
			e.printStackTrace();
		}
		return null;

	}

	// metoda ce returneaza sfertul corespunzator parametrului q din fullImage
	@Override
	public BufferedImage getQuarter(BufferedImage fullImage, Integer q) {
		BufferedImage image;
		Integer width = fullImage.getWidth();
		Integer height = fullImage.getHeight();

		int x, y, w, h; //coordonatele pentru extragerea sferturilor in functie de ce sfert este extras

		switch (q) {
		case 0:
			x = 0;
			y = 0;
			w = Math.min(width, width / 2);
			h = Math.min(height, height / 2);
			image = fullImage.getSubimage(x, y, w, h);
			break;
		case 1:
			x = Math.min(width, width / 2);
			y = 0;
			w = Math.min(width, width / 2);
			h = Math.min(height, height / 2);
			image = fullImage.getSubimage(x, y, w, h);
			break;
		case 2:
			x = 0;
			y = Math.min(height, height / 2);
			w = Math.min(width, width / 2);
			h = Math.min(height, height / 2);
			image = fullImage.getSubimage(x, y, w, h);
			break;
		case 3:
			x = Math.min(width, width / 2);
			y = Math.min(height, height / 2);
			w = Math.min(width, width / 2);
			h = Math.min(height, height / 2);
			image = fullImage.getSubimage(x, y, w, h);
			break;
		default:
			image = null;
		}

		return image;
	}

}