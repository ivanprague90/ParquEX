package business.representations.rules.then;

import java.io.Serializable;

import business.Attribute;

public class ThenTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String value;
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String idAttribute;
    
    public String getIdAttribute() {
        return idAttribute;
    }

    public void setIdAttribute(String idAttribute) {
        this.idAttribute = idAttribute;
    }

    private String isOrNot;
    
    public String getIsOrNot() {
        return isOrNot;
    }

    public void setIsOrNot(String isOrNot) {
        this.isOrNot = isOrNot;
    }

    private Integer certainty;
    
    public Integer getCertainty() {
        return certainty;
    }

    public void setCertainty(Integer certainty) {
        this.certainty = certainty;
    }
    
    @Override
	public String toString() {
    	String s = Attribute.getAttribute(idAttribute).getKey();
    	
    	if (isOrNot.equals("is"))
    		s += " E' " + value;
    	else 
    		s += " NON E' " + value;
    	
    	if (certainty < 100)
    		s += " CON CERTEZZA " + certainty;
		
    	return s;
	}

}
