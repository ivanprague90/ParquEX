package presentation.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import presentation.Parameter;
import business.QuestionManager;
import business.QuestionService;
import business.representations.QuestionTO;
import business.representations.questions.precursors.PrecursorTO;

public class MainController implements Initializable, ScreenController {
	protected ScreenDispatcher app;
	Map<String, QuestionManager> entryQuestion;
	Map<String, QuestionManager> allQuestion;
	private ToggleGroup g;
	QuestionManager q;
	RadioButton rbAnswer;
	Random r;

	@FXML
	private Label lblQuestion;

	@FXML
	private VBox vbxAnswer;

	@FXML
	private Button btnAnswer;

	// Per inizializzazioni di schermata con uso di page usare overriding
	// del metodo setScreenPane altrimenti overriding del metodo initialize
	@Override
	public void setScreenPane(ScreenDispatcher app) {
		this.app = app;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		entryQuestion = QuestionService.setEntrySet();
		vbxAnswer.setPadding(new Insets(50, 20, 0, 0));
		vbxAnswer.setSpacing(50);
	}

	@Override
	public void onSetScreen(Parameter parameter) {
		/*
		 * CreateClipsRulesAS createClipsRules = new CreateClipsRulesAS();
		 * ArrayList<String> clipsRules = (ArrayList<String>)
		 * createClipsRules.createClipsRules();
		 * lblRule.setText(clipsRules.get(0));
		 */

		r = new Random();

		List<String> keysAsArray = new ArrayList<String>(entryQuestion.keySet());

		if (!entryQuestion.isEmpty())
			q = entryQuestion
					.get(keysAsArray.get(r.nextInt(keysAsArray.size())));

		lblQuestion.setText(q.getTheQuestion());
		entryQuestion.remove(q.getId());
		lblQuestion.setWrapText(true);

		Iterator<String> it = q.getValidAnswers().iterator();

		g = new ToggleGroup();

		vbxAnswer.getChildren().clear();

		while (it.hasNext()) {
			String answ = it.next();
			rbAnswer = new RadioButton();
			rbAnswer.setText(answ);
			rbAnswer.setToggleGroup(g);
			vbxAnswer.getChildren().add(rbAnswer);
		}
	}

	@FXML
	private void handleButtonAnswer(ActionEvent event) throws IOException {

		String answer = new String();
		answer = ((RadioButton) g.getSelectedToggle()).getText();

		System.out.println(q.getAttribute() + " is " + answer);

		if (!q.getQuestionInterested().isEmpty()) {
			for (String element : q.getQuestionInterested()) {
				QuestionManager qm = new QuestionManager();
				qm = QuestionService.getQuestion(element);
				for (PrecursorTO elementList : qm.getPrecursors()) {
					if (elementList.getIdQuestion().equals(q.getId())) {
						if ((elementList.getAnswer().equals(answer) && elementList
								.getIsOrNot().equals("is"))
								|| (!elementList.getAnswer().equals(answer) && elementList
										.getIsOrNot().equals("is not")))

							entryQuestion.put(element,
									QuestionService.getQuestion(element));
					}
				}
			}
		}
		if (!entryQuestion.isEmpty())
			this.onSetScreen(new Parameter());
		else {
		}
	}

}
