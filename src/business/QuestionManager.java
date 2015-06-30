package business;

import java.util.ArrayList;
import java.util.List;

import business.representations.QuestionTO;
import business.representations.questions.precursors.PrecursorManager;
import business.representations.questions.precursors.PrecursorTO;

public class QuestionManager extends QuestionTO {

	// FIELDS
	private static final long serialVersionUID = -4538624028876677776L;

	private boolean already_added;
	
	private List<String> questionInterested;

	private List<PrecursorManager> precursorMg;
	
	private String answer;


	// CONSTRUCTORS
	public QuestionManager() {
	}
	
	public QuestionManager(QuestionTO q) {
		this.setAttribute(q.getAttribute());
		this.setId(q.getId());
		this.setAlready_added(false);
		if (precursorMg == null) {
			precursorMg = new ArrayList<PrecursorManager>();
		}
		for (PrecursorTO prec : q.getPrecursors()) {
			this.precursorMg.add(new PrecursorManager(prec));
		}
		this.setTheQuestion(q.getTheQuestion());
		this.setValidAnswers(q.getValidAnswers());
		this.setWhy(q.getWhy());

		this.questionInterested = new ArrayList<String>();
		this.answer = new String();
	}	

	
	// GETTERS AND SETTERS
	public boolean isAlready_added() {
		return already_added;
	}

	public void setAlready_added(boolean already_added) {
		this.already_added = already_added;
	}

	public List<String> getQuestionInterested() {
		return questionInterested;
	}

	public void setQuestionInterested(List<String> questionInterested) {
		this.questionInterested = questionInterested;
	}

	public void addQuestionInterested(String questionInterested) {
		this.questionInterested.add(questionInterested);
	}


	public List<PrecursorManager> getPrecursorMg() {

		return precursorMg;
	}

	public void setPrecursorMg(List<PrecursorManager> precursor) {
		this.precursorMg = precursor;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
