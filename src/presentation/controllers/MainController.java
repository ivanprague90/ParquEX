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
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import javax.swing.ToolTipManager;

import net.sf.clipsrules.jni.Environment;
import presentation.Parameter;
import business.QuestionManager;
import business.QuestionService;

public class MainController implements Initializable, ScreenController {
	protected ScreenDispatcher app;
	private Map<String, QuestionManager> entryQuestion;
	private Map<String, QuestionManager> questionAnswered;
	private ToggleGroup g;
	private QuestionManager q;
	private RadioButton rbAnswer;

	@FXML
	private Label lblWhy;

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
		questionAnswered = new HashMap<String, QuestionManager>();
		entryQuestion = QuestionService.setEntrySet();
		vbxAnswer.setPadding(new Insets(50, 20, 0, 0));
		vbxAnswer.setSpacing(50);
		lblQuestion.setPrefHeight(80);
		lblQuestion.setFont(new Font("Arial", 20));
	}

	@Override
	public void onSetScreen(Parameter parameter) {
		
		if (parameter != null) {
			questionAnswered = new HashMap<String, QuestionManager>(
					(HashMap<String, QuestionManager>) parameter.getValue(0));

			entryQuestion = new HashMap<String, QuestionManager>();

			QuestionService.resetPrecursorAdmitted();
			for (Map.Entry<String, QuestionManager> qa : questionAnswered
					.entrySet()) {
				QuestionService.setPrecursorAdmitted(qa.getValue());
			}

			QuestionService.addAdmittedQuestion(entryQuestion);
		}
		if (entryQuestion.isEmpty()) {
			Parameter parameter2 = new Parameter();
			parameter2.setValue(questionAnswered);
			app.setScreen("result", parameter2);
		} else {

			lblWhy.setVisible(false);

			q = QuestionService.callQuestion(entryQuestion);

			if (q.getWhy() != null) {
				lblWhy.setVisible(true);
			}

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

	}

	@FXML
	private void handle(MouseEvent arg0) {
		final Tooltip tooltip = new Tooltip();

		ToolTipManager.sharedInstance().setDismissDelay(10000);

		tooltip.setText(q.getWhy());
		tooltip.setFont(new Font("Arial", 10));
		lblWhy.setTooltip(tooltip);
	}

	@FXML
	private void handleButtonAnswer(ActionEvent event) throws IOException {

		q.setAnswer(((RadioButton) g.getSelectedToggle()).getText());
		questionAnswered.put(q.getId(), q);

		System.out.println(q.getAttribute() + " is " + q.getAnswer());

		QuestionService.setPrecursorAdmitted(q);

		QuestionService.addAdmittedQuestion(entryQuestion);

		if (!entryQuestion.isEmpty())
			this.onSetScreen(null);
		else {
			Parameter parameter = new Parameter();
			parameter.setValue(questionAnswered);
			app.setScreen("result", parameter);
		}
	}
}