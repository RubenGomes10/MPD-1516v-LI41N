package executearoundpattern;


import java.io.*;

/**
 * Created by lfalcao on 01/04/16.
 */


public class FileProcessorWithoutCodeRepetition {

    interface BufferedReaderProceessor {
        String process(BufferedReader reader) throws IOException;
    }

    public static String processFileReturningFirstLine(String fileName) throws IOException {
//        process(new BufferedReaderProceessor() {
//            @Override
//            public String process(BufferedReader reader) throws IOException {
//                return reader.readLine();
//            }
//        });

        process(fileName, reader -> reader.readLine());

    }
}

    public static String processFileReturnAllLines(String fileName) throws IOException {
//        process(new BufferedReaderProceessor() {
//            @Override
//            public String process(BufferedReader reader) throws IOException {
//                StringBuffer str = new StringBuffer();
//                for (String line; (line = reader.readLine()) != null; ) {
//                    str.append(line);
//                }
//                return str.toString();
//            }
//        });

        process(fileName, reader -> readAllLines(reader));
    }

    private static String readAllLines(BufferedReader reader) throws IOException {
        StringBuffer str = new StringBuffer();
        for (String line; (line = reader.readLine()) != null; ) {
            str.append(line);
        }
        return str.toString();
    }


    private static String process(String fileName, BufferedReaderProceessor processor) throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return processor.process(reader);
        }
    }
}
