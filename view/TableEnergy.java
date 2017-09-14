package view;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;

import controller.*;

public class TableEnergy{
 	/*--------------------------------------------------------------------------*/
   /* Static Helper Class                                                      */
   /*--------------------------------------------------------------------------*/
	public static class staticEnergy{
		private final SimpleStringProperty username;
		private final SimpleStringProperty energy;
		
		private staticEnergy(String u,String e){
			this.username = new SimpleStringProperty(u);
			this.energy = new SimpleStringProperty(e);

		}
		
		public String getUsername(){
			return username.get();
		}
		
		public void setUsername(String u){
			username.set(u);
		}

		public String getEnergy(){
			return energy.get();
		}
		
		public void setEnergy(String u){
			energy.set(u);
		}
	}

	public TableEnergy(){

	}

	public void show(Group root1){
		EnergyProcess ep  = new EnergyProcess();
		TableView<staticEnergy> table = new TableView<staticEnergy>();
		final ObservableList<staticEnergy> data = FXCollections.observableArrayList();

		TableColumn<staticEnergy,String> firstNameCol = new TableColumn<staticEnergy,String>("Username");
		firstNameCol.setCellValueFactory(new PropertyValueFactory("Username"));
		firstNameCol.setMaxWidth(185);
		TableColumn<staticEnergy,String> lastNameCol = new TableColumn<staticEnergy,String>("Energy");
		lastNameCol.setCellValueFactory(new PropertyValueFactory("Energy"));
		lastNameCol.setMaxWidth(150);

		table.getColumns().setAll(firstNameCol, lastNameCol);

		// table.getColumns().addAll(uCol,eCol);
 		int numRows = 0;
		int i = 0;
		String[][] list = new String[50][1];
		
		try{
			ep.fillScoreboard();
			numRows = ep.getRows();
			list = ep.getList();
		}catch(Exception e){
			System.out.println(ep.getError());
		}
		
		for(i=0;i<numRows;i++){
			data.add(new staticEnergy(list[i][0],list[i][1]));
		}

		table.setItems(data);
		table.setLayoutX(220);
		table.setLayoutY(100);
		table.prefHeightProperty().bind(table.fixedCellSizeProperty().multiply(Bindings.size(table.getItems()).add(1.01)));
		table.minHeightProperty().bind(table.prefHeightProperty());
		table.maxHeightProperty().bind(table.prefHeightProperty());

		table.setFixedCellSize(25);

		root1.getChildren().add(table);
	}
}