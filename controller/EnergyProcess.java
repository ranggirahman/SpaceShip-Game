package controller;

import java.io.*;
import model.DB;
import model.Energy;

public class EnergyProcess{
	
	private int rows;
	// private String username;
	// private String energy;
	private String[][] list = new String[25][25];
	private String[] list2 = new String[25];
	
	private String error;
	
	public EnergyProcess(){
		//konstruktor
		this.rows = 0;
	}
	
	public void fillScoreboard(){
		rows = 0;
		try{
			
			Energy player = new Energy();
			player.selectAll();
			
			while(player.getResult().next()){
				String username = player.getResult().getString(1); //username
				String energy = player.getResult().getString(2); //energy
				
				list[rows][0] = username;
				list[rows][1] = energy;

				
				rows++;
			}
			player.closeResult();
			player.closeConnection();
		}catch(Exception e){
			error = e.toString();
		}
	}
	
	public String[][] getList(){
		return this.list;
	}
	
	
	public String getError(){
		return this.error;
	}
	
	public void checkData(String a){
		String hasil="";
		int temp;
		try{
			Energy data = new Energy();
			data.getEnergy(a);
      if (data.getResult().next()){
      	temp =Integer.parseInt(data.getResult().getString(1));
      	hasil=Integer.toString(temp);
      }
      System.out.println(hasil);
		if (hasil == ""){
			// data.insertSkor(nama,"0");
		}
		// scenesatu.refreshtabel();
		}catch(Exception e){ 
		    System.out.println(e.toString()); 
		} 
	}
	
	public int getRows(){
		return this.rows;
	}
	
}