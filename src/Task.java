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
