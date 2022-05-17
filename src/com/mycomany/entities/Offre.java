/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author amira
 */
public class Offre {
     private int id;
    private String Nom;
    private int Pourcentage;   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public int getPourcentage() {
        return Pourcentage;
    }

    public void setPourcentage(int Pourcentage) {
        this.Pourcentage = Pourcentage;
    }
    
}
