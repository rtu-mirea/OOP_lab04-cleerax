import java.io.*;
import java.util.Arrays;
import java.util.RandomAccess;
import java.util.Scanner;
import java.util.LinkedList;

public class FileControl {

    public static void createFile(String fileName) {
        String[] files = new File(new File("").getAbsolutePath()).list();
        if (Arrays.asList(files).contains(fileName))
            System.out.println("Файл " + fileName + " уже создан.");
        else {
            try {
                File file1 = new File(fileName);
                file1.createNewFile();
                System.out.println("Файла " + fileName + " не было найдено.\r\nФайл создан.");
            } catch (IOException e) { System.out.println(e.toString()); }
        }
    }

    public static void addFilm(String fileName) {
        try {
            File file1 = new File(fileName);
            DataOutputStream out = new DataOutputStream(new FileOutputStream(file1.getAbsolutePath(), true));
            Scanner in = new Scanner(System.in);
            System.out.print("Введите название фильма: ");
            String title = in.nextLine();
            System.out.print("Введите название студии: ");
            String studio = in.nextLine();
            System.out.print("Введите год выпуска фильма: ");
            int year = Integer.parseInt(in.nextLine());
            System.out.print("Введите режиссера фильма: ");
            String director = in.nextLine().strip();
            System.out.print("Введите фамилию исполнителя главной роли: ");
            String leadRole = in.nextLine();

            out.writeUTF(title);
            out.writeUTF(studio);
            out.writeInt(year);
            out.writeUTF(director);
            out.writeUTF(leadRole);

            out.flush();
            out.close();

            System.out.println("\r\nФильм доабвлен\r\n");
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public static void outFilms(String fileName, LinkedList<Videofilm> list) {
        try {
            File file1 = new File(fileName);
            DataInputStream inp = new DataInputStream(new FileInputStream(file1.getAbsolutePath()));
            while (true) {
                Videofilm film = new Videofilm(inp.readUTF(), inp.readUTF(), inp.readInt(), inp.readUTF(), inp.readUTF());
                list.add(film);
                System.out.println("Фильм: " + film.getTitle() + "\r\nСтудия: " + film.getStudio() +
                        "\r\nГод выпуска: " + film.getYear() + "\r\nРежиссер: " + film.getDirector() +
                        "\r\nВ главной роли: " + film.getLeadRole() + "\r\n");
            }
        } catch (IOException e) {}
    }

    public static void sameDirector(String fileName, LinkedList<Videofilm> list, String director) {
        try {
            File file1 = new File(fileName);
            DataInputStream inp = new DataInputStream(new FileInputStream(file1.getAbsolutePath()));
            while (true) {
                String title = inp.readUTF();
                String studio = inp.readUTF();
                int year = inp.readInt();
                String Director = inp.readUTF();
                String leadRole = inp.readUTF();
                if (Director.equals(director)) {
                    Videofilm film = new Videofilm(title, studio, year, Director, leadRole);
                    list.add(film);
                }
            }
        } catch (IOException e) {}
    }

    public static void delOldest(String fileName) {
        LinkedList<Videofilm> list = new LinkedList<>();
        int i = 0, maxpos = 0, max = 10000;
        try {
            File file1 = new File(fileName);
            DataInputStream inp = new DataInputStream(new FileInputStream(file1.getAbsolutePath()));
            while (true) {
                String title = inp.readUTF();
                String studio = inp.readUTF();
                int year = inp.readInt();
                String Director = inp.readUTF();
                String leadRole = inp.readUTF();
                list.add(new Videofilm(title, studio, year, Director, leadRole));
                if (year <= max) {
                    max = year;
                    maxpos = i;
                }
                i++;
            }
        } catch (IOException e) {}
        Videofilm movie = list.remove(maxpos);
        try {
            File file1 = new File(fileName);
            file1.delete();
            file1.createNewFile();
            DataOutputStream out = new DataOutputStream(new FileOutputStream(file1.getAbsolutePath()));
            for (Videofilm film: list) {
                out.writeUTF(film.getTitle());
                out.writeUTF(film.getStudio());
                out.writeInt(film.getYear());
                out.writeUTF(film.getDirector());
                out.writeUTF(film.getLeadRole());
            }
            out.flush();
            out.close();
            System.out.println("Фильм \"" + movie.getTitle() + "\" удален");
        } catch (IOException e) {}
    }

    public static void raf(String fileName, String director) {
        try {
            File file1 = new File(fileName);
            File file2 = new File("raf.bin");

            RandomAccessFile in = new RandomAccessFile(file1, "r");
            RandomAccessFile out = new RandomAccessFile(file2, "rw");

            while (true) {
                String title = in.readUTF();
                String studio = in.readUTF();
                int year = in.readInt();
                String Director = in.readUTF();
                String leadRole = in.readUTF();
                if (Director.equals(director)) {
                    out.writeBytes(title);
                    out.writeBytes(studio);
                    out.writeInt(year);
                    out.writeBytes(Director);
                    out.writeBytes(leadRole);
                }
            }
        } catch (IOException e) {}
    }
}
