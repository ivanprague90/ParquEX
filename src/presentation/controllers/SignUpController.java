package presentation.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import presentation.Parameter;
import business.representations.UserTO;
import business.services.CreateUserAS;

public class SignUpController implements Initializable, ScreenController {
	protected ScreenDispatcher app;

	@FXML
	private Label lblNotifySignUp;

	@FXML
	private Button btnConfirm;

	@FXML
	private TextField txtUsername;

	@FXML
	private TextField txtEmail;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private PasswordField txtConfirmPwd;

	@FXML
	private TextField txtName;

	@FXML
	private TextField txtSurname;

	@FXML
	private TextField txtTaxCode;

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
		txtUsername.setText("");
		txtEmail.setText("");
		txtPassword.setText("");
		txtConfirmPwd.setText("");
		txtName.setText("");
		txtSurname.setText("");
		txtTaxCode.setText("");
		lblNotifySignUp.setText("");

	}

	@FXML
	private void handleButtonConfirm(ActionEvent event) throws IOException {
		String username = txtUsername.getText();
		String password = txtPassword.getText();
		String confirmPassword = txtConfirmPwd.getText();

		if (!username.isEmpty() && !password.isEmpty()
				&& !confirmPassword.isEmpty()) {
			if (password.equals(confirmPassword)) {
				UserTO user = new UserTO();

				user.setUsername(username);
				user.setPassword(password);
				user.setEmail(txtEmail.getText());
				user.setName(txtName.getText());
				user.setSurname(txtSurname.getText());
				user.setTaxcode(txtTaxCode.getText());

				Parameter parameter = new Parameter();
				parameter.setValue("user");
				parameter.setValue(user);

				if ((boolean) CreateUserAS.createUser(user))
					app.setScreen("enter", null);
				else
					lblNotifySignUp
							.setText("Errore inatteso o connessione internet assente!");
			} else {
				lblNotifySignUp
						.setText("Password and Conferma Password non corrispondono!");
			}
		} else {
			lblNotifySignUp
					.setText("Username, Password e Conferma password richiesti!");
		}

	}
}