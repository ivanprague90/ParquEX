package presentation;

import business.QuestionService;
import business.services.RetrievesEntitiesAS;
import presentation.controllers.ScreenDispatcher;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ParquEX extends Application {
	private static String mainFXML = "../views/MainView.fxml";
	
	private ScreenDispatcher screensContainer = new ScreenDispatcher();

	@Override
	public void start(Stage primaryStage) throws Exception {
		RetrievesEntitiesAS retrievesEntities = new RetrievesEntitiesAS();
		retrievesEntities.retrievesEntities();
		
		QuestionService.modifyQuestion();
		QuestionService.setPrecursorPreference();

		screensContainer.setFC(this);
		screensContainer.loadScreen("main", ParquEX.mainFXML);
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
