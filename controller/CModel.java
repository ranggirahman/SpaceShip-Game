/*************************************************
* Filename      : CModel.java
* Programmer    : Ranggi Rahman
* Date          : 2016-05-27
* Email         : ranggirahman@gmail.com
* Website       : www.ranggirahman.id
* Deskripsi     : program untuk merubah data yang ada sebelum di eksekuis ke DB
*
**************************************************/

package controller;

import model.*;

import javafx.scene.Group;

public class CModel{
	// penampung
	private boolean nama = false;

	// mengambil skor dari user
	public boolean getUser(String nama){ 
		this.nama = false;
		try{
			Akun akun = new Akun();
			akun.getUsr(nama);
			
			while(akun.getResult().next()){
				this.nama = true;
			}
			
			akun.closeResult();
			akun.closeConnection();
		}catch(Exception e){
			System.out.println("(error) Data Error!\n");
		}
		return this.nama;
	}
	
	// memasukan skor ke user baru
	public void insertCScore(String nama, int skor){
		try{
			Akun akun = new Akun();
			akun.insertScore(nama,skor);
			akun.closeResult();
			akun.closeConnection();
		}catch(Exception e){
			System.out.println("(error) Insert Data Gagal!\n");
		}
	}
	
	// update skor untuk user yang sudah ada
	public void updateCScore(String nama, int skor){	
		try{
			Akun akun = new Akun();
			akun.updateScore(nama,skor);
			akun.closeResult();
			akun.closeConnection();
		}catch(Exception e){
			System.out.println("(error) Update Gagal!\n");
		}
	}
}

