package Case06.Model;

import java.util.Arrays;

public class Design06 {
    private boolean active;
    private String code;
    private String id;
    private String name;
    private String[] translateName;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getTranslateName() {
        return translateName;
    }

    public void setTranslateName(String[] translateName) {
        this.translateName = translateName;
    }

    @Override
    public String toString() {
        return "Design06{" +
                "active=" + active +
                ", code='" + code + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", translateName=" + Arrays.toString(translateName) +
                '}';
    }
}
