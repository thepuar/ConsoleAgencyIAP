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
public class Pedido {

    private int id;
    private int volumen;
    private String fecha;
    private String empresa;
    private String direccioncliente;
    private String direccionalmacen;

    public Pedido() {
    }

    public Pedido(int id, int volumen, String fecha,  String empresa, String direccionAlmacen, String direccionCliente) {
        this.id = id;
        this.volumen = volumen;
        this.fecha = fecha;
       this.direccioncliente = direccionCliente;
       this.direccionalmacen = direccionAlmacen;
        this.empresa = empresa;
        
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDireccioncliente() {
        return direccioncliente;
    }

    public void setDireccioncliente(String direccioncliente) {
        this.direccioncliente = direccioncliente;
    }

    public String getDireccionalmacen() {
        return direccionalmacen;
    }

    public void setDireccionalmacen(String direccionalmacen) {
        this.direccionalmacen = direccionalmacen;
    }



}
