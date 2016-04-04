package executearoundpattern;


import java.io.*;

/**
 * Created by lfalcao on 01/04/16.
 */
public class FileProcessorWithCodeRepetition {
    public static String processFileReturningFirstLine(String fileName) throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.readLine();
        }
    }

    public static String processFileReturnAllLines(String fileName) throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.readLine();
        }
    }

    private static void close(Closeable r) {
        try {
            r.close();
        } catch (IOException e) {
            try {
                r.close();
            } catch (IOException e1) {
                // Log exception
            }
        }
    }
}
