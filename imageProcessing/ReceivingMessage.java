package imageProcessing;

public class ReceivingMessage extends Message{
	
	public void writeMessage(int i, long time){
			
		switch(i){
		case 0: 
			System.out.println("S-a primit sfertul nr. " + (i+1) + " in " + time + " nanoseconde.");
			break;
		case 1: 
			System.out.println("S-a primit sfertul nr. " + (i+1) + " in " + time + " nanoseconde.");
			break;
		case 2: 
			System.out.println("S-a primit sfertul nr. " + (i+1) + " in " + time + " nanoseconde.");
			break;
		case 3: 
			System.out.println("S-a primit sfertul nr. " + (i+1) + " in " + time + " nanoseconde.");
			break;
		}
	}
	
}