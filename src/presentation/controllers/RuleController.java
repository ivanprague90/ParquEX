package presentation.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import presentation.Parameter;
import presentation.ParquEX;
import business.representations.RuleTO;

public class RuleController extends MainController {

	@FXML
    private Button btnModifyRule;

    @FXML
    private Label lblName;

    @FXML
    private Label lblIff;

    @FXML
    private Label lblThen;

	private RuleTO rule;

	@Override
	public void onSetScreen(Parameter parameter) {
		rule = (RuleTO) parameter.getValue(0);
		lblName.setText(rule.getName());
		lblIff.setWrapText(true);
		lblIff.setText(rule.getIff().toString());
		lblThen.setWrapText(true);
		lblThen.setText(rule.getThen().toString());
	}

	@FXML
	private void handleButtonModifyRule(ActionEvent event)
			throws IOException {
		app.loadScreen("modifyRule", ParquEX.modifyRuleFXML);
		Parameter parameter = new Parameter();
		parameter.setValue(rule);
		app.setScreen("modifyRule", parameter);
	}
}
