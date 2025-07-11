import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
                    createTask();
                    mainMenu();
                    break;
                case 3:
                    deleteTask();
                    mainMenu();
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

    private static Boolean checkValid(String string){
        return string.contains("`");
    }

    private static int getLastID(Book book){
        List<Task> tasks = book.entries.get(book.entries.size());
        if (tasks.isEmpty()){
            return 0;
        }
        Task temp = tasks.getLast();
        return temp.getID();
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
            else{
                System.out.println();
                System.out.printf("You must enter an integer between 0 and %s!%n", book.entries.size());
                selectPageToView(currentIndex);
            }
        }
        catch (NumberFormatException e){
            System.out.println();
            System.out.printf("You must enter an integer between 0 and %s!%n", book.entries.size());
            selectPageToView(currentIndex);
        }
    }

    private static void createTask(){
        Scanner s = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.ENGLISH);

        String body = "";
        Categories category;
        int intDay;
        int intMonth;
        int intYear;
        String day;
        String month;

        LocalDate deadline;

        System.out.println("=======Task Creation Menu=======");
        System.out.println();
        System.out.println("Please note that you are not allowed to use the '`' character in any input!");
        System.out.println();
        while (checkValid(body) || body.length() > 25 || body.isEmpty()){  // Get body
            System.out.print("Enter task name (Maximum 25 characters): ");
            body = s.nextLine();
        }
        categoryLoop:
        while (true){  // Get category
            System.out.print("Enter task category (General(G), Important(I), Unimportant(U), Urgent(X)): ");
            String categorySelection = s.nextLine();
            switch (categorySelection) {
                case "G":
                    category = Categories.General;
                    break categoryLoop;
                case "I":
                    category = Categories.Important;
                    break categoryLoop;
                case "U":
                    category = Categories.Unimportant;
                    break categoryLoop;
                case "X":
                    category = Categories.Urgent;
                    break categoryLoop;
                default:
            }
        }
        while (true){
            try{
                System.out.print("Enter task year deadline (2025 onwards): ");
                intYear = s.nextInt();
                if (intYear >= 2025){
                    break;
                }
            }
            catch (NumberFormatException _){}
        }
        while (true){
            try{
                System.out.print("Enter task month deadline (1-12): ");
                intMonth = s.nextInt();
                if (intMonth >= 1 && intMonth <= 12){
                    month = String.valueOf(intMonth);
                    if (month.length() == 1){
                        month = "0" + month;
                    }
                    break;
                }
            }
            catch (NumberFormatException _){}
        }
        while (true){
            try{
                if (intYear % 4 == 0 && intMonth == 2){
                    System.out.print("Enter task day deadline (1-29): ");
                    intDay = s.nextInt();
                    if (intDay >= 1 && intDay <= 29){
                        day = String.valueOf(intDay);
                        if (day.length() == 1){
                            day = "0" + day;
                        }
                        break;
                    }
                }
                if (intMonth == 2){
                    System.out.print("Enter task day deadline (1-28): ");
                    intDay = s.nextInt();
                    if (intDay >= 1 && intDay <= 28){
                        day = String.valueOf(intDay);
                        if (day.length() == 1){
                            day = "0" + day;
                        }
                        break;
                    }
                }
                if (intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11){
                    System.out.print("Enter task day deadline (1-30): ");
                    intDay = s.nextInt();
                    if (intDay >= 1 && intDay <= 30){
                        day = String.valueOf(intDay);
                        if (day.length() == 1){
                            day = "0" + day;
                        }
                        break;
                    }
                }
                if (intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7 || intMonth == 8 || intMonth == 10 || intMonth == 12){
                    System.out.print("Enter task day deadline (1-31): ");
                    intDay = s.nextInt();
                    if (intDay >= 1 && intDay <= 31){
                        day = String.valueOf(intDay);
                        if (day.length() == 1){
                            day = "0" + day;
                        }
                        break;
                    }
                }
            }
            catch (NumberFormatException _){}
        }

        String temp = String.format("%s-%s-%s", intYear, month, day);
        deadline = LocalDate.parse(temp, formatter);

        Task task = new Task(getLastID(book) + 1, body, category, deadline, false);
        book.writeToBook(task);
        book.saveBook();
        System.out.println();
        System.out.println("Task added successfully!");
        System.out.println();
    }

    private static void deleteTask(){
        int deletionID;
        int page = 1;
        String confirm = "";
        Scanner s = new Scanner(System.in);

        while (true){
            try{
                System.out.println();
                System.out.println("=====Task Deletion Menu=====");
                System.out.println();
                System.out.printf("Enter task ID (1-%s): ", getLastID(book));

                deletionID = s.nextInt();

                if (deletionID >= 1 && deletionID <= getLastID(book)){
                    break;
                }
            }
            catch (NumberFormatException e){
                System.out.println();
                System.out.println("You need to enter an integer!");
            }
        }

        for ( List<Task> tasks : book.entries.values()){
            for (Task task : tasks){
                if (task.getID() == deletionID){
                    task.getSummary();
                }
            }
        }

        while (!confirm.equals("Y") && !confirm.equals("N")){
            System.out.print("Do you wish to proceed? (Y/N): ");
            confirm = s.next();
            System.out.println();
        }

        if (confirm.equals("Y")){
            page = (deletionID + 10) / 10;
            List<Task> tasks = book.entries.get(page);
            for (Task task : tasks){
                if (task.getID() == deletionID){
                    tasks.remove(task);
                    break;
                }
            }
            book.saveBook();
        }
        else{
            System.out.println("Deletion aborted!");
            System.out.println();
            mainMenu();
        }

        Book tempBook = new Book();

        for (List<Task> tasks : book.entries.values()){
            for (Task task : tasks){
                int id = getLastID(tempBook) + 1;
                String body = task.getBody();
                Categories category = task.getCategory();
                LocalDate deadline = task.getDeadline();
                Boolean completed = task.getCompleted();

                Task tempTask = new Task(id, body, category, deadline, completed);
                tempBook.writeToBook(tempTask);
                tempBook.saveBook();
                tempBook.fillBook();
            }
        }
        book = tempBook;
        book.saveBook();
        System.out.println("Task deleted successfully!");
        System.out.println();
    }
}