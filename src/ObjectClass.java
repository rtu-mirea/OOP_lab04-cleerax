import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ObjectClass {

    private lab2_1 a = new lab2_1();

    public void consoleInput() {
        this.a.setCode(new Scanner(System.in).nextLine());
    }

    public lab2_1 GET9() { return this.a; }

    public void zealotDanielObject(String filePath) throws FileNotFoundException {
        try {
            ClassTextFile freak = new ClassTextFile(filePath);
            this.a = freak.tripleTwo();
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода");
        }
    }
}
