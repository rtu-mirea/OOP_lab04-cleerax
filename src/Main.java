import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        try {
            //Задание 1
            //Упражнение 1. Исследовать возможности класса File по созданию файлов (пустых) и папок программой.
            //Применение конструктора и метода.
            File file1 = new File("MyFile1.txt");
            file1.createNewFile();
            File file2 = new File("MyFile2.txt");
            file2.createNewFile();
            File file3 = new File("/Users/cleerax/Documents/уни_ыер/3_семестр/ооп/Lab4/MyFile3.txt");
            file3.createNewFile();
            File file4 = new File("/Users/cleerax/Documents/уни_ыер/3_семестр/ооп/Lab4/pupkis");
            file4.mkdir();

            //Упражнение 2. Получить параметры файлов методами класса File

            if (file1.isFile()) {
                System.out.println("Файл " + file1.getName() + ", file1 в папке " + file1.getParent() + " является файлом");
            } else {
                System.out.println("File1 не является файлом");
            }
            if (file2.isFile()) {
                System.out.println("Файл " + file2.getName() + ", file2 в папке " + file2.getParent() + " является файлом");
            } else {
                System.out.println("File2 не является файлом");
            }
            if (file3.isFile()) {
                System.out.println("Файл " + file3.getName() + ", file3 в папке " + file3.getParent() + " является файлом");
            } else {
                System.out.println("File3 не является файлом");
            }

            if (file4.isDirectory()) {
                System.out.println("Файл " + file4.getName() + ", file4 является папкой");
            } else {
                System.out.println("File4 не папка");
            }
            System.out.println();

            String[] files = new File(new File("").getAbsolutePath()).list();
            if (Arrays.asList(files).contains("MyFile1.txt"))
                System.out.println("MyFile1.txt есть в папке приложения");
            else
                System.out.println("нету");

            System.out.println(file1.getAbsolutePath());
            System.out.println(file4.getAbsolutePath());

            System.out.println("Размер файла " + file1.getName() + ": " + file1.length() + " байтов");
            System.out.println("Размер файла " + file2.getName() + ": " + file2.length() + " байтов");
            System.out.println("Размер файла " + file3.getName() + ": " + file3.length() + " байтов");
            System.out.println("Размер папки " + file4.getName() + ": " + file4.length() + " байтов");
            System.out.println();

            //Упражнение 3. Модификация файловой структуры приложения средствами класса File.
            File folder = new File("bruver");
            folder.mkdir();
            for (String o: files)
                System.out.println(o);
            System.out.println();

            int i = 0;
            File[] folders = new File(new File("").getAbsolutePath()).listFiles();
            for (File o: folders) {
                System.out.println(o.getName());
                if (o.isDirectory())
                    i++;
            }
            System.out.println("В папке приложения " + i + " папок");

            file1.delete();
            file2.delete();
            file3.delete();
            file4.delete();
            folder.delete();

            //Задание 2

            Scanner sc = new Scanner(System.in);
            System.out.print("Введите название файла: ");
            String fileName = sc.nextLine();
            FileControl.createFile(fileName);
            String choice = "";
            while (!choice.equals("0")) {
                showMenu();
                choice = sc.nextLine();

                switch(choice) {
                    case("1"):
                        FileControl.addFilm(fileName);
                        break;
                    case("2"):
                        LinkedList<Videofilm> list = new LinkedList<>();
                        FileControl.outFilms(fileName, list);
                        break;
                    case("3"):
                        System.out.println("Введите режиссера");
                        String director = sc.nextLine();
                        LinkedList<Videofilm> listDir = new LinkedList<>();
                        FileControl.sameDirector(fileName, listDir, director.strip());
                        System.out.println("Фильмы режссера " + director + ":\r\n");
                        for (Videofilm film: listDir) {
                            System.out.println("Фильм: " + film.getTitle() + "\r\nСтудия: " + film.getStudio() +
                                    "\r\nГод выпуска: " + film.getYear() + "\r\nРежиссер: " + film.getDirector() +
                                    "\r\nВ главной роли: " + film.getLeadRole() + "\r\n");
                        }
                        break;
                    case("4"):
                        FileControl.delOldest(fileName);
                        break;
                    case("0"):
                        break;
                    default:
                        System.out.println("Ошибка, повторите ввод");
                }
            }

        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода");
        } catch(Exception e) {
            System.out.println("Ошибка");
        }
    }

    public static void showMenu() {
        System.out.println("1. Добавить фильм\r\n" +
                "2. Вывести список фильмов\r\n" +
                "3. Вывести фильмы одного режиссера\r\n" +
                "4. Удалить запись по самому старому виедофильме (оригинальная орфография сохранена)\r\n" +
                "0. Выход");
    }
}