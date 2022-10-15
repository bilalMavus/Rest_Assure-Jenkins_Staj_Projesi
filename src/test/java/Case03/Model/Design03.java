package Case03.Model;

public class Design03 {
    String id;
    String name;
    String attachmentStages;
    String description;
    String schoolId;
    String translateName;
    boolean active;
    boolean required;
    boolean useCamera;

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

    public String getAttachmentStages() {
        return attachmentStages;
    }

    public void setAttachmentStages(String attachmentStages) {
        this.attachmentStages = attachmentStages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getTranslateName() {
        return translateName;
    }

    public void setTranslateName(String translateName) {
        this.translateName = translateName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isUseCamera() {
        return useCamera;
    }

    public void setUseCamera(boolean useCamera) {
        this.useCamera = useCamera;
    }

    @Override
    public String toString() {
        return "Design03{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", attachmentStages='" + attachmentStages + '\'' +
                ", description='" + description + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", translateName='" + translateName + '\'' +
                ", active=" + active +
                ", required=" + required +
                ", useCamera=" + useCamera +
                '}';
    }
}
