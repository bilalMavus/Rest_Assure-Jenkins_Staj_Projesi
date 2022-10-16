package Case10.Model;

import java.util.Arrays;

public class Design10 {

    private boolean active;
    private String id;
    private String name;
    private String nextGradeLevel;
    private String order;
    private String shortName;
    private String[] translateName;
    private String[] translateShortName;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public String getNextGradeLevel() {
        return nextGradeLevel;
    }

    public void setNextGradeLevel(String nextGradeLevel) {
        this.nextGradeLevel = nextGradeLevel;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String[] getTranslateName() {
        return translateName;
    }

    public void setTranslateName(String[] translateName) {
        this.translateName = translateName;
    }

    public String[] getTranslateShortName() {
        return translateShortName;
    }

    public void setTranslateShortName(String[] translateShortName) {
        this.translateShortName = translateShortName;
    }

    @Override
    public String toString() {
        return "Design10{" +
                "active=" + active +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nextGradeLevel='" + nextGradeLevel + '\'' +
                ", order='" + order + '\'' +
                ", shortName='" + shortName + '\'' +
                ", translateName=" + Arrays.toString(translateName) +
                ", translateShortName=" + Arrays.toString(translateShortName) +
                '}';
    }
}
