package presentation.controllers;

import java.util.HashMap;

import presentation.Parameter;
import presentation.ParquEX;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class ScreenDispatcher extends StackPane {
	// Contiene le schermate da visualizzare
	private HashMap<String, Node> screens = new HashMap<>();
	private HashMap<String, ScreenController> controllers = new HashMap<>();
	private String actualScreen;

	private ParquEX FC;

	public ScreenDispatcher() {
		super();
	}

	// Aggiunge la schermata all'HashMap
	public void addScreen(String name, Node screen) {
		screens.put(name, screen);
	}

	// Estrae la schermata dall'HashMap
	public Node getScreen(String name) {
		return screens.get(name);
	}

	// Carica il file fxml associato alla schermata da aggiungere alla
	// collezione e
	// e infine imposta il controller associato
	public boolean loadScreen(String name, String resource) {
		try {
			FXMLLoader myLoader = new FXMLLoader(getClass().getResource(
					resource));
			Parent loadScreen = (Parent) myLoader.load();
			ScreenController myScreenControler = ((ScreenController) myLoader
					.getController());
			controllers.put(name, myScreenControler);
			myScreenControler.setScreenPane(this);
			addScreen(name, loadScreen);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		}
	}

	// Visualizza la schermata
	public boolean setScreen(final String name, Parameter parameter) {
		if (screens.get(name) != null) {
			if (!getChildren().isEmpty()) {
				getChildren().remove(0);
				getChildren().add(0, screens.get(name));
			} else {
				getChildren().add(screens.get(name));
			}
			actualScreen = name;
			controllers.get(name).onSetScreen(parameter);
			return true;
		} else {
			System.out.println("Schermata " + name + " non caricata");
			return false;
		}
	}

	// Rimuove la schermata dalla collezione
	public boolean unloadScreen(String name) {
		if (screens.remove(name) == null) {
			System.out.println("Schermata " + name + " non esiste");
			return false;
		} else {
			return true;
		}
	}
	
	public String getActualScreen() {
		return actualScreen;
	}
	
	public ParquEX getFC() {
		return FC;
	}
	
	public void setFC(ParquEX FC){
        this.FC = FC;
    }
}
