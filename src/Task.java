import java.time.LocalDate;

public class Task {
    public int id;
    public String body;
    public Main.Categories category;
    public LocalDate deadline;
    public Boolean completed;

    public Task(int id, String body, Main.Categories category, LocalDate deadline, Boolean completed){
        this.id = id;
        this.body = body;
        this.category = category;
        this.deadline = deadline;
        this.completed = completed;
    }

    // Get methods

    public int getID(){
        return this.id;
    }

    public String getBody(){
        return this.body;
    }

    public Main.Categories getCategory(){
        return this.category;
    }

    public LocalDate getDeadline(){
        return this.deadline;
    }

    public Boolean getCompleted(){
        return this.completed;
    }

    public void getSummary(){
        System.out.println();
        System.out.println("=====Task Summary=====");
        System.out.println();
        System.out.printf("ID: %s%n", this.id);
        System.out.printf("Task: %s%n", this.body);
        System.out.printf("Category: %s%n", this.category);
        System.out.printf("Deadline: %s%n", this.deadline);
        System.out.printf("Completed: %s%n", this.completed);
        System.out.println();
    }

    // Set methods

    public void setID(int id){
        this.id = id;
    }

    public void setBody(String body){
        this.body = body;
    }

    public void setCategory(Main.Categories category){
        this.category = category;
    }

    public void setDeadline(LocalDate deadline){
        this.deadline = deadline;
    }

    public void setCompleted(Boolean completed){
        this.completed = completed;
    }
}
