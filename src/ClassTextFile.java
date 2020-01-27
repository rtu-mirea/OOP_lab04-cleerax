import java.io.*;
import java.util.Scanner;

public class ClassTextFile {
    private String filePath;

    public ClassTextFile(String filePath) throws FileNotFoundException {
        if (new File(filePath).exists())
            this.filePath = filePath;
        else
            throw new FileNotFoundException();
    }

    public lab2_1 tripleTwo() throws IOException{
        Scanner in = new Scanner(new DataInputStream(new FileInputStream(new File(this.filePath))));
        String text = "";
        String line;
        while ((line = in.nextLine()) != null) {
            text += line + " ";
        }
        return new lab2_1(text);
    }
}
