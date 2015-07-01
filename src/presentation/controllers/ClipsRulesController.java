package presentation.controllers;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import presentation.Parameter;
import business.services.CreateClipsRulesAS;

public class ClipsRulesController extends MainController {
	
	@FXML
    private VBox vBoxClipsRules;
	
	@Override
	public void onSetScreen(Parameter parameter) {
		CreateClipsRulesAS createClipsRules = new CreateClipsRulesAS();
		ArrayList<String> clipsRules = (ArrayList<String>) createClipsRules.createClipsRules();
		for (int i = 0; i < clipsRules.size(); ++i) {
			Label lblClipsRule = new Label();
			
			lblClipsRule.setText(clipsRules.get(i));
			lblClipsRule.setMinHeight(Label.USE_PREF_SIZE);
			
			vBoxClipsRules.getChildren().add(lblClipsRule);
		}
	}

}
