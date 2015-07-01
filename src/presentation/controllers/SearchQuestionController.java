package presentation.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import business.Question;
import business.representations.QuestionTO;
import business.services.DeleteEntitiesAS;
import presentation.Parameter;
import presentation.ParquEX;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SearchQuestionController extends MainController {
	
	@FXML
    private Button btnAddQuestion;

    @FXML
    private Button btnDeleteQuestion;

    @FXML
    private Button btnViewQuestion;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<QuestionTO> questionsTable;
    
    private ObservableList<QuestionTO> questionData;

	private ObservableList<QuestionTO> filteredData;
	
	private QuestionTO selectedQuestion;

	@SuppressWarnings("unchecked")
	@Override
	public void onSetScreen(Parameter parameter) {	
		
		txtSearch.setText("");
		
		questionsTable.getSelectionModel().select(null);
		
		btnDeleteQuestion.setDisable(true);
		
		btnViewQuestion.setDisable(true);
		
		questionData = FXCollections
				.observableArrayList();
		filteredData = FXCollections
				.observableArrayList();
		
		ArrayList<QuestionTO> questionList = new ArrayList<QuestionTO>();
		
		for (Map.Entry<String, QuestionTO> entry : Question.getQuestions().entrySet()) 
			questionList.add(entry.getValue());
		
		questionData.addAll(questionList);

		filteredData.removeAll(questionData);
		filteredData.addAll(questionData);

		questionData.addListener(new ListChangeListener<QuestionTO>() {
			@Override
			public void onChanged(
					ListChangeListener.Change<? extends QuestionTO> change) {
				updateFilteredData();
			}
		});
		
		TableColumn<QuestionTO, String> columnAttribute = new TableColumn<QuestionTO, String>("Attributo");
		columnAttribute.setCellValueFactory(new PropertyValueFactory<QuestionTO, String>("attribute"));

		TableColumn<QuestionTO, String> columnQuestion = new TableColumn<QuestionTO, String>("Domanda");
		columnQuestion.setCellValueFactory(new PropertyValueFactory<QuestionTO, String>("theQuestion"));

		TableColumn<QuestionTO, String> columnValidAnswers = new TableColumn<QuestionTO, String>("Risposte valide");
		columnValidAnswers.setCellValueFactory(new PropertyValueFactory<QuestionTO, String>("validAnswers"));

		questionsTable.setItems(filteredData);
		questionsTable.getColumns().setAll(columnAttribute, columnQuestion, columnValidAnswers);
		
		questionsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		questionsTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<QuestionTO>() {
			@Override
			public void changed(ObservableValue<? extends QuestionTO> observable,
					QuestionTO oldValue, QuestionTO newValue) {

				btnViewQuestion.setDisable(false);
				btnDeleteQuestion.setDisable(false);
				selectedQuestion = questionsTable.getSelectionModel().getSelectedItem();
			}
		});

		txtSearch.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {

				updateFilteredData();
			}
		});

	}

	private void updateFilteredData() {
		filteredData.clear();
		
		btnViewQuestion.setDisable(true);
		btnDeleteQuestion.setDisable(true);

		for (QuestionTO p : questionData) {
			if (matchesFilter(p)) {
				filteredData.add(p);
			}
		}

		reapplyTableSortOrder();
	}
	
	private boolean matchesFilter(QuestionTO question) {
		try {
			String filterString = txtSearch.getText();

			if (filterString == null || filterString.isEmpty()) {
				// No filter --> Add all.
				return true;
			}

			String lowerCaseFilterString = filterString.toLowerCase();

			if (question.getAttribute().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
				return true;
			} else if (question.getTheQuestion().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("Nessun contenuto trovato nella tabella");
		}

		return false; // Does not match
	}

	private void reapplyTableSortOrder() {
		ArrayList<TableColumn<QuestionTO, ?>> sortOrder = new ArrayList<>(
				questionsTable.getSortOrder());
		questionsTable.getSortOrder().clear();
		questionsTable.getSortOrder().addAll(sortOrder);
	}
    
    @FXML
	private void handleButtonAddQuestion(ActionEvent event)
			throws IOException {
    	app.loadScreen("addQuestion", ParquEX.addQuestionFXML);
		app.setScreen("addQuestion", null);
	}
	
	@FXML
	private void handleButtonDeleteQuestion(ActionEvent event)
			throws IOException {
		Parameter parameter = new Parameter();
		parameter.setValue("question");
		parameter.setValue(selectedQuestion);
		DeleteEntitiesAS deleteEntitiesAS = new DeleteEntitiesAS();
    	
    	if (deleteEntitiesAS.deleteEntities(parameter)) {
			app.setScreen("searchQuestion", null);
		} else {
			//lblNotifyModQuestion
			//.setText("Errore inaspettato o connessione internet assente!");
		}
	}
	
	@FXML
	private void handleButtonViewQuestion(ActionEvent event)
			throws IOException {
		Parameter parameter = new Parameter();
		parameter.setValue(selectedQuestion);
		app.setScreen("question", parameter);
	}


}
