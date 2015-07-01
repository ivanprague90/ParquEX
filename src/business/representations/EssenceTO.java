package business.representations;

import business.representations.essences.property.PropertyTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EssenceTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String description;
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    private List<PropertyTO> property;
    
    public List<PropertyTO> getProperty() {
        if (property == null) {
        	property = new ArrayList<PropertyTO>();
        }
        return property;
    }

    public void setIff(List<PropertyTO> property) {
        this.property = property;
    }

    private String name;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
