package presentation;

import business.services.RetrievesEntitiesAS;
import presentation.controllers.ScreenDispatcher;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ParquEX extends Application {
	private static String mainFXML = "../views/MainView.fxml";
	private static String searchQuestionFXML = "../views/SearchQuestionView.fxml";
	private static String questionFXML = "../views/QuestionView.fxml";
	
	private ScreenDispatcher screensContainer = new ScreenDispatcher();

	@Override
	public void start(Stage primaryStage) throws Exception {
		RetrievesEntitiesAS retrievesEntities = new RetrievesEntitiesAS();
		retrievesEntities.retrievesEntities();

		screensContainer.setFC(this);
		screensContainer.loadScreen("main", ParquEX.mainFXML);
		screensContainer.loadScreen("searchQuestion", ParquEX.searchQuestionFXML);
		screensContainer.loadScreen("question", ParquEX.questionFXML);
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
