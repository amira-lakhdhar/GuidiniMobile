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
public class Voyage {
    private int id;
    private String description;
    private float Prix;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return Prix;
    }

    public void setPrix(float Prix) {
        this.Prix = Prix;
    }

    public Voyage(int id, String description, float Prix) {
        this.id = id;
        this.description = description;
        this.Prix = Prix;
    }

    public Voyage() {
    }

}
