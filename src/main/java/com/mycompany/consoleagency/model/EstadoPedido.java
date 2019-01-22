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
public class EstadoPedido {
    
    String nombrecamion;
    int pedido;
    int punto;
    String estado;

    public EstadoPedido() {
    }

    public EstadoPedido(String nombrecamion, int pedido, int punto, String estado) {
        this.nombrecamion = nombrecamion;
        this.pedido = pedido;
        this.punto = punto;
        this.estado = estado;
    }

    public String getNombrecamion() {
        return nombrecamion;
    }

    public void setNombrecamion(String nombrecamion) {
        this.nombrecamion = nombrecamion;
    }

    public int getPedido() {
        return pedido;
    }

    public void setPedido(int pedido) {
        this.pedido = pedido;
    }

    public int getPunto() {
        return punto;
    }

    public void setPunto(int punto) {
        this.punto = punto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String toString(){
        return "Camion -> "+this.nombrecamion+" Pedido -> "+this.pedido+" Punto -> "+this.punto+" Estado -> "+this.estado;
    }
    
    
    
}
