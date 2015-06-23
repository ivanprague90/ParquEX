package presentation.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import business.services.CreateClipsRulesAS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import presentation.Parameter;

public class MainController implements Initializable, ScreenController {
	protected ScreenDispatcher app;

	@FXML
	private Button btnEssences;

	@FXML
	private Button btnQuestions;

	@FXML
	private Button btnRules;

	@FXML
	private Label lblRule;

	// Per inizializzazioni di schermata con uso di page usare overriding
	// del metodo setScreenPane altrimenti overriding del metodo initialize
	@Override
	public void setScreenPane(ScreenDispatcher app) {
		this.app = app;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSetScreen(Parameter parameter) {
		CreateClipsRulesAS createClipsRules = new CreateClipsRulesAS();
		ArrayList<String> clipsRules = (ArrayList<String>) createClipsRules.createClipsRules();
		lblRule.setText(clipsRules.get(0));
	}
	
	@FXML
	private void handleButtonEssences(ActionEvent event)
			throws IOException {
		//app.setScreen("signup", null);
	}
	
	@FXML
	private void handleButtonQuestions(ActionEvent event)
			throws IOException {
		//app.setScreen("signup", null);
	}
	
	@FXML
	private void handleButtonRules(ActionEvent event)
			throws IOException {
		//app.setScreen("signup", null);
	}

}
