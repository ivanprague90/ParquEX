package business.representations.rules.iff;

import java.io.Serializable;

import business.Question;

public class IffTO implements Serializable {
 
    private static final long serialVersionUID = 1L;

    private String idQuestion;
    
    public String getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(String idQuestion) {
        this.idQuestion = idQuestion;
    }

    private String isOrNot;
    
    public String getIsOrNot() {
        return isOrNot;
    }

    public void setIsOrNot(String isOrNot) {
        this.isOrNot = isOrNot;
    }

    private String answer;
    
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    @Override
	public String toString() {
    	String s = Question.getQuestion(idQuestion).getAttribute();
    	
    	if (isOrNot.equals("is"))
    		s += " E' " + answer;
    	else 
    		s += " NON E' " + answer;
		
    	return s;
	}

}
