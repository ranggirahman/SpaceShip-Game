/*************************************************
* Filename      : Akun.java
* Programmer    : Ranggi Rahman
* Date          : 2016-05-27
* Email         : ranggirahman@gmail.com
* Website       : www.ranggirahman.id
* Deskripsi     : proram untuk set query yang nanti nya di eksekusi
*
**************************************************/

package model;

import java.sql.SQLException;


public class Akun extends DB{

    // tampungan tabel
    private String score;
    private String username;

    // konstruktor
    public Akun()throws Exception,SQLException {
        super();
    }

    // mengambil data dari tabel tenergi
    public void getAll(){ 
        try{
            String query = "SELECT * FROM tenergi order by energi desc";
            createQuery(query);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

    // mengambil score berdasarkan username
    public void getUsr(String nama){ 
        try{
            String query = "SELECT * FROM tenergi where username='"+nama+"'";
            createQuery(query);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

    // memasukan energi berdasarkan nama
    public void insertScore(String nama, int energi){ 
        try{
            String query = "INSERT INTO tenergi values('"+nama+"', '"+energi+"')";
            createUpdate(query);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

    // mengupdate energi yang ada berdasarkan username
    public void updateScore(String nama, int energi){ 
        try{
            String query = "UPDATE tenergi SET energi = energi+'"+energi+"' where username='"+nama+"'";
            createUpdate(query);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
