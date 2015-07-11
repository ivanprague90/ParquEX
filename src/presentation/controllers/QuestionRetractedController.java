package presentation.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
import javafx.scene.text.Font;
import presentation.Parameter;
import business.QuestionManager;
import business.QuestionService;

public class QuestionRetractedController implements Initializable,
		ScreenController {
	protected ScreenDispatcher app;
	private Map<String, QuestionManager> questionAnswered;
	private ToggleGroup gRetracted;
	private QuestionManager qRetracted;
	private RadioButton rbAnswerRetracted;
	private String oldAnswer;

	@FXML
	private VBox vbxAnswerRetracted;

	@FXML
	private Label lblQuestionRetracted;

	@FXML
	private Button btnAnswerRetracted;

	// Per inizializzazioni di schermata con uso di page usare overriding
	// del metodo setScreenPane altrimenti overriding del metodo initialize
	@Override
	public void setScreenPane(ScreenDispatcher app) {
		this.app = app;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		questionAnswered = new HashMap<String, QuestionManager>();
		vbxAnswerRetracted.setPadding(new Insets(50, 20, 0, 0));
		vbxAnswerRetracted.setSpacing(50);
		lblQuestionRetracted.setPrefHeight(80);
		lblQuestionRetracted.setFont(new Font("Arial", 20));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onSetScreen(Parameter parameter) {

		questionAnswered = (Map<String, QuestionManager>) parameter.getValue(0);

		qRetracted = (QuestionManager) parameter.getValue(1);
		oldAnswer = new String(qRetracted.getAnswer());

		lblQuestionRetracted.setText(qRetracted.getTheQuestion());
		lblQuestionRetracted.setWrapText(true);

		Iterator<String> it = qRetracted.getValidAnswers().iterator();

		gRetracted = new ToggleGroup();

		vbxAnswerRetracted.getChildren().clear();

		while (it.hasNext()) {
			String answ = it.next();
			rbAnswerRetracted = new RadioButton();
			rbAnswerRetracted.setText(answ);
			rbAnswerRetracted.setToggleGroup(gRetracted);
			vbxAnswerRetracted.getChildren().add(rbAnswerRetracted);
		}
	}

	@FXML
	private void handleButtonAnswerRetracted(ActionEvent event) throws IOException {
		String newAnswer = new String(
				((RadioButton) gRetracted.getSelectedToggle()).getText());
		qRetracted.setAnswer(newAnswer);

		if (oldAnswer.equals(newAnswer)) {
			Parameter parameter = new Parameter();
			parameter.setValue(questionAnswered);
			app.setScreen("result", parameter);
		} else {
			Map<String, QuestionManager> newQuestions = new HashMap<String, QuestionManager>(
					QuestionService.afterRetract(questionAnswered, qRetracted));
			Parameter parameter = new Parameter();
			parameter.setValue(newQuestions);
			app.setScreen("main", parameter);
		}

	}
}