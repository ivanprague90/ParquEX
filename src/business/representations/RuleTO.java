package business.representations;

import business.representations.rules.iff.IffTO;
import business.representations.rules.then.ThenTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RuleTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<IffTO> iff;
   
    public List<IffTO> getIff() {
        if (iff == null) {
            iff = new ArrayList<IffTO>();
        }
        return iff;
    }

    public void setIff(List<IffTO> iff) {
        this.iff = iff;
    }

    private List<ThenTO> then;
   
    public List<ThenTO> getThen() {
        if (then == null) {
            then = new ArrayList<ThenTO>();
        }
        return then;
    }

    public void setThen(List<ThenTO> then) {
        this.then = then;
    }

    private String id;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
