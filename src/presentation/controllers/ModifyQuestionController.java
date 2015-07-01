package presentation.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import business.Question;
import business.representations.QuestionTO;
import business.representations.questions.precursors.PrecursorTO;
import business.services.UpdateEntitiesAS;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import presentation.Parameter;

public class ModifyQuestionController extends MainController {

	@FXML
	private Label lblNotifyModQuestion;

	@FXML
	private Button btnConfirmModifyQuestion;

	@FXML
	private TextField txtModifyAttribute;

	@FXML
	private TextArea txtModifyQuestion;

	@FXML
	private TextArea txtModifyWhy;

	@FXML
	private TextField txtModifyAnswer0;

	private Button btnAddValidAnswer = new Button();

	private Button btnRemoveValidAnswer = new Button();

	private Button btnAddPrecursor = new Button();

	private Button btnRemovePrecursor = new Button();

	@FXML
	private VBox vBoxValidAnswers;

	@FXML
	private VBox vBoxPrecursors;

	@FXML
	private HBox hBoxValidAnswer0;

	@FXML
	private HBox hBoxPrecursor0;

	@FXML
	private VBox vBoxPrecursor0;

	private ArrayList<HBox> hBoxAnswerList = new ArrayList<HBox>();

	private ArrayList<TextField> validAnswerList = new ArrayList<TextField>();

	private ArrayList<HBox> hBoxPrecursorList = new ArrayList<HBox>();

	private ArrayList<VBox> vBoxPrecursorList = new ArrayList<VBox>();

	private ArrayList<ChoiceBox<QuestionTO>> choiceAttributePrecursorList = new ArrayList<ChoiceBox<QuestionTO>>();

	private ArrayList<SwitchButton> switchIsOrNotList = new ArrayList<SwitchButton>();

	private ArrayList<ChoiceBox<String>> choiceAnswerPrecursorList = new ArrayList<ChoiceBox<String>>();

	private int numValidAnswer;

	private int numPrecursor;

	private QuestionTO question;

