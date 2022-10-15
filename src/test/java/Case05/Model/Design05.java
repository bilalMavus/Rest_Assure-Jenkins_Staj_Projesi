package Case05.Model;

import java.util.Arrays;

public class Design05 {

   private String id;
   private String name;
   private String shortName;
   private String tenantId;
   private String[] translateName;

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String[] getTranslateName() {
        return translateName;
    }

    public void setTranslateName(String[] translateName) {
        this.translateName = translateName;
    }

    @Override
    public String toString() {
        return "Design05{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", translateName=" + Arrays.toString(translateName) +
                '}';
    }
}
