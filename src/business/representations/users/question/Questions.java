package business.representations.users.question;

import java.io.Serializable;

public class Questions implements Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private String idQuestion;

    
    public String getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(String idQuestion) {
        this.idQuestion = idQuestion;
    }


    private String answer;

    
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
