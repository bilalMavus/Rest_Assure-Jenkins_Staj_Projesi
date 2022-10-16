package Case08.Model;

import Tools.School;

import java.util.Arrays;

public class Design08 {
    private boolean active;
    private String code;
    private String[] constants;
    private String id;
    private String name;
    private School school;
    private String school2;
    private String[] school3;
    private String[] sections;

    public String[] getSchool3() {
        return school3;
    }

    public void setSchool3(String[] school3) {
        this.school3 = school3;
    }

    public String getSchool2() {
        return school2;
    }

    public void setSchool2(String school2) {
        this.school2 = school2;
    }

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

    public String[] getConstants() {
        return constants;
    }

    public void setConstants(String[] constants) {
        this.constants = constants;
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

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String[] getSections() {
        return sections;
    }

    public void setSections(String[] sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        return "Design08{" +
                "active=" + active +
                ", code='" + code + '\'' +
                ", constants=" + Arrays.toString(constants) +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", school=" + school +
                ", school2='" + school2 + '\'' +
                ", school3=" + Arrays.toString(school3) +
                ", sections=" + Arrays.toString(sections) +
                '}';
    }
}
