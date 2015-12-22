import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bogdan on 21.12.2015.
 */
public class IORefactor {

    public void scanFile(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        List<String> lines = new ArrayList<String>();
        while ((line = reader.readLine()) != null) {

            String s = "";
            SplitString splitString = new SplitString();
            s = splitString.tokenazer(line);
            System.out.println(s);

            write(path, s);

            lines.add(line);
        }

        String [] linesAsArray = lines.toArray(new String[lines.size()]);
    }

    public static void write(String fileName, String text) {
        File file = new File(fileName);

        try {
            if(!file.exists()){
                file.createNewFile();
            }

            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                out.print(text);
            } finally {
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
