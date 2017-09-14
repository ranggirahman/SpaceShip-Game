/*************************************************
* Filename 		: CScoring.java
* Programmer	: Ranggi Rahman
* Date 			: 2016-05-17
* Email 		: ranggirahman@gmail.com
* Website		: www.ranggirahman.id
* Deskripsi		: pemrosesan pengolahan dan pengambilan score 
*
**************************************************/


package controller;

import model.*;

public class CScoring{
	// var untuk menampung username dan score
	private String username;
	private String score;
	// var bantu untuk mengolah data tabel
	private String[][] list = new String[50][2];
	private int total;
	// penampung var error
	private String error;

	public CScoring(){
		// inisialisasi var total
		this.total = 0;
	}

	public void pScore(){
		total=0;
		try{
			Akun akun = new Akun();
			// mengambil semua data tabel dari database
			akun.getAll();

			// fetch array data tadi menjadi data terpisah
			// pengulangan sampai data yang diambil habis
			while(akun.getResult().next()){
				String username = akun.getResult().getString(1);
				String score = akun.getResult().getString(2);

				list[total][0] = username;
				list[total][1] = score;
				total = total + 1;
			}
			akun.closeResult();
			akun.closeConnection();
		}catch(Exception e){
			error = e.toString();
		}
	}

	public int getTotal(){
		return this.total;
	}	

	public String[][] getList(){
		return this.list;
	}

	public String getError(){
		return this.error;
	}
}
