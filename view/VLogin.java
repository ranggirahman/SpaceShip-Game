/*************************************************
* Filename      : VLogin.java
* Programmer    : Ranggi Rahman
* Date          : 2016-05-27
* Email         : ranggirahman@gmail.com
* Website       : www.ranggirahman.id
* Deskripsi     : scene login pada program
*
**************************************************/

package view;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType; 
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
// untuk kebutuhan pemutar lagu
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

import controller.CScoring;

public class VLogin{

    public VLogin(){

    }
  
    public static class DataSkor{
        // untuk mendefinisikan isi kolom tabel
        private final SimpleStringProperty Username;
        private final SimpleStringProperty Score;

        private DataSkor(String Username, String Score){
        	this.Username = new SimpleStringProperty(Username);
        	this.Score = new SimpleStringProperty(Score);
        }

        public String getUsername(){
            return Username.get();
        }

        public void setUsername(String Us){
            Username.set(Us);
        }

        public String getScore(){
            return Score.get();
        }

        public void setScore(String Sc){
            Score.set(Sc);
        }
    }

    //deklarasi table
    private TableView<DataSkor> table = new TableView<DataSkor>();
    //list untuk menyimpan data pada tabel
    private final ObservableList<DataSkor> data2= FXCollections.observableArrayList();

    Group root = new Group();
    Scene scene = new Scene(root,Constant.CW,Constant.CH);
    private HBox hb = new HBox();

    // yang dipanggil main 
    public void show(Stage stage){

        CScoring cscore = new CScoring();

        stage.setTitle("Welcome to FIGHT THE CHALLENGES");
        stage.setWidth(Constant.CW);
        stage.setHeight(Constant.CH);

        // label judul game
        Label scenetitle = new Label("FIGHT THE CHALLENGES"); 
        scenetitle.setFont(Font.font("Calibri",FontWeight.BOLD, 26));  
        scenetitle.setLayoutX(127); 
        scenetitle.setLayoutY(25);  
        root.getChildren().add(scenetitle);

        table.setEditable(true);
        // pembuatan kolom username pada tabel
        TableColumn usernameCol = new TableColumn("Username");
        usernameCol.setMinWidth(150);
        // penggabungan kolom dengan data username
        usernameCol.setCellValueFactory(
        	new PropertyValueFactory<DataSkor,String>("Username")
        );

        // pembuatan kolom username pada tabel
        TableColumn scoreCol = new TableColumn("Score");
    	scoreCol.setMinWidth(80);
        // penggabungan kolom dengan data score
        scoreCol.setCellValueFactory(
        	new PropertyValueFactory<DataSkor,String>("Score")
        );
       
        //add kolom ke tabel dan isi data tabel dengan data
        table.getColumns().addAll(usernameCol,scoreCol);
        table.setItems(data2);

        // membuat tampilan tabel di stage login
        final VBox vbox = new VBox();
        vbox.setSpacing(5); 
        vbox.setLayoutY(60);
        vbox.setLayoutX(130);
        vbox.setPrefHeight(230);
        vbox.setPrefWidth(244);
        vbox.getChildren().addAll(table,hb);
        vbox.setPadding(new Insets(10,0,0,10)); 

        int jumlah = 0, i = 0;
        String[][] daftar = new String[50][2];

        try{
            cscore.pScore();
            jumlah = cscore.getTotal();
            daftar = cscore.getList();
        }catch(Exception e){
            System.out.println(cscore.getError());
        }

        for (i=0; i<jumlah; i++) {
            data2.add(new DataSkor(
                daftar[i][0],
                daftar[i][1]
                ));
        }


        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        
        // label username
        Label lUname = new Label("Username");
        lUname.setFont(Font.font("Calibri",FontWeight.BOLD, 18));
        lUname.setLayoutX(140);
        lUname.setLayoutY(327);
        root.getChildren().add(lUname);

        // textfield untuk username
        TextField tfUname = new TextField();
        tfUname.setLayoutX(224);
        tfUname.setLayoutY(327);
        root.getChildren().add(tfUname);

        // button Masuk
        Button bMasuk = new Button("Masuk");
        bMasuk.setLayoutX(140);
        bMasuk.setLayoutY(380);
        bMasuk.setFont(Font.font("Calibri", FontWeight.BOLD, 18));
        root.getChildren().add(bMasuk);

        // button Masuk
        Button bSound = new Button("Mute");
        bSound.setLayoutX(224);
        bSound.setLayoutY(380);
        bSound.setFont(Font.font("Calibri", FontWeight.BOLD, 18));
        root.getChildren().add(bSound);

        // button Keluar
        Button bKeluar = new Button("Keluar");
        bKeluar.setLayoutX(300);
        bKeluar.setLayoutY(380);
        bKeluar.setFont(Font.font("Calibri", FontWeight.BOLD, 18));
        root.getChildren().add(bKeluar);

        // menampilkan messagebox alert untuk error handling username input
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Username tidak boleh kosong!");

        // pemutar lagu
        String musicFile = "sound/sound.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);

        // jika tombol masuk ditekan
        bMasuk.setOnAction(new EventHandler<ActionEvent>(){
        @Override
            public void handle(ActionEvent e){
                // pengecekan inputan username
                if(tfUname.getText().equals("")){
                    stage.setScene(scene);
                    alert.showAndWait();
                
                }else{
                    // jika username benar jalankan tampilan game
                    String nama = new String();
                    // mengambil username dari inputan
                    nama = tfUname.getText();
                    // matikan musik sebelum memulai permainan
                    mediaPlayer.stop();
                    // menyiapkan stage game dan meload nya
                    VGame game = new VGame();
                    game.start(stage, nama);
                }
            }
        });

        // jika tombol keluar ditekan
        bKeluar.setOnAction(new EventHandler<ActionEvent>(){
        @Override
            public void handle(ActionEvent e){
                // matikan musik sebelum keluar
                mediaPlayer.stop();
                System.exit(0);
            }
        });

        bSound.setOnAction(new EventHandler<ActionEvent>(){
        @Override
            public void handle(ActionEvent e){
                // matikan musik 
                mediaPlayer.pause();
            }
        });

        // memuncul kan scene yang sudah di set
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
        // play sound
        mediaPlayer.play();
    }
}
