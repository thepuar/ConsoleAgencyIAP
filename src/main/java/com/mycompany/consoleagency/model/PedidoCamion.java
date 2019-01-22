/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consoleagency.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thepu
 */
public class PedidoCamion {
    
    private int id;
    private int volumen;
    private List<PuntoRuta> puntos = new ArrayList<>();
    private int numpuntos;

    public PedidoCamion() {
    }

    public PedidoCamion(int id, int volumen) {
        this.id = id;
        this.volumen = volumen;
        this.numpuntos=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

    public List<PuntoRuta> getPuntos() {
        return puntos;
    }

    public void setPuntos(List<PuntoRuta> puntos) {
        this.puntos = puntos;
    }
    
    public void addPunto(PuntoRuta punto){
        this.puntos.add(punto);
        this.numpuntos++;
    }
    
    
    
}
