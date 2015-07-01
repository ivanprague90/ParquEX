package presentation.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import business.Essence;
import business.representations.EssenceTO;
import business.representations.essences.property.PropertyTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import presentation.Parameter;
import presentation.ParquEX;

public class MainController implements Initializable, ScreenController {
	protected ScreenDispatcher app;

	@FXML
	private Button btnEssences;

	@FXML
	private Button btnQuestions;

	@FXML
	private Button btnRules;
	
	@FXML
	private Button btnClipsRules;

	@FXML
	private Label lblRule;
	
	@FXML
    private VBox vBoxEssences;

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
		vBoxEssences.getChildren().clear();
		ArrayList<EssenceTO> essenceList = new ArrayList<EssenceTO>();
		
		for (Map.Entry<String, EssenceTO> entry : Essence.getEssences().entrySet()) 
			essenceList.add(entry.getValue());
		
		for (EssenceTO essence : essenceList) {
			Label name = new Label();
			name.setFont(Font.font(null, FontWeight.NORMAL, 18));
			name.setText(essence.getName());
			name.setMinHeight(Label.USE_PREF_SIZE);
			vBoxEssences.getChildren().add(name);
			Label property = new Label();
			String p = "";
			for (PropertyTO prop : essence.getProperty()) 
				p += prop.getKey() + ": " + prop.getValue() + "\n";
			property.setText(p);
			property.setMinHeight(Label.USE_PREF_SIZE);
			vBoxEssences.getChildren().add(property);
			Label description = new Label();
			description.setText(essence.getDescription());
			description.setPrefWidth(540);
			description.setMinHeight(Label.USE_PREF_SIZE);
			description.setWrapText(true);
			vBoxEssences.getChildren().add(description);
		}
	}
	
	@FXML
	private void handleButtonEssences(ActionEvent event)
			throws IOException {
		app.setScreen("main", null);
	}
	
	@FXML
	private void handleButtonQuestions(ActionEvent event)
			throws IOException {
		app.setScreen("searchQuestion", null);
	}
	
	@FXML
	private void handleButtonRules(ActionEvent event)
			throws IOException {
		app.setScreen("searchRule", null);
	}
	
	@FXML
	private void handleButtonClipsRules(ActionEvent event)
			throws IOException {
		app.loadScreen("clipsRules", ParquEX.clipsRulesFXML);
		app.setScreen("clipsRules", null);
	}

}
