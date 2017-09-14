package view;

import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.application.Platform;

public class Gameloop{

    public Scene theScene;
    
    public Gameloop(){
    }
    public void play(Group root){

        /*--------------------------------------------------------------------------*/
        /* Load Used Controller                                                     */
        /*--------------------------------------------------------------------------*/
        IOController keyControl = new IOController();
        GameController gameData = new GameController();

        /*--------------------------------------------------------------------------*/
        /* Load Used View/Object                                                    */
        /*--------------------------------------------------------------------------*/
        Sprite player = new Sprite();
        theScene = new Scene( root,Color.CORNSILK);
        Canvas canvas = new Canvas( 512, 512 ); /* The Animation Background */
        GraphicsContext gc = canvas.getGraphicsContext2D(); /* Draw Graphics on canvas */
        ArrayList<String> input = new ArrayList<String>(); /* Array store Keyboard Event */
        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 ); /* Main Font Scene Gameloop */
        ArrayList<Sprite> arrObs = new ArrayList<Sprite>(); /* Array obstacle */


        theScene.setOnKeyPressed(keyControl.pressEvent(input));
        theScene.setOnKeyReleased(keyControl.releaseEvent(input));


        gc.setFont( theFont );
        gc.setFill( Color.GREEN );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(1);
        
        player.setImage("img/player.png");
        player.setPosition(200, 450);
        
                
        gameData.ticker = 0; /*Loop Counter*/

        /* Run Animation (Loop) */
        new AnimationTimer(){
            public void handle(long currentNanoTime){
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - gameData.getCurrTime()) / 1000000000.0;
                gameData.setCurrTime(currentNanoTime);

                /* Spawn obstacle every 50tick / nanoTime */
                if(gameData.ticker > gameData.getSpawnTime()){

                    /* Instantiate New Obstacle */
                    Sprite obstacle = new Sprite();
                    obstacle.setImage("img/obstacle.png"); /* Set object Image */
                    double px = 350 * Math.random() + 50; /* Get Random pos */
                    obstacle.setPosition(px,0); /* Set Obstacle pos inside canvas */
                    obstacle.addVelocity(0,50); /* Add Velocity object Y Axis */

                    arrObs.add( obstacle ); /* Add Into Array */

                    gameData.ticker = 0; /* Reset Counter */
                }

                /*--------------------------------------------------------------------------*/
                /* Game Logic                                                               */
                /*--------------------------------------------------------------------------*/
                player.setVelocity(0,0);
                if (input.contains("LEFT"))
                    player.addVelocity(-50,0);
                if (input.contains("RIGHT"))
                    player.addVelocity(50,0);
                if (input.contains("UP"))
                    player.addVelocity(0,-50);
                if (input.contains("DOWN"))
                    player.addVelocity(0,50);
                if (input.contains("SPACE")){
                    Platform.exit();
                    System.exit(0);
                }
                player.update(elapsedTime*2);
                /*--------------------------------------------------------------------------*/
                /* Collision Detection                                                      */
                /*--------------------------------------------------------------------------*/
                /**
                 * 1. Loop array of obstacle(sprite)
                 * 2. Store every obstacle into temporary sprite
                 * 3. Get Current Player Position Y Axis into playerPositionY
                 * 4. Get Current index obstacle Position Y Axis into obstaclePositionY
                 * 5. Add playerPositionY +1,+2,...,+10 value, for handling missing score
                 * 6. Conditional check if pPoY(+1,+2,...,+10) equals oPosY, then score += 1
                 * 7. Set status current osbtacle sprite into TRUE. 
                 */
                Iterator<Sprite> iterasi = arrObs.iterator();
                while ( iterasi.hasNext() ){
                    Sprite obs = iterasi.next();
                    Double p = new Double(player.getPositionY());
                    int pPosY = p.intValue();
                    
                    Double o = new Double(obs.getPositionY());
                    int oPosy = o.intValue();

                    /*Handling the */                    
                    int i;
                    for(i=1;i<=10;i++){
                        if(pPosY+i == oPosy && !obs.getPass() ){
                            gameData.score++;
                            obs.setPass(true);

                        }
                    }

                    if ( player.intersects(obs) ){
                        iterasi.remove();
                    }
                    if(obs.getPositionY() > 400){
                        iterasi.remove();
                    }
                }
                
                // render
                gc.clearRect(0, 0, 512,512);
                player.render( gc );
                
                for (Sprite obs : arrObs ){
                    obs.update(elapsedTime); /*Update obstacle down side*/
                    obs.render( gc );
                }

                gameData.ticker++; /*Ticker for count obstacle respawn time*/

                String pointsText = "Energy: " + (gameData.score);
                gc.fillText( pointsText, 360, 36 );
                gc.strokeText( pointsText, 360, 36 );
            }
        }.start();

        /*--------------------------------------------------------------------------*/
        /* Adding into group                                                        */
        /*--------------------------------------------------------------------------*/
        root.getChildren().add( canvas );

    }

}