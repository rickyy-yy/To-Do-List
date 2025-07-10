import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Book {
    public HashMap<Integer,List<Task>> entries = new HashMap<>();
    public int maxPageSize = 10;

    public Book(){
        this.entries.put(1, new ArrayList<Task>());
    }

    public Boolean isPageFull(){
        return this.entries.get(this.entries.size()).size() >= maxPageSize;
    }

    private void createNewPage(){
        this.entries.put(this.entries.size() + 1, new ArrayList<Task>());
    }

    public void writeToBook(Task task){
        if (isPageFull()){
            createNewPage();
            writeToBook(task);
        }
        else{
            List<Task> temp = this.entries.get(this.entries.size());
            temp.add(task);
            this.entries.put(this.entries.size(), temp);
        }
    }

    public void readFromPage(int index){
        String showing = String.format("|| Showing Page %s/%s ||", index, this.entries.size());
        String header = String.format("%-5s %s %-20s %s %-15s %s %-15s %s %-15s", "ID", " | ", "Task", " | ", "Category", " | ", "Deadline", " | ", "Completed");
        String divider = "-".repeat(84);
        System.out.println(showing);
        System.out.println(header);
        System.out.println(divider);

        List<Task> tasks = this.entries.get(index);

        for (Task task : tasks){
            int id = task.getID();
            String body = task.getBody();
            Main.Categories category = task.getCategory();
            LocalDate deadline = task.getDeadline();
            Boolean completed = task.getCompleted();

            String entry = String.format("%-5s %s %-20s %s %-15s %s %-15s %s %-15s", id, " | ", body, " | ", category, " | ", deadline, " | ", completed);
            System.out.println(entry);
        }
    }

    public void fillBook(){
        try{
            this.entries.clear();
            this.entries.put(1, new ArrayList<Task>());

            File f = new File(Main.filepath);
            Scanner s = new Scanner(f);
            List<String> fileContents = new ArrayList<>();
            String[] entryDetails = new String[5];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            formatter = formatter.withLocale(Locale.ENGLISH);

            while (s.hasNextLine()){
                fileContents.add(s.nextLine());
            }
            for (String task_entry : fileContents){
                entryDetails = task_entry.split("`");
                int id = Integer.parseInt(entryDetails[0]);
                String body = entryDetails[1];
                Main.Categories category = Main.Categories.valueOf(entryDetails[2]);
                LocalDate deadline = LocalDate.parse(entryDetails[3], formatter);
                Boolean completed = Boolean.parseBoolean(entryDetails[4]);
                Task task = new Task(id, body, category, deadline, completed);
                writeToBook(task);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Tasks.txt was not found!");
        }
    }

    public void saveBook(){
        try{
            File f = new File(Main.filepath);
            FileWriter writer = new FileWriter(Main.filepath);
            int pageNumber = 0;
            String temp;

            for (int index : this.entries.keySet()){
                List<Task> page = this.entries.get(index);
                pageNumber++;
                int lineNumber = 0;

                for (Task task : page){
                    lineNumber++;
                    int id = task.getID();
                    String body = task.getBody();
                    Main.Categories category = task.getCategory();
                    LocalDate deadline = task.getDeadline();
                    Boolean completed = task.getCompleted();

                    if (lineNumber != 1 || pageNumber != 1){
                        temp = String.format("%n%s`%s`%s`%s`%s", id, body, category, deadline, completed);
                    }
                    else{
                        temp = String.format("%s`%s`%s`%s`%s", id, body, category, deadline, completed);
                    }
                    writer.write(temp);
                }
            }

            writer.close();
        }
        catch (IOException e){
            System.out.println("Something went wrong saving the tasks!");
        }
    }
}
