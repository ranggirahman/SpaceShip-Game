/*************************************************
* Filename 		: Main.java
* Programmer	: Ranggi Rahman
* Date 			: 2016-05-27
* Email 		: ranggirahman@gmail.com
* Website		: www.ranggirahman.id
* Deskripsi		: file induk dari program
*
**************************************************/

import view.VLogin;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	public static void main (String[] args){
		launch(args);
	}

	@Override
	public void start(Stage stage){
		// inisiasi stage login
		VLogin login = new VLogin();
		// tampilkan stage login 
		login.show(stage);
	}
}
