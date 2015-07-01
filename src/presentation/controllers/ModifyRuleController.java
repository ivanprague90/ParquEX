package presentation.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import business.Attribute;
import business.Question;
import business.representations.AttributeTO;
import business.representations.QuestionTO;
import business.representations.RuleTO;
import business.representations.rules.iff.IffTO;
import business.representations.rules.then.ThenTO;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import presentation.Parameter;

public class ModifyRuleController extends MainController {

	@FXML
	private Label lblNotifyModRule;

	@FXML
	private Button btnConfirmModifyRule;

	@FXML
	private TextField txtModifyName;

	private Button btnAddCondition = new Button();

	private Button btnRemoveCondition = new Button();

	private Button btnAddAction = new Button();

	private Button btnRemoveAction = new Button();

	@FXML
	private VBox vBoxConditions;

	@FXML
	private VBox vBoxActions;

	@FXML
	private HBox hBoxCondition0;
	
	@FXML
	private VBox vBoxCondition0;

	@FXML
	private HBox hBoxAction0;

	@FXML
	private VBox vBoxAction0;
	
	private ArrayList<HBox> hBoxConditionList = new ArrayList<HBox>();

	private ArrayList<VBox> vBoxConditionList = new ArrayList<VBox>();

	private ArrayList<ChoiceBox<QuestionTO>> choiceQuestionConditionList = new ArrayList<ChoiceBox<QuestionTO>>();

	private ArrayList<SwitchButton> switchConditionIsOrNotList = new ArrayList<SwitchButton>();

	private ArrayList<ChoiceBox<String>> choiceAnswerConditionList = new ArrayList<ChoiceBox<String>>();

	private ArrayList<HBox> hBoxActionList = new ArrayList<HBox>();

	private ArrayList<VBox> vBoxActionList = new ArrayList<VBox>();

	private ArrayList<ChoiceBox<AttributeTO>> choiceAttributeActionList = new ArrayList<ChoiceBox<AttributeTO>>();

	private ArrayList<SwitchButton> switchActionIsOrNotList = new ArrayList<SwitchButton>();

	private ArrayList<ChoiceBox<String>> choiceValueActionList = new ArrayList<ChoiceBox<String>>();
	
	private ArrayList<TextField> choiceCertaintyActionList = new ArrayList<TextField>();

	private int numCondition;

	private int numAction;

	private RuleTO rule;

