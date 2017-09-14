/*************************************************
* Filename      : VObject.java
* Programmer    : Ranggi Rahman
* Date          : 2016-05-27
* Email         : ranggirahman@gmail.com
* Website       : www.ranggirahman.id
* Deskripsi     : program pendukung VGame untuk memproses kordinat dan velocity
*
**************************************************/

package view;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class VObject{
    // tampingan image (gambar)
    private Image image;
    // tampungan koordinat
    private double x;
    private double y;
    // tampungan gerakan ke kordinat   
    private double vX; 
    private double vY;
    // ukuran w x h image
    private double w; 
    private double h; 
    // tampungan player melewati tantangan
    private boolean lewat;

    public VObject(){
        // konstruktor
        // inisialisasi
        x = 0;
        y = 0;
        vX = 0;
        vY = 0;
        lewat = false;
    }

    // meminta kordinat x
    public double getX(){
        return this.x;
    }

    // meminta kordinat y
    public double getY(){
        return this.y;
    }

    // set posisi kordinat x dan y
    public void setPosition(double x, double y){
        this.x = x;
        this.y = y;
    }

    // menambah gaya agar tantangan bergerak 
    public void addVelocity(double x, double y){
        vX += x;
        vY += y;
    }

    // set gaya agar player(pesawat) diam
    public void setVelocity(double x, double y){
        vX = x;
        vY = y;
    }    

    // set update object terhadap animasi (time)
    public void update(double time, int stat){
        this.x += vX * time;
         
        this.y += vY * time;
    }

    // set (untuk) jika tantangan terlewati
    public void setLewat(){
        this.lewat = true;
    }

    // mengembalikan fungsi setLewat
    public boolean getLewat(){
        return this.lewat;
    }

    // kode untuk set image ke program
    public void setImage(String baru, double w, double h){
        image = new Image(baru);
        this.w = w;
        this.h = h;
    }

    // menggambar graphic ke canvas
    public void draw(GraphicsContext gc){
        gc.drawImage(image, x, y, w, h);
    }

    // set retangle object (pesawat)
    public Rectangle2D getPlayer(){
        return new Rectangle2D(x, y, w, h);
    }

    // kondisi untuk mengetahui player tabrak tantangan atau tidak
    public boolean intersects(VObject tabrak){
        return tabrak.getPlayer().intersects(this.getPlayer());
    }
}