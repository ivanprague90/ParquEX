package presentation.controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;

public class SwitchButton extends Label {
	private SimpleBooleanProperty switchedOn = new SimpleBooleanProperty(false);

	public SwitchButton() {
		Button switchBtn = new Button();
		switchBtn.setPrefWidth(40);
		switchBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				switchedOn.set(!switchedOn.get());
			}
		});

		setGraphic(switchBtn);

		switchedOn.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> ov,
					Boolean t, Boolean t1) {
				if (t1) {
					setText("  E' ");
					setStyle("-fx-background-color: green;-fx-text-fill:white;-fx-font-weight: bold;");
					setContentDisplay(ContentDisplay.RIGHT);
				} else {
					setText(" NON E'  ");
					setStyle("-fx-background-color: blue;-fx-text-fill:white;-fx-font-weight: bold;");
					setContentDisplay(ContentDisplay.LEFT);
				}
			}
		});

		switchedOn.set(true);
	}

	public SimpleBooleanProperty switchOnProperty() {
		return switchedOn;
	}
}
