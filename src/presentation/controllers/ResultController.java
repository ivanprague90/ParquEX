package presentation.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import business.QuestionManager;
import business.QuestionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import net.sf.clipsrules.jni.Environment;
import net.sf.clipsrules.jni.FactAddressValue;
import net.sf.clipsrules.jni.LexemeValue;
import net.sf.clipsrules.jni.MultifieldValue;
import net.sf.clipsrules.jni.NumberValue;
import presentation.Parameter;

public class ResultController implements Initializable, ScreenController {
	protected ScreenDispatcher app;
	private Environment clips;
	private Map<String, QuestionManager> questionAnswered;

	@FXML
	private VBox vbxResult;

	@FXML
	private Button btnRetract;
	
	@FXML
    private Label lblEssence;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblProperty;

	// Per inizializzazioni di schermata con uso di page usare overriding
	// del metodo setScreenPane altrimenti overriding del metodo initialize
	@Override
	public void setScreenPane(ScreenDispatcher app) {
		this.app = app;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		questionAnswered = new HashMap<String, QuestionManager>();

		clips = new Environment();
		clips.load("src/integration/clips/essence.clp");
	}

	@Override
	public void onSetScreen(Parameter parameter) {
		clips.reset();
		
		vbxResult.getChildren().clear();
		
		questionAnswered = (HashMap<String, QuestionManager>) parameter
				.getValue(0);
		
		this.assertInClips();

		String evalStr = "(ESSENCES::get-essence-list)";

		MultifieldValue pv = (MultifieldValue) clips.eval(evalStr);

		for (int i = 0; i < pv.size(); i++) {
			HBox row = new HBox();
			Label nameEssence = new Label();

			final ProgressBar pbs = new ProgressBar(100);
			final ProgressIndicator pins = new ProgressIndicator(100);

			Label cert = new Label();
			FactAddressValue fv = (FactAddressValue) pv.get(i);

			int certainty = 0;

			try {
				certainty = ((NumberValue) fv.getFactSlot("certainty"))
						.intValue();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Number n;
			n = certainty;

			pbs.setProgress(n.doubleValue() / 100);
			pins.setProgress(n.doubleValue() / 100);

			String essenceName = "ciao";
			try {
				essenceName = ((LexemeValue) fv.getFactSlot("value"))
						.lexemeValue();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			nameEssence.setText(essenceName.toUpperCase());

			nameEssence.setPrefWidth(150);
			pbs.setPrefWidth(100);
			row.setSpacing(10);
			row.getChildren().clear();
			row.getChildren().addAll(nameEssence, pbs, pins);
			vbxResult.getChildren().add(row);
		}

	}

	private void assertInClips() {

		for (Map.Entry<String, QuestionManager> qa : questionAnswered
				.entrySet()) {
			QuestionManager qm = qa.getValue();

			clips.assertString("(attribute (name "
					+ qm.getAttribute().toLowerCase() + ") (value "
					+ qm.getAnswer().toLowerCase().replaceAll("\\s", "") + "))");
		}

		clips.run();

	}

	@FXML
	private void handleButtonRetract(ActionEvent event) throws IOException {

		Parameter parameter = new Parameter();
		parameter.setValue(questionAnswered);
		app.setScreen("retract", parameter);

	}
}
