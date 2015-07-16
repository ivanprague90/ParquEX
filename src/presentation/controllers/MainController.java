package presentation.controllers;

import integration.DAOException;
import integration.ImagesDAO;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import javax.swing.ToolTipManager;

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
	
	private Tooltip tool = new Tooltip();

	@FXML
	private Label lblWhy;
	
	@FXML
	private Label lblWhyT;

	@FXML
	private Label lblQuestion;

	@FXML
	private VBox vbxAnswer;

	@FXML
	private Button btnAnswer;

	@FXML
	private BorderPane borderInner;

	// Per inizializzazioni di schermata con uso di page usare overriding
	// del metodo setScreenPane altrimenti overriding del metodo initialize
	@Override
	public void setScreenPane(ScreenDispatcher app) {
		this.app = app;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		questionAnswered = new HashMap<String, QuestionManager>();
		entryQuestion = QuestionService.setEntrySet();
		vbxAnswer.setPadding(new Insets(50, 20, 0, 0));
		vbxAnswer.setSpacing(50);
		lblQuestion.setPrefHeight(80);
		lblQuestion.setFont(new Font("Arial", 25));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onSetScreen(Parameter parameter) {

		borderInner.setRight(null);

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
				rbAnswer.setFont(new Font("Arial", 15));
				rbAnswer.setText(answ);
				rbAnswer.setToggleGroup(g);
				vbxAnswer.getChildren().add(rbAnswer);
			}
			if (!q.getImage().isEmpty()) {
				Label l = new Label(
						"\n\n\n\n\n\n\nClicca sulle varie risposte per visualizzare un immagine di aiuto");
				l.setFont(new Font("Arial", 15));
				borderInner.setRight(l);
			}

			// Listener per le immagini sulle risposte
			g.selectedToggleProperty().addListener(
					new ChangeListener<Toggle>() {
						@Override
						public void changed(
								ObservableValue<? extends Toggle> ov, Toggle t,
								Toggle t1) {

							RadioButton chk = (RadioButton) t1.getToggleGroup()
									.getSelectedToggle();
							String ss = chk.getText();

							ImageView im = null;

							if (!q.getImage().isEmpty()) {

								// ImagesDAO ima = new ImagesDAO();
								boolean b = false;
								for (String s : q.getImage()) {
									im = new ImageView();
									if (s.toLowerCase().contains(
											ss.toLowerCase())) {

										im.setImage(new Image(getClass()
												.getResourceAsStream(
														"../views/image/" + s)));
										b = true;
										borderInner.setRight(im);
									}

								}
								if (!b)
									borderInner.setRight(im);
							}
						}
					});
		}

	}

	@FXML
	private void handle(MouseEvent arg0) {
		 tool.setText(q.getWhy());

		 //tool.setFont(new Font("Arial", 8));
	
		lblWhyT.setTooltip(tool);
	}

	@FXML
	private void handleButtonAnswer(ActionEvent event) throws IOException,
			DAOException {

		q.setAnswer(((RadioButton) g.getSelectedToggle()).getText());
		questionAnswered.put(q.getId(), q);

		System.out.println(q.getAttribute() + " is " + q.getAnswer());

		QuestionService.setPrecursorAdmitted(q);

		QuestionService.addAdmittedQuestion(entryQuestion);

		if (!entryQuestion.isEmpty())
			this.onSetScreen(null);
		else {
			QuestionService.saveQuestionIntoUser(app.getFC().getLoggedUser(),
					questionAnswered);
			Parameter parameter = new Parameter();
			parameter.setValue(questionAnswered);
			app.setScreen("result", parameter);
		}
	}
}