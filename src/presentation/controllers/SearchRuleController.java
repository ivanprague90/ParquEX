package presentation.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import business.Rule;
import business.representations.RuleTO;
import business.services.DeleteEntitiesAS;
import presentation.Parameter;
import presentation.ParquEX;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SearchRuleController extends MainController {
	
	@FXML
    private Button btnAddRule;

    @FXML
    private Button btnDeleteRule;

    @FXML
    private Button btnViewRule;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<RuleTO> rulesTable;
    
    private ObservableList<RuleTO> ruleData;

	private ObservableList<RuleTO> filteredData;
	
	private RuleTO selectedRule;

	@SuppressWarnings("unchecked")
	@Override
	public void onSetScreen(Parameter parameter) {	
		
		txtSearch.setText("");
		
		rulesTable.getSelectionModel().select(null);
		
		btnDeleteRule.setDisable(true);
		
		btnViewRule.setDisable(true);
		
		ruleData = FXCollections
				.observableArrayList();
		filteredData = FXCollections
				.observableArrayList();
		
		ArrayList<RuleTO> ruleList = new ArrayList<RuleTO>();
		
		for (Map.Entry<String, RuleTO> entry : Rule.getRules().entrySet()) 
			ruleList.add(entry.getValue());
		
		ruleData.addAll(ruleList);

		filteredData.removeAll(ruleData);
		filteredData.addAll(ruleData);

		ruleData.addListener(new ListChangeListener<RuleTO>() {
			@Override
			public void onChanged(
					ListChangeListener.Change<? extends RuleTO> change) {
				updateFilteredData();
			}
		});
		
		TableColumn<RuleTO, String> columnName = new TableColumn<RuleTO, String>("Nome regola");
		columnName.setCellValueFactory(new PropertyValueFactory<RuleTO, String>("name"));

		rulesTable.setItems(filteredData);
		rulesTable.getColumns().setAll(columnName);
		
		rulesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		rulesTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<RuleTO>() {
			@Override
			public void changed(ObservableValue<? extends RuleTO> observable,
					RuleTO oldValue, RuleTO newValue) {

				btnViewRule.setDisable(false);
				btnDeleteRule.setDisable(false);
				selectedRule = rulesTable.getSelectionModel().getSelectedItem();
			}
		});

		txtSearch.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {

				updateFilteredData();
			}
		});

	}

	private void updateFilteredData() {
		filteredData.clear();
		
		btnViewRule.setDisable(true);
		btnDeleteRule.setDisable(true);

		for (RuleTO p : ruleData) {
			if (matchesFilter(p)) {
				filteredData.add(p);
			}
		}

		reapplyTableSortOrder();
	}
	
	private boolean matchesFilter(RuleTO rule) {
		try {
			String filterString = txtSearch.getText();

			if (filterString == null || filterString.isEmpty()) {
				// No filter --> Add all.
				return true;
			}

			String lowerCaseFilterString = filterString.toLowerCase();

			if (rule.getName().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("Nessun contenuto trovato nella tabella");
		}

		return false; // Does not match
	}

	private void reapplyTableSortOrder() {
		ArrayList<TableColumn<RuleTO, ?>> sortOrder = new ArrayList<>(
				rulesTable.getSortOrder());
		rulesTable.getSortOrder().clear();
		rulesTable.getSortOrder().addAll(sortOrder);
	}
    
    @FXML
	private void handleButtonAddRule(ActionEvent event)
			throws IOException {
    	app.loadScreen("addRule", ParquEX.addRuleFXML);
		app.setScreen("addRule", null);
	}
	
	@FXML
	private void handleButtonDeleteRule(ActionEvent event)
			throws IOException {
		Parameter parameter = new Parameter();
		parameter.setValue("rule");
		parameter.setValue(selectedRule);
		DeleteEntitiesAS deleteEntitiesAS = new DeleteEntitiesAS();
    	
    	if (deleteEntitiesAS.deleteEntities(parameter)) {
			app.setScreen("searchRule", null);
		} else {
			//lblNotifyModQuestion
			//.setText("Errore inaspettato o connessione internet assente!");
		}
	}
	
	@FXML
	private void handleButtonViewRule(ActionEvent event)
			throws IOException {
		Parameter parameter = new Parameter();
		parameter.setValue(selectedRule);
		app.setScreen("rule", parameter);
	}


}
