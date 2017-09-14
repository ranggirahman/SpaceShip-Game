/*************************************************
* Filename 		: CControl.java
* Programmer	: Ranggi Rahman
* Date 			: 2016-05-17
* Email 		: ranggirahman@gmail.com
* Website		: www.ranggirahman.id
* Deskripsi		: pemrosesan untuk menggerakan objek dalam game
*
**************************************************/

package controller;

import javafx.scene.input.KeyEvent;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class CControl{
	// tampungan kordinat untuk diolah
	private double vx;	
	private double vy;
	// tampungan untuk status spasi tertekan apa belum 
	private int s;
	// tampungan inputan masuk 
	boolean kiri, kanan, atas, bawah, spasi, rkiri, rkanan, ratas, rbawah, rspasi;	

	public CControl(){
		// inisialisasi awal
		this.kiri = false;this.rkiri = true;
		this.kanan = false;this.rkanan = true;
		this.atas = false;this.ratas = true;
		this.bawah = false;this.rbawah = true;
		this.spasi = false;this.rspasi = true;
		// inisialisasi kordinat awal
		this.vx = 0;
		this.vy = 0;
		// spasi diangap belum tertekan dari awal
		this.s = 0;
	}

	// fungsi untuk mendengarkan inputan (eventhandler) dari keyboard
	public void cmove(Scene scene, double x, double y, float w, float h){
		// ketika key ditekan
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        @Override
	        // list key yang akan didengarkan
	        public void handle(KeyEvent event) {
	            switch (event.getCode()) {
	            	// jika menekan tombol kiri
	                case LEFT:  
		                kiri = true; 
		                rkiri = false;  
	                break;
	                // jika menekan tombol kanan
	                case RIGHT: 
	                	kanan = true; 
	                	rkanan = false;  
	                break;
	                // jika menekan tombol atas
	                case UP: 
		                atas = true; 
		                ratas = false; 
	                break;
	                // jika menekan tombol bawah
	                case DOWN: 
		                bawah = true; 
		                rbawah = false; 
	                break;
	                // jika menekan tombol spasi
					case SPACE: 
						spasi = true; 
						rspasi = false; 
					break;
					
	            }
	        }
	    });
		
		// ketika key dilepas setelah ditekan
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent event) {
	            switch (event.getCode()) {
	            	// jika tombol kiri dilepas
	                case LEFT: 
	                	rkiri = true; 
	                break;
	                // jika tombol kanan dilepas
	                case RIGHT: 
	                	rkanan = true; 
	                break;
	                // jika tombol atas dilepas
	                case UP: 
	                	ratas = true;  
	                break;
	                // jika tombol bawah dilepas
	                case DOWN: 
	                	rbawah = true;  
	                break;
	                // jika tombol spasi dilepas
					case SPACE: 
						rspasi = true;  
					break;
	            }
	        }
	    });


		// yang mengatur permainan play / paused dari button control spasi
		if (spasi && !rspasi){
			if (this.s == 0) { 
				this.s = 1;
				this.spasi = false;
				this.rspasi = true;
			}else{
				this.s = 0;
				this.spasi = false;
				this.rspasi = true;
			}
		}else if(rspasi){
			// mengatur kordinat x player controled by keyboard input arah
			// agar pesawat tidak menembus batas kiri tampilan
			if (kiri && !rkiri && x >= 1){
				this.vx = -150;
			// agar pesawat tidak menembus batas kanan tampilan
			}else if (kanan && !rkanan && x <= 450){
				this.vx = 150;
			}else{
				this.vx = 0;
			}

			// mengatur kordinat y player controled by keyboard input arah
			// agar pesawat tidak menembus batas atas tampilan
			if (atas && !ratas && y >= 1){ 
				this.vy = -150;
			// agar pesawat tidak menembus batas bawah tampilan
			}else if (bawah && !rbawah && y <= 415){
				this.vy = 150;
			}else{
				this.vy = 0;
			}

			// debuging position
			// System.out.println(x);
			// System.out.println(y);

		}else{
			this.s = 0;
		}
	}

	// meminta status apakah key spasi ditekan apa belum
	public int getStat(){
		return this.s;
	}
	
	// meminta kordinat x
	public double getX(){
		return this.vx;
	}

	// meminta kordinat y
	public double getY(){
		return this.vy;
	}
}

			