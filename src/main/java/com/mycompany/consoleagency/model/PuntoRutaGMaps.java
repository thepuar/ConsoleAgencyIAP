/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.consoleagency.model;

import com.google.maps.model.LatLng;

/**
 *
 * @author thepu
 */
public class PuntoRutaGMaps {
    
    LatLng coord;
    long metros;

    public PuntoRutaGMaps(LatLng coord, long metros) {
        this.coord = coord;
        this.metros = metros;
    }

    public PuntoRutaGMaps() {
    }

    public LatLng getCoord() {
        return coord;
    }

    public void setCoord(LatLng coord) {
        this.coord = coord;
    }

    public long getMetros() {
        return metros;
    }

    public void setMetros(long metros) {
        this.metros = metros;
    }
    
    
    
}
