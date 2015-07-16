package presentation.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import presentation.Parameter;
import business.QuestionManager;
import business.QuestionService;
import business.representations.UserTO;

public class ProfileController implements Initializable, ScreenController {
	protected ScreenDispatcher app;

	@FXML
	private Button btnNewSession;
	
	@FXML
    private Button btnLastSession;

	@FXML
	private Label lblUsername;

	@FXML
	private Label lblEmail;

	@FXML
	private Label lblName;

	@FXML
	private Label lblSurname;

	@FXML
	private Label lblTaxCode;

	// Per inizializzazioni di schermata con uso di page usare overriding
	// del metodo setScreenPane altrimenti overriding del metodo initialize
	@Override
	public void setScreenPane(ScreenDispatcher app) {
		this.app = app;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@Override
	public void onSetScreen(Parameter parameter) {
		UserTO user = app.getFC().getLoggedUser();
		lblUsername.setText(user.getUsername());
		lblEmail.setText(user.getEmail());
		lblName.setText(user.getName());
		lblSurname.setText(user.getSurname());
		lblTaxCode.setText(user.getTaxcode());
		
		
		btnLastSession.setDisable(false);
		if (user.getQuestions().isEmpty())
			btnLastSession.setDisable(true);

	}

	@FXML
	private void handleButtonNewSession(ActionEvent event) throws IOException {
		app.setScreen("main", null);

	}
	
	@FXML
	private void handleButtonLastSession(ActionEvent event) throws IOException {
		Parameter parameter = new Parameter();
		Map<String,QuestionManager> questionAnswered = new HashMap<String,QuestionManager> (QuestionService.createQuestionAnsweredLoggedUser(app.getFC().getLoggedUser()));
		parameter.setValue(questionAnswered);
		app.setScreen("result", parameter);

	}
}