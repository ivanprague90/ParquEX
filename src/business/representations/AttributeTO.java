package business.representations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AttributeTO implements Serializable {
	
    private static final long serialVersionUID = 1L;

    private String id;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String key;
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private List<String> values;
 
    public List<String> getValues() {
        if (values == null) {
            values = new ArrayList<String>();
        }
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

}
