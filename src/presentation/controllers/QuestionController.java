package presentation.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import presentation.Parameter;
import presentation.ParquEX;
import business.representations.QuestionTO;

public class QuestionController extends MainController {

	@FXML
	private Label lblAttribute;

	@FXML
	private Label lblTheQuestion;

	@FXML
	private Label lblWhy;

	@FXML
	private Label lblValidAnswers;

	@FXML
	private Label lblPrecursors;

	private QuestionTO question;

	@Override
	public void onSetScreen(Parameter parameter) {
		question = (QuestionTO) parameter.getValue(0);
		lblAttribute.setText(question.getAttribute());
		lblTheQuestion.setText(question.getTheQuestion());
		lblTheQuestion.setWrapText(true);
		lblWhy.setText(question.getWhy());
		lblWhy.setWrapText(true);
		lblValidAnswers.setText(question.getValidAnswers().toString());
		lblPrecursors.setText(question.getPrecursors().toString());
	}

	@FXML
	private void handleButtonModifyQuestion(ActionEvent event)
			throws IOException {
		app.loadScreen("modifyQuestion", ParquEX.modifyQuestionFXML);
		Parameter parameter = new Parameter();
		parameter.setValue(question);
		app.setScreen("modifyQuestion", parameter);
	}
}
