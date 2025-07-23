package imageProcessing;

import java.awt.image.BufferedImage;


public class Producer extends Thread{

	private QuartersBuffer buffer;
	private ImageReader imageReader;
	
	public QuartersBuffer getBuffer() {
		return buffer;
	}

	public void setBuffer(QuartersBuffer buffer) {
		this.buffer = buffer;
	}

	public ImageReader getImageReader() {
		return imageReader;
	}

	public void setImageReader(ImageReader imageReader) {
		this.imageReader = imageReader;
	}

	public Producer(QuartersBuffer buffer, String fileName) {
		
		this.buffer = buffer;
		imageReader = new ImageReader(fileName);
	}

	@Override
	public void run() {
		
		try{
			for(int i = 0; i < 4; i++)
			{
				long startTime = System.nanoTime();
				BufferedImage currentQuarter = imageReader.readQuarter(i); //apeleaza metoda de citire a sferturilor corespunzatoare din imaginea initiala pentru prelucrare
				long endTime = System.nanoTime();
				ReadingMessage mess = new ReadingMessage();
				mess.writeMessage(i, endTime - startTime); // metoda pentru afisarea mesajului la consola pentru confirmarea citirii si afisarea timpului necesar in nanosecunde
				
				buffer.put(currentQuarter); //punerea in buffer a sfertului extras din imagine
				buffer.setCurrentQuarter(i + 1); //avansarea in buffer pentru procesarea sfertului urmator
				
				Thread.sleep(1000);
				
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	
	
	
	
}