	@Override
	public void onSetScreen(Parameter parameter) {

		numCondition = 1;
		numAction = 1;

		btnAddCondition.getStyleClass().add("ipad-grey");
		btnAddCondition.setText("+");
		btnRemoveCondition.getStyleClass().add("ipad-grey");
		btnRemoveCondition.setText("-");
		
		addToCondition(vBoxCondition0);
		hBoxConditionList.add(hBoxCondition0);
		vBoxConditionList.add(vBoxCondition0);
		
		btnAddCondition.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				addCondition();
			}
		});
		btnRemoveCondition.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				HBox hBoxCondition = hBoxConditionList.get(hBoxConditionList
						.size() - 1);
				hBoxCondition.getChildren().removeAll(btnAddCondition,
						btnRemoveCondition);
				HBox hBoxCondition1 = hBoxConditionList.get(hBoxConditionList
						.size() - 2);
				hBoxCondition1.getChildren().addAll(btnAddCondition,
						btnRemoveCondition);
				vBoxConditions.getChildren().remove(hBoxCondition);
				hBoxConditionList.remove(hBoxConditionList.size() - 1);
				vBoxConditionList.remove(vBoxConditionList.size() - 1);
				choiceQuestionConditionList
						.remove(choiceQuestionConditionList.size() - 1);
				switchConditionIsOrNotList.remove(switchConditionIsOrNotList.size() - 1);
				choiceAnswerConditionList.remove(choiceAnswerConditionList
						.size() - 1);
				if (hBoxConditionList.size() == 1) {
					hBoxCondition1.getChildren().remove(btnRemoveCondition);
					HBox.setMargin(btnAddCondition, new Insets(0, 10, 0, 0));
				}
				numCondition--;
			}
		});
		
		btnAddAction.getStyleClass().add("ipad-grey");
		btnAddAction.setText("+");
		btnRemoveAction.getStyleClass().add("ipad-grey");
		btnRemoveAction.setText("-");

		addToAction(vBoxAction0);
		hBoxActionList.add(hBoxAction0);
		vBoxActionList.add(vBoxAction0);

		btnAddAction.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				addAction();
			}
		});
		btnRemoveAction.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				HBox hBoxAction = hBoxActionList.get(hBoxActionList
						.size() - 1);
				hBoxAction.getChildren().removeAll(btnAddAction,
						btnRemoveAction);
				HBox hBoxAction1 = hBoxActionList.get(hBoxActionList
						.size() - 2);
				hBoxAction1.getChildren().addAll(btnAddAction,
						btnRemoveAction);
				vBoxActions.getChildren().remove(hBoxAction);
				hBoxActionList.remove(hBoxActionList.size() - 1);
				vBoxActionList.remove(vBoxActionList.size() - 1);
				choiceAttributeActionList
						.remove(choiceAttributeActionList.size() - 1);
				switchActionIsOrNotList.remove(switchActionIsOrNotList.size() - 1);
				choiceValueActionList.remove(choiceValueActionList
						.size() - 1);
				if (hBoxActionList.size() == 1) {
					hBoxAction1.getChildren().remove(btnRemoveAction);
					HBox.setMargin(btnAddAction, new Insets(0, 10, 0, 0));
				}
				numAction--;
			}
		});

		hBoxCondition0.getChildren().add(btnAddCondition);
		HBox.setMargin(btnAddCondition, new Insets(0, 10, 0, 0));
		hBoxAction0.getChildren().add(btnAddAction);
		HBox.setMargin(btnAddAction, new Insets(0, 10, 0, 0));
		
		// Modifica Regola
		
		rule = (RuleTO) parameter.getValue(0);
		txtModifyName.setText(rule.getName());
		for (int i = 0; i < rule.getIff().size(); ++i) {
			if (i > 0)
				addCondition();
			
			String id = rule.getIff().get(i).getIdQuestion();
			String isOrNot = rule.getIff().get(i).getIsOrNot();
			String answer = rule.getIff().get(i).getAnswer();
			
			ArrayList<QuestionTO> questionList = new ArrayList<QuestionTO>();		

			for (Map.Entry<String, QuestionTO> entry : Question.getQuestions()
					.entrySet())
				questionList.add(entry.getValue());
			
			int idexQuestion = -1;
			for (int j = 0; j < questionList.size(); ++j) 
				if (questionList.get(j).getId().equals(id))
					idexQuestion = j;
			if (idexQuestion >= 0)
				choiceQuestionConditionList.get(i).getSelectionModel().select(++idexQuestion);
			
			if (isOrNot.equals("is-not"))
				switchConditionIsOrNotList.get(i).switchOnProperty();
			
			ArrayList<String> answerList = (ArrayList<String>) Question.getQuestion(id).getValidAnswers();
			
			int idexAnswer = -1;
			for (int j = 0; j < answerList.size(); ++j) 
				if (answerList.get(j).equals(answer))
					idexAnswer = j;
			if (idexAnswer >= 0)
				choiceAnswerConditionList.get(i).getSelectionModel().select(++idexAnswer);
		}
		
		for (int i = 0; i < rule.getThen().size(); ++i) {
			if (i > 0)
				addAction();
			
			String attribute = rule.getThen().get(i).getIdAttribute();
			String isOrNot = rule.getThen().get(i).getIsOrNot();
			String value = rule.getThen().get(i).getValue();
			Integer certainty = rule.getThen().get(i).getCertainty();
			
			ArrayList<AttributeTO> attributeList = new ArrayList<AttributeTO>();		

			for (Map.Entry<String, AttributeTO> entry : Attribute.getAttributes()
					.entrySet())
				attributeList.add(entry.getValue());
			
			int idexAttribute = -1;
			for (int j = 0; j < attributeList.size(); ++j) 
				if (attributeList.get(j).getId().equals(attribute))
					idexAttribute = j;
			if (idexAttribute >= 0)
				choiceAttributeActionList.get(i).getSelectionModel().select(++idexAttribute);
			
			if (isOrNot.equals("is-not"))
				switchActionIsOrNotList.get(i).switchOnProperty();
			
			ArrayList<String> valueList = (ArrayList<String>) Attribute.getAttribute(attribute).getValues();
			
			int idexValue = -1;
			for (int j = 0; j < valueList.size(); ++j) 
				if (valueList.get(j).equals(value))
					idexValue = j;
			if (idexValue >= 0)
				choiceValueActionList.get(i).getSelectionModel().select(++idexValue);
			
			choiceCertaintyActionList.get(i).setText(certainty.toString());
		}

	}
	
	private void addToCondition(VBox vBox) {
		SwitchButton switchButton = new SwitchButton();

		ArrayList<QuestionTO> questionList = new ArrayList<QuestionTO>();
		
		questionList.add(null);

		for (Map.Entry<String, QuestionTO> entry : Question.getQuestions()
				.entrySet())
			questionList.add(entry.getValue());
		ObservableList<QuestionTO> data = FXCollections
				.observableList(questionList);
		ChoiceBox<QuestionTO> chbxModifyQuestion = new ChoiceBox<QuestionTO>();
		chbxModifyQuestion.setPrefWidth(150);
		chbxModifyQuestion.setItems(data);

		ChoiceBox<String> chbxModifyAnswer = new ChoiceBox<String>();
		chbxModifyQuestion.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<QuestionTO>() {
					@Override
					public void changed(
							ObservableValue<? extends QuestionTO> observableValue,
							QuestionTO question, QuestionTO question2) {
						ArrayList<String> validAnwList = new ArrayList<String>();
						validAnwList.add(null);
						if (question2 != null)
							validAnwList.addAll((ArrayList<String>) question2
								.getValidAnswers());
						ObservableList<String> data = FXCollections
								.observableList(validAnwList);
						chbxModifyAnswer.setItems(data);
					}
				});
		chbxModifyAnswer.setPrefWidth(150);

		vBox.getChildren().addAll(chbxModifyQuestion, switchButton,
				chbxModifyAnswer);
		VBox.setMargin(switchButton, new Insets(5, 0, 0, 0));
		VBox.setMargin(chbxModifyAnswer, new Insets(5, 0, 0, 0));
		choiceQuestionConditionList.add(chbxModifyQuestion);
		switchConditionIsOrNotList.add(switchButton);
		choiceAnswerConditionList.add(chbxModifyAnswer);
	}

	private void addToAction(VBox vBox) {
		SwitchButton switchButton = new SwitchButton();

		ArrayList<AttributeTO> attributeList = new ArrayList<AttributeTO>();
		
		attributeList.add(null);

		for (Map.Entry<String, AttributeTO> entry : Attribute.getAttributes()
				.entrySet())
			attributeList.add(entry.getValue());
		ObservableList<AttributeTO> data = FXCollections
				.observableList(attributeList);
		ChoiceBox<AttributeTO> chbxModifyAttribute = new ChoiceBox<AttributeTO>();
		chbxModifyAttribute.setPrefWidth(150);
		chbxModifyAttribute.setItems(data);

		ChoiceBox<String> chbxModifyValue = new ChoiceBox<String>();
		chbxModifyAttribute.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<AttributeTO>() {
					@Override
					public void changed(
							ObservableValue<? extends AttributeTO> observableValue,
							AttributeTO attribute, AttributeTO attribute2) {
						ArrayList<String> valueActList = new ArrayList<String>();
						valueActList.add(null);
						if (attribute2 != null)
							valueActList.addAll((ArrayList<String>) attribute2
								.getValues());
						ObservableList<String> data = FXCollections
								.observableList(valueActList);
						chbxModifyValue.setItems(data);
					}
				});
		chbxModifyValue.setPrefWidth(150);
		
		Label lblCertainty = new Label("CERTEZZA");
		lblCertainty.setFont(Font.font(null, FontWeight.NORMAL, 20));
		
		TextField txtModifyCertainty = new TextField();
		txtModifyCertainty.setMaxWidth(60);
		txtModifyCertainty.setText("100");

		vBox.getChildren().addAll(chbxModifyAttribute, switchButton,
				chbxModifyValue, lblCertainty, txtModifyCertainty);
		
		VBox.setMargin(switchButton, new Insets(5, 0, 0, 0));
		VBox.setMargin(chbxModifyValue, new Insets(5, 0, 0, 0));
		choiceAttributeActionList.add(chbxModifyAttribute);
		switchActionIsOrNotList.add(switchButton);
		choiceValueActionList.add(chbxModifyValue);
		choiceCertaintyActionList.add(txtModifyCertainty);
	}

	private void addCondition() {
		HBox hBoxCondition1 = new HBox();
		hBoxCondition0.getChildren().remove(btnAddCondition);
		VBox vBoxCondition1 = new VBox();
		vBoxCondition1.setPrefWidth(150);
		vBoxCondition1.setAlignment(Pos.CENTER);
		addToCondition(vBoxCondition1);
		vBoxActionList.add(vBoxCondition1);
		hBoxCondition1.getChildren().addAll(vBoxCondition1, btnAddCondition,
				btnRemoveCondition);
		HBox.setMargin(vBoxCondition1, new Insets(10, 10, 0, 210));
		HBox.setMargin(btnAddCondition, new Insets(10, 10, 0, 0));
		HBox.setMargin(btnRemoveCondition, new Insets(10, 10, 0, 0));
		hBoxConditionList.add(hBoxCondition1);
		vBoxConditions.getChildren().add(hBoxCondition1);
		numCondition++;
	}

	private void addAction() {
		HBox hBoxAction1 = new HBox();
		hBoxAction0.getChildren().remove(btnAddAction);
		VBox vBoxAction1 = new VBox();
		vBoxAction1.setPrefWidth(150);
		vBoxAction1.setAlignment(Pos.CENTER);
		addToAction(vBoxAction1);
		vBoxActionList.add(vBoxAction1);
		hBoxAction1.getChildren().addAll(vBoxAction1, btnAddAction,
				btnRemoveAction);
		HBox.setMargin(vBoxAction1, new Insets(10, 10, 0, 210));
		HBox.setMargin(btnAddAction, new Insets(10, 10, 0, 0));
		HBox.setMargin(btnRemoveAction, new Insets(10, 10, 0, 0));
		hBoxActionList.add(hBoxAction1);
		vBoxActions.getChildren().add(hBoxAction1);
		numAction++;
	}

	@FXML
	private void handleButtonConfirm(ActionEvent event) throws IOException {
		RuleTO ruleTO = new RuleTO();
		ruleTO.setId(rule.getId());
		ruleTO.setName(txtModifyName.getText());
		
		ArrayList<IffTO> conditionList = new ArrayList<IffTO>();
		for (int i = 0; i < numCondition; ++i) {
			IffTO condition = new IffTO();
			if (choiceQuestionConditionList.get(i).getSelectionModel().getSelectedItem() == null)
				continue;
			condition.setIdQuestion(choiceQuestionConditionList.get(i).getSelectionModel().getSelectedItem().getId());
			if (switchConditionIsOrNotList.get(i).switchOnProperty().getValue())
				condition.setIsOrNot("is");
			else
				condition.setIsOrNot("is-not");
			if (choiceAnswerConditionList.get(i).getSelectionModel().getSelectedItem() == null)
				continue;
			condition.setAnswer(choiceAnswerConditionList.get(i).getSelectionModel().getSelectedItem());
			conditionList.add(condition);
		}
		if (!conditionList.isEmpty())
			ruleTO.setIff(conditionList);
		
		ArrayList<ThenTO> actionList = new ArrayList<ThenTO>();
		for (int i = 0; i < numAction; ++i) {
			ThenTO action = new ThenTO();
			if (choiceAttributeActionList.get(i).getSelectionModel().getSelectedItem() == null)
				continue;
			action.setIdAttribute(choiceAttributeActionList.get(i).getSelectionModel().getSelectedItem().getId());
			if (switchActionIsOrNotList.get(i).switchOnProperty().getValue())
				action.setIsOrNot("is");
			else
				action.setIsOrNot("is-not");
			if (choiceValueActionList.get(i).getSelectionModel().getSelectedItem() == null)
				continue;
			action.setValue(choiceValueActionList.get(i).getSelectionModel().getSelectedItem());
			if (Integer.valueOf(choiceCertaintyActionList.get(i).getText()) > 100)
				action.setCertainty(100);
			else if (Integer.valueOf(choiceCertaintyActionList.get(i).getText()) < 0)
				action.setCertainty(0);
			else if (choiceCertaintyActionList.get(i).getText().matches("[0-9]"))
				action.setCertainty(100);
			else
				action.setCertainty(Integer.valueOf(choiceCertaintyActionList.get(i).getText()));
			
			actionList.add(action);
		}
		if (!actionList.isEmpty())
			ruleTO.setThen(actionList);

		Parameter parameter = new Parameter();
		parameter.setValue("rule");
		parameter.setValue(ruleTO);
		UpdateEntitiesAS updateEntitiesAS = new UpdateEntitiesAS();
		
		Parameter parameter2 = new Parameter();
		parameter2.setValue(ruleTO);
    	
    	if (updateEntitiesAS.updateEntities(parameter)) {
			app.setScreen("rule", parameter2);
		} else {
			lblNotifyModRule.setWrapText(true);
			lblNotifyModRule
			.setText("Errore inaspettato o connessione internet assente");
		}

	}

}
