package presentation;

import business.QuestionService;
import business.services.RetrievesEntitiesAS;
import presentation.controllers.ScreenDispatcher;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ParquEX extends Application {
	private static String mainFXML = "../views/MainView.fxml";
	private static String resultFXML = "../views/ResultView.fxml";
	private static String retractFXML = "../views/RetractView.fxml";
	private static String questionRetractedFXML = "../views/QuestionRetractedView.fxml";
	
	private ScreenDispatcher screensContainer = new ScreenDispatcher();

	@Override
	public void start(Stage primaryStage) throws Exception {
		RetrievesEntitiesAS retrievesEntities = new RetrievesEntitiesAS();
		retrievesEntities.retrievesEntities();
		
		QuestionService.modifyQuestion();
		QuestionService.setPrecursorPreference();

		screensContainer.setFC(this);
		screensContainer.loadScreen("main", ParquEX.mainFXML);
		screensContainer.loadScreen("result", ParquEX.resultFXML);
		screensContainer.loadScreen("retract", ParquEX.retractFXML);
		screensContainer.loadScreen("questionRetracted", ParquEX.questionRetractedFXML);
		screensContainer.setScreen("main", null);

		Scene scene = new Scene(screensContainer);

		//primaryStage.getIcons().add(
				//new Image(getClass().getResourceAsStream(
						//"views/images/icon.png")));
		primaryStage.setTitle("ParquEX");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
