package presentation.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import business.Question;
import business.representations.QuestionTO;
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

public class AddQuestionController extends MainController {
	private boolean inizialize = false;

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
	
	@Override
	public void onSetScreen(Parameter parameter) {
		numValidAnswer = 1;
		numPrecursor = 1;

		if (inizialize) {
			for (int i = 0; i < hBoxAnswerList.size(); ++i)
				if (i > 0)
					vBoxValidAnswers.getChildren().removeAll(
							hBoxAnswerList.get(i));
			if (!hBoxValidAnswer0.getChildren().contains(btnAddValidAnswer)) {
				hBoxValidAnswer0.getChildren().add(btnAddValidAnswer);
				HBox.setMargin(btnAddValidAnswer, new Insets(0, 10, 0, 0));
			}
		}

		btnAddValidAnswer.getStyleClass().add("ipad-grey");
		btnAddValidAnswer.setText("+");
		btnRemoveValidAnswer.getStyleClass().add("ipad-grey");
		btnRemoveValidAnswer.setText("-");
		if (!inizialize) {
			hBoxAnswerList.add(hBoxValidAnswer0);
			validAnswerList.add(txtModifyAnswer0);
		}
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

		if (inizialize) {
			for (int i = 0; i < hBoxPrecursorList.size(); ++i)
				if (i > 0)
					vBoxPrecursors.getChildren().removeAll(
							hBoxPrecursorList.get(i));
			if (!hBoxPrecursor0.getChildren().contains(btnAddPrecursor)) {
				hBoxPrecursor0.getChildren().add(btnAddPrecursor);
				HBox.setMargin(btnAddPrecursor, new Insets(0, 10, 0, 0));
			}
		}
		
		btnAddPrecursor.getStyleClass().add("ipad-grey");
		btnAddPrecursor.setText("+");
		btnRemovePrecursor.getStyleClass().add("ipad-grey");
		btnRemovePrecursor.setText("-");

		if (!inizialize) {
			addTo(vBoxPrecursor0);
			hBoxPrecursorList.add(hBoxPrecursor0);
			vBoxPrecursorList.add(vBoxPrecursor0);
		}
		
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
				choiceAttributePrecursorList.remove(choiceAttributePrecursorList.size()-1);
				switchIsOrNotList.remove(switchIsOrNotList.size()-1);
				choiceAnswerPrecursorList.remove(choiceAnswerPrecursorList.size()-1);
				if (hBoxPrecursorList.size() == 1) {
					hBoxPrecursor1.getChildren().remove(btnRemovePrecursor);
					HBox.setMargin(btnAddPrecursor, new Insets(0, 10, 0, 0));
				}
				numPrecursor--;
			}
		});
		if (!inizialize) {
			hBoxValidAnswer0.getChildren().add(btnAddValidAnswer);
			HBox.setMargin(btnAddValidAnswer, new Insets(0, 10, 0, 0));
			hBoxPrecursor0.getChildren().add(btnAddPrecursor);
			HBox.setMargin(btnAddPrecursor, new Insets(0, 10, 0, 0));
			inizialize = true;
		} else {
			ChoiceBox<QuestionTO> choiceAttribute = choiceAttributePrecursorList.get(0);
			SwitchButton switchButton = switchIsOrNotList.get(0);
			ChoiceBox<String> choiceAnswer = choiceAnswerPrecursorList.get(0);
			vBoxPrecursor0.getChildren().removeAll(choiceAttribute, switchButton, choiceAnswer);
			choiceAttributePrecursorList.remove(0);
			switchIsOrNotList.remove(0);
			choiceAnswerPrecursorList.remove(0);
			addTo(vBoxPrecursor0);
		}

	}

	private void addTo(VBox vBox) {
		SwitchButton switchButton = new SwitchButton();

		ArrayList<QuestionTO> questionList = new ArrayList<QuestionTO>();

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
						ArrayList<String> answerPreList = (ArrayList<String>) question2
								.getValidAnswers();
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
		hBoxPrecursor1.getChildren().addAll(vBoxPrecursor1,
				btnAddPrecursor, btnRemovePrecursor);
		HBox.setMargin(vBoxPrecursor1, new Insets(10, 10, 0, 210));
		HBox.setMargin(btnAddPrecursor, new Insets(10, 10, 0, 0));
		HBox.setMargin(btnRemovePrecursor, new Insets(10, 10, 0, 0));
		hBoxPrecursorList.add(hBoxPrecursor1);
		vBoxPrecursors.getChildren().add(hBoxPrecursor1);
		numPrecursor++;
	}

	@FXML
	private void handleButtonConfirm(ActionEvent event) throws IOException {
		// app.setScreen("signup", null);
	}

}
