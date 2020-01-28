import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SerializationClass {

    private String filePath;
    private ArrayList<lab2_1> list;

    public SerializationClass(String filePath) {
        this.filePath = filePath;
        this.list = new ArrayList<>();
    }

    public void writeObject(lab2_1 obj) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.filePath));
        out.writeObject(obj);
        out.close();
    }

    public lab2_1 readObject() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.filePath));
        lab2_1 cringe = (lab2_1) in.readObject();
        in.close();
        return cringe;
    }

    public lab2_1 getObject(int a) { return list.get(a); }

    public void coll() throws IOException {
        Scanner in = new Scanner(System.in);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.filePath, true));
        lab2_1 obj;
        System.out.println("Введите количество объектов: ");
        int count = in.nextInt();
        in.nextLine();
        for (int i = 0; i < count; i++){
            System.out.println("Введите строку для объекта №" + (i + 1));
            obj = new lab2_1();
            obj.setCode(in.nextLine());
            list.add(obj);
            out.writeObject(obj);
        }
        out.close();
    }

    public void toColl() throws IOException{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.filePath));
        list = new ArrayList<>();
        try {
            while (true) {
                list.add((lab2_1) in.readObject());
            }
        } catch (Exception e) {}
    }

    public ArrayList<lab2_1> getColl() { return this.list; }

    public void print() {
        try {
            System.out.println("Содержимое файла:");
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.filePath));
            int i = 1;
            while (true) {
                lab2_1 obj = (lab2_1) in.readObject();
                System.out.println(i + " объект: код: " + obj.getCode());
                i += 1;
            }
        } catch (IOException e) {}
        catch (ClassNotFoundException e) {
            System.out.println("Класс не найден ексепшон");
        }
        System.out.println("Содержимое коллекции:");
        int i = 1;
        for (lab2_1 o: list) {
            System.out.println(i + " объект: код: " + o.getCode());
        }
    }
}
