package Case11.Model;

import java.util.Arrays;

public class Design11 {
    private String id;
    private String code;
    private String description;
    private String priority;
    private String[] translateDescription;
    private boolean active;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String[] getTranslateDescription() {
        return translateDescription;
    }

    public void setTranslateDescription(String[] translateDescription) {
        this.translateDescription = translateDescription;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Design11{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", translateDescription=" + Arrays.toString(translateDescription) +
                ", active=" + active +
                '}';
    }
}
