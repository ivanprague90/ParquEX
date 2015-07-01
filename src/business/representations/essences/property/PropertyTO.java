package business.representations.essences.property;

import java.io.Serializable;

public class PropertyTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String value;
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String key;
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
