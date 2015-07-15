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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import presentation.Parameter;
import business.QuestionManager;
import business.QuestionService;
import business.User;
import business.UserService;

public class EnterController implements Initializable, ScreenController {
	protected ScreenDispatcher app;
	private Map<String, QuestionManager> questionAnswered;

	@FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignUp;

    @FXML
    private Label lblNotify;

	// Per inizializzazioni di schermata con uso di page usare overriding
	// del metodo setScreenPane altrimenti overriding del metodo initialize
	@Override
	public void setScreenPane(ScreenDispatcher app) {
		this.app = app;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onSetScreen(Parameter parameter) {
		txtPassword.setText("");
		
		
	}

	@FXML
	private void handleButtonLogin(ActionEvent event)
			throws IOException {
		String username = txtUsername.getText();
		String password = txtPassword.getText();
		String id = UserService.existUsername(username);
		
		if (!username.isEmpty() && !password.isEmpty()) {
			
			if(id != null && UserService.validUser(id, password)) {
				System.out.println("Welcome " + username + " with PWD: " + password);
				app.getFC().setLoggedUser(User.getUser(id));
				questionAnswered = new HashMap<String,QuestionManager> (QuestionService.createQuestionAnsweredLoggedUser(User.getUser(id)));
				Parameter par = new Parameter();
				par.setValue(questionAnswered);
				
				app.setScreen("main", par);
			} else if (id == null)	
				lblNotify.setText("L'username non esiste. Riprovare o registrarsi!");
			else if (!UserService.validUser(id, password))
				lblNotify.setText("La password non e' corretta. Riprovare!");
			
		} else {
			lblNotify.setText("Riempi tutti i campi!");
		}
	}
	
	@FXML
	private void handleButtonSignup(ActionEvent event)
			throws IOException {
		app.setScreen("signUp", null);
	}
}