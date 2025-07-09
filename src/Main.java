import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String filepath = "C:\\Users\\Richie\\Documents\\Java\\To-Do List Console Version 2\\Tasks.txt";
    public static Book book = new Book();

    public enum Categories{
        General,
        Important,
        Unimportant,
        Urgent
    }

    public static void main(String[] args) {
        checkFiles();
        book.fillBook();
        mainMenu();
    }

    private static void mainMenu(){
        System.out.println("=======Welcome to the To-Do List App=======");
        System.out.println();
        System.out.println("1) View Tasks");
        System.out.println("2) Add Tasks");
        System.out.println("3) Delete Tasks");
        System.out.println("4) Exit Application");
        System.out.println();
        System.out.println("===========================================");

        Scanner s = new Scanner(System.in);
        System.out.print("Enter your selection (1/2/3/4): ");
        try{
            int selection = s.nextInt();
            System.out.println();
            switch (selection) {
                case 1:
                    viewTasks(1);
                    break;
                case 2:
                    // Add tasks
                    break;
                case 3:
                    // Delete tasks
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    mainMenu();
                    break;
            }
        }
        catch (InputMismatchException e){
            System.out.println();
            System.out.println("You need to enter a numerical value between 1 to 4!");
            System.out.println();
            mainMenu();
        }
    }

    private static void checkFiles(){
        try{
            File f = new File(filepath);
            Boolean result = f.createNewFile();
        }
        catch (IOException | SecurityException e){
            System.out.println("An error has occurred when creating Tasks.txt!");
        }
    }

    private static void viewTasks(int index){
        book.readFromPage(index);
        selectPageToView(index);
    }

    private static void selectPageToView(int currentIndex){
        try{
            Scanner s = new Scanner(System.in);
            System.out.println();
            System.out.printf("Enter page number (1 - %s) to view or 0 to return to main menu: ", book.entries.size());

            int selection = s.nextInt();
            System.out.println();
            if (selection == 0){
                mainMenu();
            }
            if (selection == currentIndex){
                System.out.println("You are already viewing this page!");
                selectPageToView(currentIndex);
            }
            if (selection >= 0 && selection <= book.entries.size()){
                viewTasks(selection);
            }
        }
        catch (NumberFormatException e){
            System.out.println();
            System.out.printf("You must enter an integer between 0 and %s%n!", book.entries.size());
            selectPageToView(currentIndex);
        }
    }
}