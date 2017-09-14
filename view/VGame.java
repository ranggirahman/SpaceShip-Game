/*************************************************
* Filename      : VGame.java
* Programmer    : Ranggi Rahman
* Date          : 2016-05-27
* Email         : ranggirahman@gmail.com
* Website       : www.ranggirahman.id
* Deskripsi     : scene game pada program
*
**************************************************/

package view;
import controller.*;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class VGame{

	CModel data = new CModel();
	// set kordinat meteor random
	Random koordinat = new Random();
	Double max = new Double(((Constant.WIDTH-(2 * Constant.HW))/Constant.TW));
	private int wait = 0;
	// time untuk peluru
	private int TIME = 0;  
	private int energi = 0; 		//energi
	private int s1 = 0; 			//stat game dari controller
	private int s2 = 0; 			//stat game untuk pause game over
	// set kordinat player saat pertama membuka game
	private double xp = 220;
	private double yp = 220; 
	// menampung kordinat player dinamis
	private int x = 0;
	private int y = 0;
	private long lTime = System.nanoTime(); //
	private double bTime;
	private boolean nama;
	
	public VGame(){
		
	}
	
	public void start(Stage stage, String user){
		
        stage.setTitle("FIGHT THE CHALLENGES");
		Group root = new Group(); 
		Scene scene = new Scene(root);
		stage.setScene(scene);


		Canvas canvas = new Canvas(Constant.CW, Constant.CH);
		root.getChildren().add(canvas);
	    
	    GraphicsContext gc = canvas.getGraphicsContext2D();
	    CControl mv = new CControl();

	    VObject player = new VObject();
	    player.setImage("img/pesawat.png", Constant.HW, Constant.HH);
	    player.setPosition(xp, yp);
	    

	    ArrayList<VObject> arrT = new ArrayList<VObject>();

	    AnimationTimer play = new AnimationTimer(){
	    	public void handle(long xTime)
            {
                // update waktu
                bTime = (xTime - lTime) / 1000000000.0;
                lTime = xTime;
                
                int maxT = max.intValue();
            	
                
                if(TIME < 10 && wait == 0){
        			if (s2 == 0) {
	                    VObject tantangan = new VObject();
	                    
	                    // logika untuk mengganti gambar random yang turun
						if(TIME == 1 ){
							tantangan.setImage("img/1.png", Constant.TW, Constant.TH);
						}else if(TIME == 2){
							tantangan.setImage("img/2.png", Constant.TW, Constant.TH);
						}else if(TIME == 3){
							tantangan.setImage("img/3.png", Constant.TW, Constant.TH);
						}else if(TIME == 4){
							tantangan.setImage("img/4.png", Constant.TW, Constant.TH);
						}else if(TIME == 5){
							tantangan.setImage("img/5.png", Constant.TW, Constant.TH);
						}else if(TIME == 6){
							tantangan.setImage("img/6.png", Constant.TW, Constant.TH);
						}else if(TIME == 7){
							tantangan.setImage("img/7.png", Constant.TW, Constant.TH);
						}else if(TIME == 8){
							tantangan.setImage("img/8.png", Constant.TW, Constant.TH);
						}else if(TIME == 9){
							tantangan.setImage("img/9.png", Constant.TW, Constant.TH);
						}
						
                		x = (int)(Math.random()*490);
                    	y = (int)(Math.random()*50);
	                    
	                    if (x >= 490) {
	                    	wait = 1;
	                    }

	                    tantangan.setPosition(x,-y);
	                    // pengaturan agar tantangan seolah dinamis menyamping
	                    if(TIME % 2 == 0){
	                    	tantangan.addVelocity(30,100);
	                    }else{
	                    	tantangan.addVelocity(-30,100);
	                    }
	                    
                    	arrT.add(tantangan);
	                }
	                // delay agar tidak terlalu banyak melahirkan tantangan
            	}else if(TIME > 150){
            		TIME = 0;
            		
            		wait = 0;
            	}

                // meminta kordinat ke Controler Control (controler inputan kordinat dan spasi)            
                mv.cmove(scene, player.getX(), player.getY(), Constant.CW, Constant.CH);
                // meminta pengecekan spasi ditekan apa belum
               	s1 = mv.getStat();
               	player.setVelocity(0,0);
               	
               	// pengecekan spasi sudah ditekan apa belum
               	if (s1 == 0) {
               		player.addVelocity(mv.getX(), mv.getY());
               	}else{
               		nama = data.getUser(user);
               		if (!nama) {
               			data.insertCScore(user, energi);
               		}else{
               			data.updateCScore(user, energi);
               		}
               		// hentikan
           			stop();

           			// ke scene login 
           			VLogin login = new VLogin(); 
					login.show(stage);
               	}
                    
                
                // pengecekan jika player mengenai / melewati tantangan
                if (s2 == 0) {
                	
                	// set update object terhadap animasi
	                player.update(bTime, 1);

	                Iterator<VObject> iterasi = arrT.iterator();
	                while (iterasi.hasNext()){
	                    VObject tantanganm = iterasi.next();

	                    // tambahkan energi setiap melewati tantangan
	                    if (player.getY() <= tantanganm.getY() && !tantanganm.getLewat()){
	                    	tantanganm.setLewat();
	                    	energi++;
	                    }

	                    // jika player mengenai tantangan
	                    if (player.intersects(tantanganm)){
	                    	player.setImage("img/destroyed.png", 100, 100);
	                        s2 = 1;
	                        nama = data.getUser(user);		               		
	                    }
	                    if(tantanganm.getY() > Constant.CH){
	                        iterasi.remove();
	                    }
	                }
                }

                // apply canvas
                gc.clearRect(0, 0, Constant.CW, Constant.CH);
                player.draw(gc);
                
                // update tantangan yang jatuh
                for (VObject tantanganm : arrT){
                    if (s2 == 0) {
                    	tantanganm.update(bTime, 0);
                    }
                    tantanganm.draw(gc);
                }

                // ticker time untuk mengatur animasi
            	TIME++;

            	// menampilkan energi sekarang di layar
                String total = ""+energi;
                gc.setFont(new Font("Calibri", 28));
                gc.fillText(total, 430, 35);

                // menampilkan pesan ketika player bertabrakan
                if (s2 == 1) {
                	Label gend = new Label("DESTROYED"); 
			        gend.setFont(Font.font("Calibri",FontWeight.BOLD, 48));  
			        gend.setLayoutX(125); 
			        gend.setLayoutY(200);  
			        root.getChildren().add(gend);
        		}
            }
        };
		

	    play.start();
	    stage.sizeToScene();
	    stage.show();
	}


}

