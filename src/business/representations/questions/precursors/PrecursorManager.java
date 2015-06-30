package business.representations.questions.precursors;

public class PrecursorManager extends PrecursorTO {
	
	private static final long serialVersionUID = -5140335015182495284L;
	
	private boolean admitted;
	
	public boolean isAdmitted() {
		return admitted;
	}

	public void setAdmitted(boolean admitted) {
		this.admitted = admitted;
	}


	public PrecursorManager(PrecursorTO prec) {
		this.admitted = false;
		this.setAnswer(prec.getAnswer());
		this.setIdQuestion(prec.getIdQuestion());
		this.setIsOrNot(prec.getIsOrNot());
	}
	
}
