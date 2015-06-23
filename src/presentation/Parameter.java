package presentation;

import java.util.ArrayList;
import java.util.List;

public class Parameter {

    private List<Object> parameters = new ArrayList<Object>();

    public List<Object> getParameters() {
		return parameters;
	}

	public void setValue(Object object) {
    	parameters.add(object);
    }

    public Object getValue(int index) {
        return parameters.get(index);
    }
    
    public int size() {
    	return parameters.size();
    }
    
    public void remove(Object object) {
    	parameters.remove(object);
    }
}
