package imageProcessing;

public class ReadingMessage extends Message{
	
	public void writeMessage(int i, long time){
		switch(i){
		case 0: 
			System.out.println("\n" + "A fost citit sfertul " + (i+1) + " in " + time + " nanoseconde.");
			break;
		case 1: 
			System.out.println("\n" + "A fost citit al " + (i+1) + " sfert in " + time + " nanoseconde.");
			break;
		case 2: 
			System.out.println("\n" + "A fost citit al " + (i+1) + " sfert in " + time + " nanoseconde.");
			break;
		case 3: 
			System.out.println("\n" + "A fost citit al " + (i+1) + " sfert in " + time + " nanoseconde.");
			break;
		}
	}

}