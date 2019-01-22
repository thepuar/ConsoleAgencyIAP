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
public class RutaCamion {
    
    private int id;
    private List<PedidoCamion> pedidos = new ArrayList<>();

    public RutaCamion() {
    }

    public RutaCamion(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PedidoCamion> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoCamion> pedidos) {
        this.pedidos = pedidos;
    }
    
    
}
