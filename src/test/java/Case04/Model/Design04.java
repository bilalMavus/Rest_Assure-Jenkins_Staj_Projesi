package Case04.Model;

public class Design04 {

    private String id;
    private String name;
    private String translateName;
    private String code;

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

    public String getTranslateName() {
        return translateName;
    }

    public void setTranslateName(String translateName) {
        this.translateName = translateName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Design04{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", translateName='" + translateName + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
