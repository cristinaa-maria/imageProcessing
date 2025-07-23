package testing;
import java.io.*;
import imageProcessing.*;

public class MainClass {

    // metoda folosita pentru extragerea extensiei unui fisier dat ca parametru
    public static String getFileExtension(String fullName) {
        String fileName = new File(fullName).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    public static void setupFiles(String... fileNames) {

        // cazul in care se cere doar fisierul de intrare de la user
        System.out.println("Introduceti numele fisierului(trebuie sa aiba extensia .bmp)");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            fileNames[0] = reader.readLine().trim(); // trim() added here
        } catch (IOException e) {
            System.out.println("Exception thrown when reading from console...");
            e.printStackTrace();
        }

        File inputFile = new File(fileNames[0]);

        while (!MainClass.getFileExtension(fileNames[0]).equals("bmp") || !inputFile.exists()) {
            // verificarea numelui fisierului de intrare : trebuie sa aiba extensia bmp si sa existe in directorul curent
            if (!MainClass.getFileExtension(fileNames[0]).equals("bmp"))
                System.out.println("Fisierul trebuie sa aiba extensia .bmp!");
            else if (!inputFile.exists())
                System.out.println("Inexistent file with specified name");
            else
                System.out.println("Fisierul a fost gasit!");

            try {
                fileNames[0] = reader.readLine().trim(); // trim() added here
            } catch (IOException e) {
                System.out.println("Exception thrown when reading from console...");
                e.printStackTrace();
            }
            inputFile = new File(fileNames[0]);
        }

        System.out.println("Acceptat! Numele fisierul: " + fileNames[0]);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String[] fileNames;
        String inputFileName = null;
        String outputFileName = "output.bmp";

        QuartersBuffer buffer = new QuartersBuffer();

        PipedOutputStream outStream = new PipedOutputStream();
        PipedInputStream inStream = new PipedInputStream(outStream, 5000054);

        // utilizatorul poate alege sa introduca doar numele fisierului de intrare, iar cel de iesire sa ramana default, sau sa le dea pe ambele
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        fileNames = new String[1];
        setupFiles(fileNames);
        inputFileName = fileNames[0];
        outputFileName = generateZoomedFileName(inputFileName); // Generate output file name based on input file name
        

        // se creeaza obiectele de tip ProducerClass, WriterResult si ConsumerClass
        Producer prod = new Producer(buffer, inputFileName);
        Consumer cons = new Consumer(buffer, outStream);
        WriterResult writer = new WriterResult(inStream, outputFileName);

        // se pornesc thread-urile corespunzatoare
        prod.start();
        cons.start(); // rol de consumer in relatia cu producer si de producer in relatia cu writer

        new Thread(writer::run).start(); // al doilea "consumer"
        try {
            prod.join();
            cons.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Metoda de generare a fisierului cu imaginea cu zoom
    private static String generateZoomedFileName(String inputFileName) {
        int dotIndex = inputFileName.lastIndexOf('.');
        String fileNameWithoutExtension = (dotIndex == -1) ? inputFileName : inputFileName.substring(0, dotIndex);
        return fileNameWithoutExtension + "_zoomed.bmp";
    }
}
