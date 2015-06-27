package business;

import java.util.ArrayList;
import java.util.List;

import business.representations.QuestionTO;

public class QuestionManager extends QuestionTO {

	private boolean already_asked;

	public boolean isAlready_asked() {
		return already_asked;
	}

	public void setAlready_asked(boolean already_asked) {
		this.already_asked = already_asked;
	}

	private List<String> questionInterested;

	public List<String> getQuestionInterested() {
		return questionInterested;
	}

	public void setQuestionInterested(List<String> questionInterested) {
		this.questionInterested = questionInterested;
	}
	
	public void addQuestionInterested(String questionInterested) {
		this.questionInterested.add(questionInterested);
	}

	public QuestionManager(QuestionTO q) {
		this.setAttribute(q.getAttribute());
		this.setId(q.getId());
		this.setAlready_asked(false);
		this.setPrecursors(q.getPrecursors());
		this.setTheQuestion(q.getTheQuestion());
		this.setValidAnswers(q.getValidAnswers());
		this.setWhy(q.getWhy());
		
		this.questionInterested = new ArrayList<String> ();
	}

	public QuestionManager () {}
}
