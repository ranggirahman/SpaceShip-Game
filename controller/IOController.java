package controller;

import java.io.*;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class IOController{

	public IOController(){};

	public EventHandler<KeyEvent> pressEvent(ArrayList<String> input){
		EventHandler<KeyEvent> cek = new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){
				String code = e.getCode().toString();
				
				if ( !input.contains(code) )
				input.add( code );
			}
	   };

	   return cek;
	}

	public EventHandler<KeyEvent> releaseEvent(ArrayList<String> input){
		EventHandler<KeyEvent> ret = new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){
			  String code = e.getCode().toString();
			  input.remove( code );
			}
      };

      return ret;
	}
}