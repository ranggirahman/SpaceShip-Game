package model;

import java.sql.SQLException;

/**
 * Class : Energy
 * Property : 
 *
 * Constructor :
 * Energy()
 * All program need for tenergi
 * 
 */
public class Energy extends DB{

    public Energy()throws Exception,SQLException {
        super();
    }

    public void getEnergy(String name){
        try{
            String query = "SELECT energi FROM tenergi where username='" +name+ "'";
            createQuery(query);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public void selectAll(){
        try{
            String query = "SELECT username,energi from tenergi order by energi desc";
            createQuery(query);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

}
