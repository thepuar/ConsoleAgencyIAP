/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consoleagency.model;

/**
 *
 * @author thepu
 */
public class PuntoRuta {
    
    private int id;
    private double[] coord;
    private long metros;
    private int estado;

    public PuntoRuta() {
    }

    public PuntoRuta(int id, double[] coord, long metros, int estado) {
        this.id = id;
        this.coord = coord;
        this.metros = metros;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double[] getCoord() {
        return coord;
    }

    public void setCoord(double[] coord) {
        this.coord = coord;
    }

    public long getMetros() {
        return metros;
    }

    public void setMetros(long metros) {
        this.metros = metros;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
}
