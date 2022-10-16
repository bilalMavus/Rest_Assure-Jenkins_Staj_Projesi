package Case04.Model;

import java.util.Arrays;

public class Design04 {
    private String id;
    private String name;
    private String[] translateName;
    private String code;
    private String schoolId;
    private String[] children;
    private String systemField;
    private String systemFieldName;
    private String type;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String[] getChildren() {
        return children;
    }

    public void setChildren(String[] children) {
        this.children = children;
    }

    public String getSystemField() {
        return systemField;
    }

    public void setSystemField(String systemField) {
        this.systemField = systemField;
    }

    public String getSystemFieldName() {
        return systemFieldName;
    }

    public void setSystemFieldName(String systemFieldName) {
        this.systemFieldName = systemFieldName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Design04{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", translateName=" + Arrays.toString(translateName) +
                ", code='" + code + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", children=" + Arrays.toString(children) +
                ", systemField='" + systemField + '\'' +
                ", systemFieldName='" + systemFieldName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
