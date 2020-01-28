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
        while (in.hasNextLine()) {
            text += in.nextLine() + " ";
        }
        in.close();
        return new lab2_1(text);
    }
}
