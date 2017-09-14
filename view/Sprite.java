package view;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class Sprite
{
    private Image image;
    private double positionX;
    private double positionY;    
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
    private boolean pass;

    public Sprite(){
        positionX = 0;
        positionY = 0;    
        velocityX = 0;
        velocityY = 0;
        pass = false;
    }

    public void setImage(Image i){
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImage(String filename){
        Image i = new Image(filename);
        setImage(i);
    }

    public double getPositionY(){
        return this.positionY;
    }

    public void setPosition(double x, double y){
        positionX = x;
        positionY = y;
    }

    public void setVelocity(double x, double y){
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y){
        velocityX += x;
        velocityY += y;
    }

    public void update(double time){
        positionX += velocityX * time;
        positionY += velocityY * time;
    }

    public void render(GraphicsContext gc){
        gc.drawImage( image, positionX, positionY );
    }

    public Rectangle2D getBoundary(){
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(Sprite s){
        return s.getBoundary().intersects( this.getBoundary() );
    }
    
    public void setPass(boolean a){
        this.pass = a;
    }

    public boolean getPass(){
        return this.pass;
    }

}