package business.representations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImageListTO implements Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private List<ImageTO> list;

    
    
    public List<ImageTO> getList() {
        if (list == null) {
            list = new ArrayList<ImageTO>();
        }
        return list;
    }

    public void setList(List<ImageTO> list) {
        this.list = list;
    }

}

