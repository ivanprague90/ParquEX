package presentation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import presentation.controllers.ScreenDispatcher;
import business.QuestionService;
import business.representations.UserTO;
import business.services.RetrievesEntitiesAS;

public class ParquEX extends Application {
	private static String mainFXML = "../views/MainView.fxml";
	private static String resultFXML = "../views/ResultView.fxml";
	private static String retractFXML = "../views/RetractView.fxml";
	private static String questionRetractedFXML = "../views/QuestionRetractedView.fxml";
	private static String enterFXML = "../views/EnterView.fxml";
	private static String signUpFXML = "../views/SignUpView.fxml";
	private static String profileFXML = "../views/ProfileView.fxml";
	
	private ScreenDispatcher screensContainer = new ScreenDispatcher();
	
	private UserTO loggedUser;

	@Override
	public void start(Stage primaryStage) throws Exception {
		RetrievesEntitiesAS retrievesEntities = new RetrievesEntitiesAS();
		retrievesEntities.retrievesEntities();
		
		QuestionService.modifyQuestion();
		QuestionService.setPrecursorPreference();
		QuestionService.fillListImage();

		screensContainer.setFC(this);
		screensContainer.loadScreen("main", ParquEX.mainFXML);
		screensContainer.loadScreen("result", ParquEX.resultFXML);
		screensContainer.loadScreen("retract", ParquEX.retractFXML);
		screensContainer.loadScreen("questionRetracted", ParquEX.questionRetractedFXML);
		screensContainer.loadScreen("enter", ParquEX.enterFXML);
		screensContainer.loadScreen("signUp", ParquEX.signUpFXML);
		screensContainer.loadScreen("profile", ParquEX.profileFXML);
		screensContainer.setScreen("enter", null);

		Scene scene = new Scene(screensContainer);

		primaryStage.getIcons().add(
				new Image(getClass().getResourceAsStream(
						"views/image/icon.png")));
		primaryStage.setTitle("ParquEX");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public UserTO getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(UserTO user) {
		loggedUser = user;
	}

	public void userLogout() {
		loggedUser = null;
	}

}
