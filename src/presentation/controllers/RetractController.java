package presentation.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import presentation.Parameter;
import business.QuestionManager;

public class RetractController implements Initializable, ScreenController {
	protected ScreenDispatcher app;
	private Map<String, QuestionManager> questionAnswered;
	private QuestionManager selectedQuestion;

	private ObservableList<QuestionManager> userData;

	@FXML
	private Button btnRetractQuestion;

	@FXML
	private TableView<QuestionManager> tbvAllQuestion;

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

	@SuppressWarnings("unchecked")
	@Override
	public void onSetScreen(Parameter parameter) {
		questionAnswered = new HashMap<String, QuestionManager>();
		selectedQuestion = new QuestionManager();
		selectedQuestion = null;
		tbvAllQuestion.getSelectionModel().clearSelection();
		
		btnRetractQuestion.setDisable(true);


		userData = FXCollections
				.observableArrayList();
		
		questionAnswered = (Map<String, QuestionManager>) parameter.getValue(0);

		List<QuestionManager> list= new ArrayList<QuestionManager> ();
		
		for (Map.Entry<String, QuestionManager> every : questionAnswered
				.entrySet()) {
			list.add(every.getValue());
		}
		
		userData.addAll(list);

		TableColumn<QuestionManager, String> columnQuestion = new TableColumn<QuestionManager, String>(
				"Question");
		columnQuestion
				.setCellValueFactory(new PropertyValueFactory<QuestionManager, String>(
						"theQuestion"));

		TableColumn<QuestionManager, String> columnAnswer = new TableColumn<QuestionManager, String>(
				"Answer");
		columnAnswer
				.setCellValueFactory(new PropertyValueFactory<QuestionManager, String>(
						"answer"));
		
		tbvAllQuestion.setItems(userData);
		
		tbvAllQuestion.getColumns().clear();
		tbvAllQuestion.getColumns().addAll(columnQuestion, columnAnswer);

		tbvAllQuestion
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		tbvAllQuestion.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<QuestionManager>() {
			@Override
			public void changed(ObservableValue<? extends QuestionManager> observable,
					QuestionManager oldValue, QuestionManager newValue) {

				selectedQuestion = tbvAllQuestion.getSelectionModel().getSelectedItem();
				btnRetractQuestion.setDisable(false);
				System.out.println(selectedQuestion.getTheQuestion());
			}
		});
	}
	
	@FXML
	private void handleButtonRetractQuestion(ActionEvent event) throws IOException {
		
		System.out.println("ciao");
		Parameter parameter = new Parameter();
		parameter.setValue(questionAnswered);
		parameter.setValue(selectedQuestion);
		app.setScreen("questionRetracted", parameter);
		System.out.println("hello");
		

	}
}
