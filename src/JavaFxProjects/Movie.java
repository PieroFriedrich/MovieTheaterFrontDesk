/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFxProjects;

import java.util.ArrayList;

public class Movie {
    
    
    private String name;
    private String date;
    private String typetkt;
    private static final double stand_tkt = 10.00;
    private static final double gstrate = 0.05;
    private ArrayList<String> movies = new ArrayList<String>();
    
    
    public Movie(){
        
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<String> getMovies() {
        return movies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTypetkt() {
        return typetkt;
    }

    public void setTypetkt(String typetkt) {
        this.typetkt = typetkt;
    }
    
    
    
    public double tktCost(int nTkts){
        
        return nTkts*stand_tkt+ nTkts*stand_tkt*gstrate;
    }
    
    
    
}