	@Override
	public void onSetScreen(Parameter parameter) {

		numValidAnswer = 1;
		numPrecursor = 1;

		btnAddValidAnswer.getStyleClass().add("ipad-grey");
		btnAddValidAnswer.setText("+");
		btnRemoveValidAnswer.getStyleClass().add("ipad-grey");
		btnRemoveValidAnswer.setText("-");
		
		hBoxAnswerList.add(hBoxValidAnswer0);
		validAnswerList.add(txtModifyAnswer0);

		btnAddValidAnswer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				addValidAnswer();

			}
		});
		btnRemoveValidAnswer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				HBox hBoxValidAnswer = hBoxAnswerList.get(hBoxAnswerList.size() - 1);
				hBoxValidAnswer.getChildren().removeAll(btnAddValidAnswer,
						btnRemoveValidAnswer);
				HBox hBoxValidAnswer1 = hBoxAnswerList.get(hBoxAnswerList
						.size() - 2);
				hBoxValidAnswer1.getChildren().addAll(btnAddValidAnswer,
						btnRemoveValidAnswer);
				vBoxValidAnswers.getChildren().remove(hBoxValidAnswer);
				hBoxAnswerList.remove(hBoxAnswerList.size() - 1);
				validAnswerList.remove(validAnswerList.size() - 1);
				if (hBoxAnswerList.size() == 1) {
					hBoxValidAnswer1.getChildren().remove(btnRemoveValidAnswer);
					HBox.setMargin(btnAddValidAnswer, new Insets(0, 10, 0, 0));
				}
				numValidAnswer--;
			}
		});

		btnAddPrecursor.getStyleClass().add("ipad-grey");
		btnAddPrecursor.setText("+");
		btnRemovePrecursor.getStyleClass().add("ipad-grey");
		btnRemovePrecursor.setText("-");

		addTo(vBoxPrecursor0);
		hBoxPrecursorList.add(hBoxPrecursor0);
		vBoxPrecursorList.add(vBoxPrecursor0);

		btnAddPrecursor.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				addPrecursor();
			}
		});
		btnRemovePrecursor.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				HBox hBoxPrecursor = hBoxPrecursorList.get(hBoxPrecursorList
						.size() - 1);
				hBoxPrecursor.getChildren().removeAll(btnAddPrecursor,
						btnRemovePrecursor);
				HBox hBoxPrecursor1 = hBoxPrecursorList.get(hBoxPrecursorList
						.size() - 2);
				hBoxPrecursor1.getChildren().addAll(btnAddPrecursor,
						btnRemovePrecursor);
				vBoxPrecursors.getChildren().remove(hBoxPrecursor);
				hBoxPrecursorList.remove(hBoxPrecursorList.size() - 1);
				vBoxPrecursorList.remove(vBoxPrecursorList.size() - 1);
				choiceAttributePrecursorList
						.remove(choiceAttributePrecursorList.size() - 1);
				switchIsOrNotList.remove(switchIsOrNotList.size() - 1);
				choiceAnswerPrecursorList.remove(choiceAnswerPrecursorList
						.size() - 1);
				if (hBoxPrecursorList.size() == 1) {
					hBoxPrecursor1.getChildren().remove(btnRemovePrecursor);
					HBox.setMargin(btnAddPrecursor, new Insets(0, 10, 0, 0));
				}
				numPrecursor--;
			}
		});

		hBoxValidAnswer0.getChildren().add(btnAddValidAnswer);
		HBox.setMargin(btnAddValidAnswer, new Insets(0, 10, 0, 0));
		hBoxPrecursor0.getChildren().add(btnAddPrecursor);
		HBox.setMargin(btnAddPrecursor, new Insets(0, 10, 0, 0));

		// Modifica Domanda
		question = (QuestionTO) parameter.getValue(0);
		txtModifyAttribute.setText(question.getAttribute());
		txtModifyQuestion.setText(question.getTheQuestion());
		txtModifyWhy.setText(question.getWhy());
		for (int i = 0; i < question.getValidAnswers().size(); ++i) {
			if (i > 0)
				addValidAnswer();
			validAnswerList.get(i).setText(question.getValidAnswers().get(i));
		}
		for (int i = 0; i < question.getPrecursors().size(); ++i) {
			if (i > 0)
				addPrecursor();

			String id = question.getPrecursors().get(i).getIdQuestion();
			String isOrNot = question.getPrecursors().get(i).getIsOrNot();
			String answer = question.getPrecursors().get(i).getAnswer();
			
			ArrayList<QuestionTO> questionList = new ArrayList<QuestionTO>();		

			for (Map.Entry<String, QuestionTO> entry : Question.getQuestions()
					.entrySet())
				questionList.add(entry.getValue());
			
			int idexQuestion = -1;
			for (int j = 0; j < questionList.size(); ++j) 
				if (questionList.get(j).getId().equals(id))
					idexQuestion = j;
			if (idexQuestion >= 0)
				choiceAttributePrecursorList.get(i).getSelectionModel().select(++idexQuestion);
			
			if (isOrNot.equals("is-not"))
				switchIsOrNotList.get(i).switchOnProperty();
			
			ArrayList<String> answerList = (ArrayList<String>) Question.getQuestion(id).getValidAnswers();
			
			int idexAnswer = -1;
			for (int j = 0; j < answerList.size(); ++j) 
				if (answerList.get(j).equals(answer))
					idexAnswer = j;
			if (idexAnswer >= 0)
				choiceAnswerPrecursorList.get(i).getSelectionModel().select(++idexAnswer);
			
		}

	}

	private void addTo(VBox vBox) {
		SwitchButton switchButton = new SwitchButton();

		ArrayList<QuestionTO> questionList = new ArrayList<QuestionTO>();
		
		questionList.add(null);

		for (Map.Entry<String, QuestionTO> entry : Question.getQuestions()
				.entrySet())
			questionList.add(entry.getValue());
		ObservableList<QuestionTO> data = FXCollections
				.observableList(questionList);
		ChoiceBox<QuestionTO> chbxModifyAttribute = new ChoiceBox<QuestionTO>();
		chbxModifyAttribute.setPrefWidth(150);
		chbxModifyAttribute.setItems(data);

		ChoiceBox<String> chbxModifyAnswer = new ChoiceBox<String>();
		chbxModifyAttribute.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<QuestionTO>() {
					@Override
					public void changed(
							ObservableValue<? extends QuestionTO> observableValue,
							QuestionTO question, QuestionTO question2) {
						ArrayList<String> answerPreList = new ArrayList<String>();
						answerPreList.add(null);
						if (question2 != null)
							answerPreList.addAll((ArrayList<String>) question2
								.getValidAnswers());
						ObservableList<String> data = FXCollections
								.observableList(answerPreList);
						chbxModifyAnswer.setItems(data);
					}
				});
		chbxModifyAnswer.setPrefWidth(150);

		vBox.getChildren().addAll(chbxModifyAttribute, switchButton,
				chbxModifyAnswer);
		VBox.setMargin(switchButton, new Insets(5, 0, 0, 0));
		VBox.setMargin(chbxModifyAnswer, new Insets(5, 0, 0, 0));
		choiceAttributePrecursorList.add(chbxModifyAttribute);
		switchIsOrNotList.add(switchButton);
		choiceAnswerPrecursorList.add(chbxModifyAnswer);
	}

	private void addValidAnswer() {
		HBox hBoxValidAnswer1 = new HBox();
		hBoxValidAnswer0.getChildren().remove(btnAddValidAnswer);
		TextField txtModifyAnswer1 = new TextField();
		txtModifyAnswer1.setPrefWidth(150);
		validAnswerList.add(txtModifyAnswer1);
		hBoxValidAnswer1.getChildren().addAll(txtModifyAnswer1,
				btnAddValidAnswer, btnRemoveValidAnswer);
		HBox.setMargin(txtModifyAnswer1, new Insets(10, 10, 0, 210));
		HBox.setMargin(btnAddValidAnswer, new Insets(10, 10, 0, 0));
		HBox.setMargin(btnRemoveValidAnswer, new Insets(10, 10, 0, 0));
		hBoxAnswerList.add(hBoxValidAnswer1);
		vBoxValidAnswers.getChildren().add(hBoxValidAnswer1);
		numValidAnswer++;
	}

	private void addPrecursor() {
		HBox hBoxPrecursor1 = new HBox();
		hBoxPrecursor0.getChildren().remove(btnAddPrecursor);
		VBox vBoxPrecursor1 = new VBox();
		vBoxPrecursor1.setPrefWidth(150);
		vBoxPrecursor1.setAlignment(Pos.CENTER);
		addTo(vBoxPrecursor1);
		vBoxPrecursorList.add(vBoxPrecursor1);
		hBoxPrecursor1.getChildren().addAll(vBoxPrecursor1, btnAddPrecursor,
				btnRemovePrecursor);
		HBox.setMargin(vBoxPrecursor1, new Insets(10, 10, 0, 210));
		HBox.setMargin(btnAddPrecursor, new Insets(10, 10, 0, 0));
		HBox.setMargin(btnRemovePrecursor, new Insets(10, 10, 0, 0));
		hBoxPrecursorList.add(hBoxPrecursor1);
		vBoxPrecursors.getChildren().add(hBoxPrecursor1);
		numPrecursor++;
	}

	@FXML
	private void handleButtonConfirm(ActionEvent event) throws IOException {
		QuestionTO questionTO = new QuestionTO();
		questionTO.setId(question.getId());
		questionTO.setAttribute(txtModifyAttribute.getText());
		questionTO.setTheQuestion(txtModifyQuestion.getText());
		questionTO.setWhy(txtModifyWhy.getText());
		ArrayList<String> valAnswList = new ArrayList<String>();
		for (int i = 0; i < numValidAnswer; ++i) 
			if (!validAnswerList.get(i).getText().isEmpty())
				valAnswList.add(validAnswerList.get(i).getText());
		questionTO.setValidAnswers(valAnswList);
		ArrayList<PrecursorTO> precList = new ArrayList<PrecursorTO>();
		for (int i = 0; i < numPrecursor; ++i) {
			PrecursorTO prec = new PrecursorTO();
			if (choiceAttributePrecursorList.get(i).getSelectionModel().getSelectedItem() == null)
				continue;
			prec.setIdQuestion(choiceAttributePrecursorList.get(i).getSelectionModel().getSelectedItem().getId());
			if (switchIsOrNotList.get(i).switchOnProperty().getValue())
				prec.setIsOrNot("is");
			else
				prec.setIsOrNot("is-not");
			if (choiceAnswerPrecursorList.get(i).getSelectionModel().getSelectedItem() == null)
				continue;
			prec.setAnswer(choiceAnswerPrecursorList.get(i).getSelectionModel().getSelectedItem());
			precList.add(prec);
		}
		if (!precList.isEmpty())
			questionTO.setPrecursors(precList);

		Parameter parameter = new Parameter();
		parameter.setValue("question");
		parameter.setValue(questionTO);
		UpdateEntitiesAS updateEntitiesAS = new UpdateEntitiesAS();
		
		Parameter parameter2 = new Parameter();
		parameter2.setValue(questionTO);
    	
    	if (updateEntitiesAS.updateEntities(parameter)) {
			app.setScreen("question", parameter2);
		} else {
			lblNotifyModQuestion.setWrapText(true);
			lblNotifyModQuestion
			.setText("Errore inaspettato o connessione internet assente");
		}
	}

}
