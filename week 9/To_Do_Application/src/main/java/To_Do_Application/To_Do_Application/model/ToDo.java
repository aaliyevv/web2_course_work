package To_Do_Application.To_Do_Application.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ToDo {
    @Id
    private String id;
    private String title;
    private String description;
    private String person;
    private boolean isComplete;

    // Constructors
    public ToDo() {}

    public ToDo(String title, String description, String person, boolean isComplete) {
        this.title = title;
        this.description = description;
        this.person = person;
        this.isComplete = isComplete;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
