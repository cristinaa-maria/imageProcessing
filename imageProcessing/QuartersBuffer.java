package imageProcessing;

import java.awt.image.BufferedImage;

public class QuartersBuffer {

	private BufferedImage[] imageQuarters;
	private final static Integer capacity;
	private int currentQuarter;
	private int counter;

	// bloc de initializare pentru variabilele currentQuarter si counter
	{
		currentQuarter = 0;
		counter = 0;
	}
	// blocul static de initializare al variabilei capacity
	static {
		capacity = 4;
	}

	public QuartersBuffer() {
		imageQuarters = new BufferedImage[4]; // in constructor alocam 4
												// elemente de tip BufferedImage
												// pentru sferturile in care va
												// fi descompusa imaginea
												// primita din Producer
	}

	// metoda prin care Producer pune cate un sfert din imagine in Buffer-ul
	// comun
	public synchronized void put(BufferedImage quarter) {
		while (imageQuarters[capacity - 1] != null) {
			try {
				// asteapta sa ia Consumer un sfert din imaginea origiala
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// apoi pune in Buffer un nou element
		imageQuarters[currentQuarter] = quarter;
		currentQuarter++; // incrementeaza atat counterul cat si sfertul curent
							// pentru a stii cate elemente sunt in buffer
		counter++;
		notifyAll();// anunta Consumer ca a adaugat un element in Buffer

	}

	// metoda prin care Consumer are acces si ia cate un element de tip
	// BufferedImage din Buffer-ul comun
	public synchronized BufferedImage get(int index) throws InterruptedException {
		while (counter <= index) {
			// asteapta sa puna Producer un sfert din imaginea originala
			wait();
		}

		BufferedImage item = imageQuarters[index];
		notifyAll(); // anunta Producer ca a luat un element din Buffer
		return item;
	}

	public int getCurrentQuarter() {
		return currentQuarter;
	}

	public void setCurrentQuarter(int currentQuarter) {
		this.currentQuarter = currentQuarter;
	}

	public BufferedImage[] getImageQuarters() {
		return imageQuarters;
	}

	public void setImageQuarters(BufferedImage[] imageQuarters) {
		this.imageQuarters = imageQuarters;
	}

	public int getCounter() {
		return counter;
	}
	public int getCapacity() {
		return capacity;
	}


}