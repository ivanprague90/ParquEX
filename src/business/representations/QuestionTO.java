package business.representations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import business.representations.questions.precursors.PrecursorTO;

public class QuestionTO implements Serializable {

	private static final long serialVersionUID = 1L;

    private String attribute;
    
    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    private String theQuestion;
    
    public String getTheQuestion() {
        return theQuestion;
    }

    public void setTheQuestion(String theQuestion) {
        this.theQuestion = theQuestion;
    }


    private List<PrecursorTO> precursors;    
    
    public List<PrecursorTO> getPrecursors() {
        if (precursors == null) {
            precursors = new ArrayList<PrecursorTO>();
        }
        return precursors;
    }

    public void setPrecursors(List<PrecursorTO> precursors) {
        this.precursors = precursors;
    }

    private String id;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String why;
    
    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }

    private List<String> validAnswers;    
    
    public List<String> getValidAnswers() {
        if (validAnswers == null) {
            validAnswers = new ArrayList<String>();
        }
        return validAnswers;
    }

    public void setValidAnswers(List<String> validAnswers) {
        this.validAnswers = validAnswers;
    }
    
    @Override
	public String toString() {
		return "" + attribute;
	}

}
