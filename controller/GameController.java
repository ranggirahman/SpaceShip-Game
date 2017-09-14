package controller;

import java.io.*;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;


public class GameController{
	public int spawnTime;
	public int score;
	public int ticker;
	public long currTime;
	public GameController(){
		spawnTime = 100; /* Default Obstacle Spawn Time*/
		score = 0;
		currTime = System.nanoTime();
	};

	public void setSpawnTime(int s){
		spawnTime = s;
	}
	public int getSpawnTime(){
		return spawnTime;
	}
	public void setScore(int s){
		score = s;
	}
	public int getScore(){
		return score;
	}

	public void setCurrTime(long s){
		currTime = s;
	}
	public long getCurrTime(){
		return currTime;
	}
	
}