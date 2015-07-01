package presentation;

import business.services.RetrievesEntitiesAS;
import presentation.controllers.ScreenDispatcher;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ParquEX extends Application {
	public static String mainFXML = "../views/MainView.fxml";
	public static String searchQuestionFXML = "../views/SearchQuestionView.fxml";
	public static String questionFXML = "../views/QuestionView.fxml";
	public static String modifyQuestionFXML = "../views/ModifyQuestionView.fxml";
	public static String addQuestionFXML = "../views/AddQuestionView.fxml";
	public static String searchRuleFXML = "../views/SearchRuleView.fxml";
	public static String ruleFXML = "../views/RuleView.fxml";
	public static String modifyRuleFXML = "../views/ModifyRuleView.fxml";
	public static String addRuleFXML = "../views/AddRuleView.fxml";
	public static String clipsRulesFXML = "../views/ClipsRulesView.fxml";
	
	
	private ScreenDispatcher screensContainer = new ScreenDispatcher();

	@Override
	public void start(Stage primaryStage) throws Exception {
		RetrievesEntitiesAS retrievesEntities = new RetrievesEntitiesAS();
		retrievesEntities.retrievesEntities();

		screensContainer.setFC(this);
		screensContainer.loadScreen("main", ParquEX.mainFXML);
		screensContainer.loadScreen("searchQuestion", ParquEX.searchQuestionFXML);
		screensContainer.loadScreen("question", ParquEX.questionFXML);
		screensContainer.loadScreen("searchRule", ParquEX.searchRuleFXML);
		screensContainer.loadScreen("rule", ParquEX.ruleFXML);
		screensContainer.setScreen("main", null);

		Scene scene = new Scene(screensContainer);

		primaryStage.getIcons().add(
				new Image(getClass().getResourceAsStream(
						"images/icon.png")));
		primaryStage.setTitle("ParquEX");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
